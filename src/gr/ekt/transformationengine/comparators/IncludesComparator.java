package gr.ekt.transformationengine.comparators;

import gr.ekt.transformationengine.core.Comparator;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public class IncludesComparator implements Comparator{
    
    // Define a static logger variable
    static Logger logger = Logger.getLogger(IncludesComparator.class);
    
    /**
     * Creates a new instance of EqualsComparator
     */
    public IncludesComparator() {
    }
    
    public  boolean compare(List valuesList, String recordValue){
    
        //-- check for error parameters
        if( (recordValue != null) && (valuesList != null) ){
            recordValue = recordValue.trim();
            
        }else 
            return false;
        
        Iterator it = valuesList.iterator();
         
         //== For each element in valuesList ==
         while(it.hasNext()){
             
             String currentValue = (String) it.next();
             
             //== check if the one string contains the other ==
             if((  (currentValue.indexOf(recordValue)) != -1 ) || ((recordValue.indexOf(currentValue)) != -1) ){
                 return true;
             }
         }
         
         //== if there were not a match
         return false;
    }
}
