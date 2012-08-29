// Copyright (c) 2007-2012 National Documentation Centre (EKT, www.ekt.gr)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
// 
//   1. Redistributions of source code must retain the above copyright notice,
//      this list of conditions and the following disclaimer.
// 
//   2. Redistributions in binary form must reproduce the above copyright
//      notice, this list of conditions and the following disclaimer in the
//      documentation and/or other materials provided with the distribution.
// 
//   3. The name of the author may be used to endorse or promote products
//      derived from this software without specific prior written permission.
// 
// THIS SOFTWARE IS PROVIDED BY THE AUTHOR "AS IS" AND ANY EXPRESS OR IMPLIED
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
// EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
// OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
// WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
// OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
// ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// 
///////////////////////////////////////////////////////////////////////////////

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

public class DSpaceOutputGenerator extends OutputGenerator{

	Map<String, Map<String, DSpaceMetadata>> mappings = new HashMap<String, Map<String,DSpaceMetadata>>();

	String handlePrefix;

	String outputDir = "dspace_data";
	
	static final DecimalFormat FORMAT = new DecimalFormat("000000");

	/* (non-Javadoc)
	 * @see gr.ekt.repositories.utils.classification.core.OutputGenerator#generateOutput(gr.ekt.repositories.utils.classification.core.RecordSet)
	 */
	public DSpaceOutputGenerator() {
		super();
	}

	@Override
	public boolean generateOutput(RecordSet recordSet) {

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

			if (tmpRecord instanceof MapDSpaceRecord){

				//-- CREATE ITEMS DIRECTORY --
				boolean success = (new File("./output/" + outputDir + "/" +FORMAT.format(counter) ) ).mkdir();
				
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
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				//-- WRITE THE CONTENTS FILE
				try {
					BufferedWriter out = new BufferedWriter(new OutputStreamWriter
							(new FileOutputStream("./output/" + outputDir + "/"+FORMAT.format(counter)+"/"+"contents"),"UTF8"));

					out.close();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				//-- WRITE THE HANDLE FILE
				try {
					if (((MapDSpaceRecord)tmpRecord).getHandle() != null){
						BufferedWriter outhandle = new BufferedWriter(new OutputStreamWriter
								(new FileOutputStream("./output/" + outputDir + "/"+FORMAT.format(counter)+"/"+"handle"),"UTF8"));
						outhandle.write(this.getHandlePrefix()+"/"+((MapDSpaceRecord)tmpRecord).getHandle());
						outhandle.write("\n");
						outhandle.close();
					}
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
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
