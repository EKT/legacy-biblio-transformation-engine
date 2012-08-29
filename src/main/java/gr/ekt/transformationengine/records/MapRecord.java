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

package gr.ekt.transformationengine.records;

import gr.ekt.transformationengine.records.SimpleRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapRecord extends SimpleRecord{
    
	public Map<String, List<String>> recordValue=new HashMap<String, List<String>>();
   
    /**
     * Constructor
     * @param recordValue
     *
     */
    public MapRecord(Map<String, List<String>> recordValue) {
        
        this.recordValue = recordValue;
    }
    
    /*
     * Default constructor
     */
    public MapRecord() {
        
    }
    /**
     * this method gets specific information from the End Note XML
     *
     * 
     * @param columnSpec
     * @return the List of the values
     */
    public List<String> getByName(String fieldName){
        List<String> result = new ArrayList<String>();
        if (recordValue.get(fieldName)!=null){
            result= recordValue.get(fieldName);
        }
        return result;
    }
    
    public void printByName(String fieldName) {
        List<String> result=(List<String>) recordValue.get(fieldName);
        for (String s : result)
        	System.out.println(s);
    }
	
	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.records.SimpleRecord#addValueToField(java.lang.String, java.lang.String)
	 */
	@Override
	public void addValueToField(String fieldName, String fieldValue) {
		if (recordValue.containsKey(fieldName)){
			recordValue.get(fieldName).add(fieldValue);
		}
		else {
			ArrayList<String> tmp = new ArrayList<String>();
			tmp.add(fieldValue);
			recordValue.put(fieldName, tmp);
		}
	}
	
	/* (non-Javadoc)
	 * @see gr.ekt.repositories.utils.classification.core.Record#removeField(java.lang.String)
	 */
	@Override
	public void removeField(String fieldName) {
		recordValue.remove(fieldName);
	}
	
	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.records.SimpleRecord#removeValueFromField(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeValueFromField(String fieldName, String value) {
		List<String> values = recordValue.get(fieldName);
		if (values.contains(value)){
			values.remove(value);
		}
	}
}
