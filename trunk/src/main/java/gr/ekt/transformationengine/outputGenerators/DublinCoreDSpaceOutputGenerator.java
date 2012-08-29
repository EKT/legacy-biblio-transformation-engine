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
