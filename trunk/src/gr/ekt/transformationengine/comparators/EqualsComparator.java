package gr.ekt.transformationengine.comparators;

import gr.ekt.transformationengine.core.Comparator;

import java.util.List;
import org.apache.log4j.Logger;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
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
