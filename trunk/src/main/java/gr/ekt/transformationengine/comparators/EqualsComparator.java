/*
 * EqualsComparator.java
 *
 * Created on October 1, 2007, 2:18 PM
 *
 */

package gr.ekt.transformationengine.comparators;

import gr.ekt.transformationengine.core.Comparator;

import java.util.List;
import org.apache.log4j.Logger;

public class EqualsComparator implements Comparator{
    
    // Define a static logger variable
    static Logger logger = Logger.getLogger(EqualsComparator.class);
    
    /**
     * Creates a new instance of EqualsComparator
     */
    public EqualsComparator() {
    }
    
    public  boolean compare(List valuesList, String recordValue){
    
        //-- check for error parameters
        if( (recordValue != null) && (valuesList != null) )
            recordValue = recordValue.trim();
        else 
            return false;
        
        //-- perform the search
        if(valuesList.contains(recordValue))                       
            return true;
        else 
            return false;
        
    }
    
}
