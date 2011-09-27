package gr.ekt.transformationengine.comparators;

import gr.ekt.transformationengine.core.Comparator;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

import uk.ac.shef.wit.simmetrics.similaritymetrics.SmithWatermanGotohWindowedAffine;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public class SmithWatermanAlgorithmComparator implements Comparator{

    // Define a static logger variable
    static Logger logger = Logger.getLogger(SmithWatermanAlgorithmComparator.class);
    
    
    float maxSimilarity = 0.0f;
    String maxString = "";
    
    /**
     * Default (empty) constructor
     *
     */
    public SmithWatermanAlgorithmComparator() {
            // TODO Auto-generated constructor stub
    }

       
    
    public float getMaxSimilarity() {
		return maxSimilarity;
	}



	public void setMaxSimilarity(float maxSimilarity) {
		this.maxSimilarity = maxSimilarity;
	}



	public String getMaxString() {
		return maxString;
	}



	public void setMaxString(String maxString) {
		this.maxString = maxString;
	}



	public  boolean compare(List valuesList, String recordValue){
    
        //-- check for error
        if( (valuesList == null) || (recordValue == null) ){
            return false;
        }
        
        //Instantiate the algorithm to be used for the comparator
    	SmithWatermanGotohWindowedAffine algorithm = new SmithWatermanGotohWindowedAffine();
    	
        Iterator it = valuesList.iterator();
        
        //== For each element in valuesList ==
        while(it.hasNext()){
            
            String currentValue = (String) it.next();
  
            //== make use of the simmetric algorithm ==
            float similarity = algorithm.getSimilarity(currentValue, recordValue);
            if (similarity >= 0.55){
            	return true;
                
            }  
            else {
            	if (similarity > maxSimilarity){
            		maxSimilarity = similarity;
            		maxString = currentValue;
            	}
            }
        }
    	return false;
    }
}
