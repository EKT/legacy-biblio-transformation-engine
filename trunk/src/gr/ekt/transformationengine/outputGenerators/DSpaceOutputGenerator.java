package gr.ekt.transformationengine.outputGenerators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import gr.ekt.transformationengine.core.OutputGenerator;
import gr.ekt.transformationengine.core.Record;
import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.dspace.DSpaceMetadata;
import gr.ekt.transformationengine.records.DspaceRecord;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public class DSpaceOutputGenerator extends OutputGenerator{

	Map<String, DSpaceMetadata> mapping = new HashMap<String, DSpaceMetadata>();

	/* (non-Javadoc)
	 * @see gr.ekt.repositories.utils.classification.core.OutputGenerator#generateOutput(gr.ekt.repositories.utils.classification.core.RecordSet)
	 */
	public DSpaceOutputGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean generateOutput(RecordSet recordSet) {
		// TODO Auto-generated method stub

		if( (recordSet == null ))
			return false;

		File file = new File("./output/dspace_data");
		if (file.exists())
			this.deleteDirectory(file);
		file.mkdir();

		Iterator<Record> it = recordSet.getRecords().iterator();
		int counter = 0;
		while(it.hasNext()){
			counter++;   
			Record tmpRecord= (Record)it.next();
			
			//== create the root element of XML DSpace record file ==
			Document doc = DocumentFactory.getInstance().createDocument();
			Element root = doc.addElement("dublin_core");

			HashMap<String, Document> prefixesMap = new HashMap<String, Document>();
			for (String metadataPrefix : ((DspaceRecord)tmpRecord).getMetadataPrefixes() ){
				Document doc2 = DocumentFactory.getInstance().createDocument();
				doc2.addElement("dublin_core").addAttribute("schema", metadataPrefix);
				prefixesMap.put(metadataPrefix, doc2);
			}
			
			if (tmpRecord instanceof DspaceRecord){

				for (String field : mapping.keySet()){
					List<String> resultList = tmpRecord.getByName(field);

					if (resultList.size()>0){
						Iterator<String> it2 = resultList.iterator();
						while(it2.hasNext()){

							String currentStringValue = (String)it2.next();

							if("".equals(currentStringValue.trim()) || currentStringValue == null){
								continue;
							}

							currentStringValue = currentStringValue.trim();

							DSpaceMetadata metadata = mapping.get(field);
							
							String schema = metadata.getSchema();
							Element dcvalue = null;
							if (schema.equals("dc"))
								dcvalue = root.addElement("dcvalue");
							else
								dcvalue = prefixesMap.get(schema).getRootElement().addElement("dcvalue");
							
							dcvalue.setText(currentStringValue);
							//dcvalue.addAttribute("schema", metadata.getSchema());
							dcvalue.addAttribute("element", metadata.getElement());
							if (metadata.getQualifier() != null && !metadata.getQualifier().equals(""))
								dcvalue.addAttribute("qualifier", metadata.getQualifier());
						}
					}
				}

				//-- CREATE ITEMS DIRECTORY --
				boolean success = (new File("./output/dspace_data/"+counter ) ).mkdir();
				if (success){
					//System.out.println("Directory: "+counter+ " created");
				} 

				//-- WRITE THE CONTENTS FILE
				try {
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter
							(new FileOutputStream("./output/dspace_data/"+counter+"/"+"contents"),"UTF8"));

					out.close();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				//-- WRITE THE HANDLE FILE
				try {
					if (((DspaceRecord)tmpRecord).getHandle() != null){
						BufferedWriter outhandle = new BufferedWriter(new OutputStreamWriter
								(new FileOutputStream("./output/dspace_data/"+counter+"/"+"handle"),"UTF8"));
						outhandle.write(((DspaceRecord)tmpRecord).getHandlePrefix()+"/"+((DspaceRecord)tmpRecord).getHandle());
						outhandle.write("\n");
						outhandle.close();
					}
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				//== output the file ==
				try {
					FileOutputStream fos = new FileOutputStream("./output/dspace_data/"+counter+"/"+"dublin_core.xml");
					OutputFormat format = OutputFormat.createPrettyPrint();

					XMLWriter writer = new XMLWriter(fos, format);
					
					writer.write(doc);
					
					fos.close();
					writer.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//== Output the other prefixes files
				try {
					for (String metadataPrefix : ((DspaceRecord)tmpRecord).getMetadataPrefixes() ){
						FileOutputStream fos = new FileOutputStream("./output/dspace_data/"+counter+"/"+"metadata_"+metadataPrefix+".xml");
						OutputFormat format = OutputFormat.createPrettyPrint();

						XMLWriter writer = new XMLWriter(fos, format);
						writer.write(prefixesMap.get(metadataPrefix));
						
						writer.close();
						fos.close();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return false;
	}


	public boolean deleteDirectory(File path) {
		if( path.exists() ) {
			File[] files = path.listFiles();
			for(int i=0; i<files.length; i++) {
				if(files[i].isDirectory()) {
					deleteDirectory(files[i]);
				}
				else {
					files[i].delete();
				}
			}
		}
		return( path.delete() );
	}


	/**
	 * @return the mapping
	 */
	public Map<String, DSpaceMetadata> getMapping() {
		return mapping;
	}


	/**
	 * @param mapping the mapping to set
	 */
	public void setMapping(Map<String, DSpaceMetadata> mapping) {
		this.mapping = mapping;
	}


}
