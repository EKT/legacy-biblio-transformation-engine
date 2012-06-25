/*
 * ConjunctionClassifier.java
 *
 * Created on September 26, 2007, 12:45 PM
 *
 */

package gr.ekt.transformationengine.core;

import java.util.Iterator;

import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;
import gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode;
import gr.ekt.transformationengine.exceptions.UnsupportedCriterion;

public class ConjunctionTransformationWorkflow extends TransformationWorkflow {
    
	public ConjunctionTransformationWorkflow(){
		super();
	}

    /**
     * The implementation of filtering process for the ConjuctionClassifier
     *
     * @param recordSet
     *  The RecordSet to be classified 
     *
     * @return the result RecordSet 
     */
    public RecordSet process(RecordSet recordSet) throws UnimplementedAbstractMethod, UnsupportedComparatorMode,UnsupportedCriterion {
        
        logger.info("filter started");

        RecordSet resultRecordSet = recordSet; //initially the result is the same record set
        
        //-- check for errors 
        if(steps == null || steps.size() == 0){
            return recordSet;
        }
        
        //== give the record set to each filter and get the result set ==
        Iterator<ProcessingStep> it = steps.iterator();
        while(it.hasNext()){
 			
            ProcessingStep currentStep =  it.next();
            
            //Initialize filter
            currentStep.initialize();
            
            //== give the record set to the filter
            resultRecordSet = currentStep.process(resultRecordSet);
            
            System.out.println("Size of result set = " + resultRecordSet.getRecords().size());
        }
        
        logger.info("filter finished");
        
        return resultRecordSet;
    }
    
    public void initialize() {}
    
    
    
}
