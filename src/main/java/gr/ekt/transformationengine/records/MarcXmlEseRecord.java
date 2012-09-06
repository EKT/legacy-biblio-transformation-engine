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

/**
 * 
 */
package gr.ekt.transformationengine.records;

import gr.ekt.transformationengine.records.MapEseRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Subfield;


/**
 * @author kstamatis
 *
 */
public abstract class MarcXmlEseRecord extends SimpleRecord {

	public org.marc4j.marc.Record marcxmlRecord = null;
	
	/**
	 * Default constructor
	 */
	public MarcXmlEseRecord() {
		// TODO Auto-generated constructor stub
	}

	public abstract Map<String, Character> mapFieldNameWithMarcCode(String fieldName);
	
	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.records.SimpleRecord#getByName(java.lang.String)
	 */
	@Override
	public List<Object> getByName(String elementName) {
		Map<String, Character> mapping = mapFieldNameWithMarcCode(elementName);
		
		String key = (String)mapping.keySet().toArray()[0];
		List<String> tmp = getByDatafieldArray(key, new char[]{mapping.get(key)});
		List<Object> result = new ArrayList<Object>();
		for (String s : tmp)
			result.add(s);
		return result;
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.records.SimpleRecord#printByName(java.lang.String)
	 */
	@Override
	public void printByName(String elementName) {
		System.out.println("printByName: Not implements yet!");
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.records.SimpleRecord#removeField(java.lang.String)
	 */
	@Override
	public void removeField(String fieldName) {
		// TODO Auto-generated method stub
		System.out.println("removeField: Not implements yet!");
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.records.SimpleRecord#removeValueFromField(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeValueFromField(String fieldName, Object value) {
		// TODO Auto-generated method stub
		System.out.println("removeValueFromField: Not implements yet!");
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.records.SimpleRecord#addValueToField(java.lang.String, java.lang.String)
	 */
	@Override
	public void addValueToField(String fieldName, Object fieldValue) {
		// TODO Auto-generated method stub
		System.out.println("addValueToField: Not implements yet!");
	}

	public org.marc4j.marc.Record getMarcxmlRecord() {
		return marcxmlRecord;
	}

	public void setMarcxmlRecord(org.marc4j.marc.Record marcxmlRecord) {
		this.marcxmlRecord = marcxmlRecord;
	}
	
	//Helper methods
	
	public String getByDatafield(String datafieldStr, char[] codeStrArray) {

		//System.out.println(datafieldStr);
		DataField datafield = (DataField)marcxmlRecord.getVariableField(datafieldStr);

		if (datafield == null)
			return null;

		String result = "";
		for (char codeStr : codeStrArray){
			if (datafield.getSubfield(codeStr) != null)
				result += datafield.getSubfield(codeStr).getData() + " - ";
		}

		return result.replace(" - ", "").trim();

	}

	public ArrayList<String> getByDatafieldArray(String datafieldStr, char[] codeStrArray) {
		ArrayList<String> resultArray = new ArrayList<String>();

		//System.out.println(datafieldStr);
		List datafields = marcxmlRecord.getVariableFields(datafieldStr);

		for (Object obj : datafields){
			DataField datafield = (DataField)obj;

			if (datafield == null)
				return null;

			String result = "";
			for (char codeStr : codeStrArray){
				if (datafield.getSubfield(codeStr) != null)
					result += datafield.getSubfield(codeStr).getData() + " - ";
			}

			resultArray.add(result.replace(" - ", "").trim());
		}

		return resultArray;
	}

	public DataField getByDatafield(String datafieldStr) {

		//System.out.println(datafieldStr);
		DataField datafield = (DataField)marcxmlRecord.getVariableField(datafieldStr);

		if (datafield == null)
			return null;


		return datafield;
	}

	public List<DataField> getByDatafieldArray(String datafieldStr) {

		//System.out.println(datafieldStr);
		List datafields = marcxmlRecord.getVariableFields(datafieldStr);

		if (datafields == null)
			return null;


		return datafields;
	}

	public String getByDatafield(String datafieldStr, char[] codeStrArray, char code, String value) {

		//System.out.println(datafieldStr);
		DataField datafield = (DataField)marcxmlRecord.getVariableField(datafieldStr);

		if (datafield == null)
			return null;

		if (datafield.getSubfield(code) != null){
			if (!datafield.getSubfield(code).getData().equals(value))
				return null;
		}


		String result = "";
		for (char codeStr : codeStrArray){
			if (datafield.getSubfields(codeStr) != null)
				for (Object subfield : datafield.getSubfields(codeStr))
					result += ((Subfield) subfield).getData() + " | ";
		}

		return result.trim();
	}

	public String getByControlField(String tag) {

		ControlField controlField = (ControlField)marcxmlRecord.getVariableField(tag);

		if (controlField == null)
			return null;

		return controlField.getData();
	}

	public void removeDatafield(String datafieldStr) {

		//System.out.println(datafieldStr);
		DataField datafield = (DataField)marcxmlRecord.getVariableField(datafieldStr);

		marcxmlRecord.removeVariableField(datafield);
	}

	public void addDatafield(String datafieldStr, String value){
		MarcFactory factory = MarcFactory.newInstance();

		if (datafieldStr.equals("101")){
			DataField df = factory.newDataField(datafieldStr, '0', ' ');
			df.addSubfield(factory.newSubfield('a', value));

			marcxmlRecord.addVariableField(df);
		}
		if (datafieldStr.equals("100")){
			DataField df = factory.newDataField(datafieldStr, ' ', ' ');
			df.addSubfield(factory.newSubfield('a', value));

			marcxmlRecord.addVariableField(df);
		}
		if (datafieldStr.equals("102")){
			DataField df = factory.newDataField(datafieldStr, '0', ' ');
			df.addSubfield(factory.newSubfield('a', value));

			marcxmlRecord.addVariableField(df);
		}
		else if (datafieldStr.equals("314")){
			DataField df = factory.newDataField(datafieldStr, '0', ' ');
			df.addSubfield(factory.newSubfield('a', value));

			marcxmlRecord.addVariableField(df);
		}


	}
}
