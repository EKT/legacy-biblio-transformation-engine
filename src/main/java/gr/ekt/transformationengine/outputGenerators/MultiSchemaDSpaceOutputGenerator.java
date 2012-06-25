package gr.ekt.transformationengine.outputGenerators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import gr.ekt.transformationengine.records.MapRecord;
import gr.ekt.transformationengine.records.SimpleDspaceRecord;

public class MultiSchemaDSpaceOutputGenerator extends OutputGenerator{

	Map<String, Map<String, DSpaceMetadata>> mappings = new HashMap<String, Map<String,DSpaceMetadata>>();
	//Map<String, DSpaceMetadata> mapping = new HashMap<String, DSpaceMetadata>();

	String handlePrefix;

	String outputDir = "dspace_data";
	
	static final DecimalFormat FORMAT = new DecimalFormat("000000");

	/* (non-Javadoc)
	 * @see gr.ekt.repositories.utils.classification.core.OutputGenerator#generateOutput(gr.ekt.repositories.utils.classification.core.RecordSet)
	 */
	public MultiSchemaDSpaceOutputGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean generateOutput(RecordSet recordSet) {
		// TODO Auto-generated method stub

		if( (recordSet == null ))
			return false;

		File file = new File("./output/" + outputDir);
		if (file.exists())
			this.deleteDirectory(file);
		file.mkdir();

		Iterator<Record> it = recordSet.getRecords().iterator();
		int counter = 0;
		while(it.hasNext()){
			counter++;   
			Record tmpRecord= (Record)it.next();

			if (tmpRecord instanceof SimpleDspaceRecord){

				//-- CREATE ITEMS DIRECTORY --
				boolean success = (new File("./output/" + outputDir + "/" +FORMAT.format(counter) ) ).mkdir();
				if (success){
					//System.out.println("Directory: "+counter+ " created");
				} 
				
				for (String schema : mappings.keySet()){

					//== create the root element of XML DSpace record file ==
					Document doc = DocumentFactory.getInstance().createDocument();
					Element root = doc.addElement("dublin_core");
					root.addAttribute("schema", schema);
					
					for (String field : mappings.get(schema).keySet()){
						List<String> resultList = tmpRecord.getByName(field);

						if (resultList.size()>0){
							Iterator<String> it2 = resultList.iterator();
							while(it2.hasNext()){

								String currentStringValue = (String)it2.next();

								if("".equals(currentStringValue.trim()) || currentStringValue == null){
									continue;
								}

								currentStringValue = currentStringValue.trim();

								DSpaceMetadata metadata = mappings.get(schema).get(field);

								Element dcvalue = null;
								dcvalue = root.addElement("dcvalue");
								dcvalue.addAttribute("schema", schema);


								dcvalue.setText(currentStringValue);
								//dcvalue.addAttribute("schema", metadata.getSchema());
								dcvalue.addAttribute("element", metadata.getElement());
								if (metadata.getQualifier() != null && !metadata.getQualifier().equals(""))
									dcvalue.addAttribute("qualifier", metadata.getQualifier());
								
								if (metadata.getLanguage() != null && !metadata.getLanguage().equals(""))
									dcvalue.addAttribute("language", metadata.getLanguage());
							}
						}

					}
					
					//== output the file ==
					try {
						String filename = "dublin_core";
						if (!schema.equals("dc")){
							filename = "metadata_"+schema;
						}
						FileOutputStream fos = new FileOutputStream("./output/" + outputDir + "/" + FORMAT.format(counter)+"/"+filename+".xml");
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
				}

				//-- WRITE THE CONTENTS FILE
				try {
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter
							(new FileOutputStream("./output/" + outputDir + "/"+FORMAT.format(counter)+"/"+"contents"),"UTF8"));

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
					if (((SimpleDspaceRecord)tmpRecord).getHandle() != null){
						BufferedWriter outhandle = new BufferedWriter(new OutputStreamWriter
								(new FileOutputStream("./output/" + outputDir + "/"+FORMAT.format(counter)+"/"+"handle"),"UTF8"));
						outhandle.write(this.getHandlePrefix()+"/"+((SimpleDspaceRecord)tmpRecord).getHandle());
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

	public Map<String, Map<String, DSpaceMetadata>> getMappings() {
		return mappings;
	}


	public void setMappings(Map<String, Map<String, DSpaceMetadata>> mappings) {
		this.mappings = mappings;
	}

	public String getHandlePrefix() {
		return handlePrefix;
	}

	public void setHandlePrefix(String handlePrefix) {
		this.handlePrefix = handlePrefix;
	}
	
	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
}
