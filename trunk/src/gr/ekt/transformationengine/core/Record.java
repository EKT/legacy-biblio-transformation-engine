package gr.ekt.transformationengine.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public abstract class Record {

    /**
     * Anyone who implement this method must return the list with requested values in a Record
     *
     * @param elementName
     *  the requested information (not always the name of the tag)
     *
     * @return the List of the values 
     */
    public abstract List<String> getByName(String elementName);
    
    /**
     * Anyone who implement this method must print information about this Record
     *
     * @param elementName
     *  the requested information (not always the name of the tag)
     */
    public abstract void printByName(String elementName);

    /**
     * A method for supporting removing a field from the record
     * @param fieldName The name of the field to be removed
     */
    public abstract void removeField(String fieldName);
    
    /**
     * A method for supporting adding a new field to the record
     * @param fieldName The name of the field to be added
     * @param fieldValues An arraylist of string values for the added field
     */
    public abstract void addField(String fieldName, ArrayList<String> fieldValues);  
    
    /**
     * A method for supporting updating a new field to the record. It uses the removeField and addField methods.
     * @param fieldName The name of the field to be updated
     * @param fieldValues The arrayList of strings of the new values for the field
     */
    public void updateField(String fieldName, ArrayList<String> fieldValues){
    	removeField(fieldName);
    	addField(fieldName, fieldValues);
    }
    
    // Flag used by some simple filters
    private String Flag = null;

    public void setFlag(String Flag) {
        this.Flag = Flag;
    }

    public String getFlag() {
        return Flag;
    }
    
}
