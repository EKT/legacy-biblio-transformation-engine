package gr.ekt.transformationengine.outputGenerators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
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
import gr.ekt.transformationengine.records.MapDSpaceRecord;
import gr.ekt.transformationengine.records.MapRecord;

public class DublinCoreDSpaceOutputGenerator extends DSpaceOutputGenerator{

	/* (non-Javadoc)
	 * @see gr.ekt.repositories.utils.classification.core.OutputGenerator#generateOutput(gr.ekt.repositories.utils.classification.core.RecordSet)
	 */
	public DublinCoreDSpaceOutputGenerator() {
		super();		
	}
	
	@Override
	public boolean generateOutput(RecordSet recordSet){

		MapRecord record = (MapRecord) recordSet.getRecords().get(0);
		
		HashMap<String, DSpaceMetadata> mapping = new HashMap<String, DSpaceMetadata>();
		
		for (String key : record.recordValue.keySet()){
			String[] elements = key.split("\\.");
			if (elements[0].trim().equals("dc") && elements.length>1){
				DSpaceMetadata dspaceMetadata = new DSpaceMetadata();
				dspaceMetadata.setSchema("dc");
				dspaceMetadata.setElement(elements[1].trim());
				if (elements.length>2){
					dspaceMetadata.setQualifier(elements[2].trim());
				}
				mapping.put(key, dspaceMetadata);
			}
		}
		
		mappings.put("dc", mapping);
		
		return super.generateOutput(recordSet);
	}
	
	
}
