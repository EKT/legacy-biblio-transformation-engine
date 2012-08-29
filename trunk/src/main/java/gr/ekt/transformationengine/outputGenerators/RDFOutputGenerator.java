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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC;

import gr.ekt.transformationengine.core.OutputGenerator;
import gr.ekt.transformationengine.core.Record;
import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.dspace.DSpaceMetadata;

/**
 * @author nkons
 *
 */
public class RDFOutputGenerator extends OutputGenerator {
	
	Map<String, DSpaceMetadata> mapping = new HashMap<String, DSpaceMetadata>();
	
	String modelUri = "http://example.com/sample";
	String nsA = modelUri + "#";
	String nsDC = "http://purl.org/dc/elements/1.1/";
	
	public RDFOutputGenerator() {
		super();
	}
	
	@Override
	public boolean generateOutput(RecordSet recordSet) {
		Iterator<Record> it = recordSet.getRecords().iterator();
		
		Model model = ModelFactory.createDefaultModel();
		
		int counter = 0;
		while(it.hasNext()) {
			counter++;   
			Record tmpRecord= (Record) it.next();

			for (String field : mapping.keySet()) {
				List<String> resultList = tmpRecord.getByName(field);
				
				if (resultList.size() > 0) {
					Iterator<String> it2 = resultList.iterator();
					while (it2.hasNext()) {
						String currentStringValue = (String) it2.next();
						//DSpaceMetadata metadata = mapping.get(field);
						Resource resource = model.createResource(nsA + "Individual" + counter); //Handle could go here instead of a mere counter
						if (field.equalsIgnoreCase("ARTL_TITLE")) {		

							resource.addProperty(DC.title, currentStringValue);
						}
						if (field.equalsIgnoreCase("SOURCEYEAR")) {
							resource.addProperty(DC.date, currentStringValue);
						}
						if (field.equalsIgnoreCase("JRNLFULL")) {
							resource.addProperty(DC.publisher, currentStringValue);
						}

					}
				}
			}						
		}
		
		//Available: RDF/XML, RDF/XML-ABBREV, N-TRIPLE, N3, TURTLE
		model.write(System.out, "RDF/XML");
		
		return false;
	}
	
	public Map<String, DSpaceMetadata> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, DSpaceMetadata> mapping) {
		this.mapping = mapping;
	}
}

//<?xml version="1.0"?>
//<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
//            xmlns:dc="http://purl.org/dc/elements/1.1/">  
//  <rdf:Description>
//    <dc:creator>a</dc:creator>
//    <dc:contributor>b</dc:contributor>
//    <dc:publisher>c</dc:publisher>
//    <dc:subject>d</dc:subject>
//    <dc:description>e</dc:description>
//    <dc:identifier>f</dc:identifier>
//    <dc:relation>g</dc:relation>
//    <dc:source>h</dc:source>
//    <dc:rights>i</dc:rights>
//    <dc:format>j</dc:format>
//    <dc:type>k</dc:type>
//    <dc:title>l</dc:title>
//    <dc:date>m</dc:date>
//    <dc:coverage>n</dc:coverage>
//    <dc:language>o</dc:language>
//  </rdf:Description>
//</rdf:RDF>
