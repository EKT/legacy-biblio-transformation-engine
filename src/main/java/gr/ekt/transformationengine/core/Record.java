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

package gr.ekt.transformationengine.core;

import java.util.ArrayList;
import java.util.List;

/** 
 * The abstract class that represents the information of a Record. A record consists of fields and each 
 * field consists of a list of values
 */
public abstract class Record {

    /**
     * Given a fieldName, return the list of values for this field
     *
     * @param elementName
     *  the requested information (not always the name of the tag)
     *
     * @return the List of the values 
     */
    public abstract List<String> getByName(String fieldName);
    
    /**
     * Given a fieldName, print the list of values for this field
     *
     * @param elementName
     *  the requested information (not always the name of the tag)
     */
    public abstract void printByName(String fieldName);

    /**
     * Remove totally a field from the specific record
     * @param fieldName The name of the field to be removed
     */
    public abstract void removeField(String fieldName);
    
    /**
     * Remove totally a specific value from the specified input field
     * @param fieldName The name of the field to remove the value from
     * @param value The value to remove from the specified field
     */
    public abstract void removeValueFromField(String fieldName, String value);
    
    /**
     * A method for supporting adding a new field to the record along with its values
     * @param fieldName The name of the field to be added
     * @param fieldValues An arraylist of string values for the added field
     */
    public abstract void addField(String fieldName, ArrayList<String> fieldValues);  
    
    /**
     * A method for supporting adding a new value to the specified field of the record. Keep in mind that 
     * if field already exists, the field value must be added to the list of previous values
     * @param fieldName The name of the field that the value should be added
     * @param fieldValue The string value to be added to the field
     */
    public abstract void addValueToField(String fieldName, String fieldValue);
    
    /**
     * A method for supporting updating a field of the record. All previous values are deleted, and the new 
     * ones are added
     * @param fieldName The name of the field to be updated
     * @param fieldValues The arrayList of strings of the new values for the field
     */
    public void updateField(String fieldName, ArrayList<String> fieldValues){
    	removeField(fieldName);
    	addField(fieldName, fieldValues);
    }
    
    /**
     * A method for supporting updating a value of an existing field of the record. Only the specified value 
     * is being updated
     * @param fieldName The name of the field to be updated
     * @param oldFieldValue The value of the field that should be updated
     * @param newFieldValue The new value of the field that should replace the old one
     */
    public void updateValueInField(String fieldName, String oldFieldValue, String newFieldValue){
    	removeValueFromField(fieldName, oldFieldValue);
    	addValueToField(fieldName, newFieldValue);
    }    
}
