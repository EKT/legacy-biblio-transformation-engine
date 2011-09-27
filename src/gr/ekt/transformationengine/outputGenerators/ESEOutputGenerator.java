package gr.ekt.transformationengine.outputGenerators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import gr.ekt.transformationengine.core.OutputGenerator;
import gr.ekt.transformationengine.core.Record;
import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.dspace.ESEMetadata;
import gr.ekt.transformationengine.records.ESERecord;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public class ESEOutputGenerator extends OutputGenerator {

	Map<String, ESEMetadata> mapping = new HashMap<String, ESEMetadata>();
	
	/**
	 * Default (empty) constructor
	 */
	public ESEOutputGenerator() {
		
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.OutputGenerator#generateOutput(gr.ekt.transformationengine.core.RecordSet)
	 */
	@Override
	public boolean generateOutput(RecordSet recordSet) {
		// TODO Auto-generated method stub

		if( (recordSet == null ))
			return false;

		File file = new File("./output/ese.xml");
		if (file.exists())
			file.delete();

		//== create the root element of XML DSpace record file ==
		Document doc = DocumentFactory.getInstance().createDocument();
		
		Namespace defaultNS = new Namespace("", "http://www.openarchives.org/OAI/2.0/");
		Namespace xsiNS = new Namespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		
		Element root = doc.addElement(new QName("OAI-PMH", defaultNS));
		root.addNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		root.addAttribute(new QName("schemaLocation", xsiNS), "http://www.openarchives.org/OAI/2.0/ http://www.openarchives.org/OAI/2.0/OAI-PMH.xsd");
		
		Element records = root.addElement("ListRecords");
		
		Iterator<Record> it = recordSet.getRecords().iterator();
		int counter = 0;
		while(it.hasNext()){
			counter++;   
			Record tmpRecord= (Record)it.next();

			//== if the record is PandektisXML 
			if (tmpRecord instanceof ESERecord){
				
				ESERecord eseRecord = (ESERecord)tmpRecord;
				
				Element record = records.addElement("record");
				
				//Record header
				Element header = record.addElement("header");
				header.addElement("identifier").addText(eseRecord.getIdentifier());
				header.addElement("datestamp").addText((new java.util.Date()).toString());
				Element metadataEle = record.addElement("metadata");
				
				Namespace dc = new Namespace("dc", "http://purl.org/dc/elements/1.1/");
				Namespace dcterms = new Namespace("dcterms", "http://purl.org/dc/terms/");
				Namespace europeana = new Namespace("europeana", "http://www.europeana.eu/schemas/ese/");
				
				Element eurecord = metadataEle.addElement(new QName("record",europeana));
				eurecord.addNamespace("dc", "http://purl.org/dc/elements/1.1/");
				eurecord.addNamespace("dcterms", "http://purl.org/dc/terms/");
				//eurecord.addNamespace("europeana", "http://www.europeana.eu/schemas/ese/");
				
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

							ESEMetadata metadata = mapping.get(field);
							
							String schema = metadata.getSchema();
							Element dcvalue = null;
							if (schema.equals("dc"))
								dcvalue = eurecord.addElement(new QName(metadata.getElement(), dc));
							else if (schema.equals("dcterms"))
								dcvalue = eurecord.addElement(new QName(metadata.getElement(), dcterms));
							else
								dcvalue = eurecord.addElement(new QName(metadata.getElement(), europeana));
							
							dcvalue.setText(currentStringValue);
						}
					}
				}
			}
		}
		
		//== output the file ==
		try {
			FileOutputStream fos = new FileOutputStream("./output/ese.xml");
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
		
		return false;
	}

	/**
	 * @return the mapping
	 */
	public Map<String, ESEMetadata> getMapping() {
		return mapping;
	}

	/**
	 * @param mapping the mapping to set
	 */
	public void setMapping(Map<String, ESEMetadata> mapping) {
		this.mapping = mapping;
	}

	
	
}
