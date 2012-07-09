/**
 * 
 */
package gr.ekt.transformationengine.records;

import java.util.ArrayList;
import java.util.List;

import gr.ekt.transformationengine.core.Record;

/**
 * @author kstamatis
 *
 */
public abstract class SimpleRecord extends Record {

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#getByName(java.lang.String)
	 */
	@Override
	public abstract List<String> getByName(String elementName);

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#printByName(java.lang.String)
	 */
	@Override
	public abstract  void printByName(String elementName);

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#removeField(java.lang.String)
	 */
	@Override
	public abstract void removeField(String fieldName);

	/*
	 * (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#removeValueFromField(java.lang.String, java.lang.String)
	 */
	@Override
	public abstract void removeValueFromField(String fieldName, String value);
	
	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#addField(java.lang.String, java.util.ArrayList)
	 */
	@Override
	public void addField(String fieldName, ArrayList<String> fieldValues) {
		for (String s : fieldValues)
			addValueToField(fieldName, s);
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#addFieldValue(java.lang.String, java.lang.String)
	 */
	@Override
	public abstract void addValueToField(String fieldName, String fieldValue);
	
}
