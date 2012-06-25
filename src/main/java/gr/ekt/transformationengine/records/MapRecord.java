package gr.ekt.transformationengine.records;

import gr.ekt.transformationengine.core.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MapRecord extends SimpleDspaceRecord{
    
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
	 * @see gr.ekt.repositories.utils.classification.core.Record#addField(java.lang.String, java.util.ArrayList)
	 */
	@Override
	public void addField(String fieldName, ArrayList<String> fieldValues) {
		if (!recordValue.containsKey(fieldName)){
    		recordValue.put(fieldName, fieldValues);
    	}
	}
	/* (non-Javadoc)
	 * @see gr.ekt.repositories.utils.classification.core.Record#removeField(java.lang.String)
	 */
	@Override
	public void removeField(String fieldName) {
		recordValue.remove(fieldName);
	}
	
}
