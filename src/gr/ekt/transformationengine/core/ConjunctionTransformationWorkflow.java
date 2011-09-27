package gr.ekt.transformationengine.core;

import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;
import gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode;
import gr.ekt.transformationengine.exceptions.UnsupportedCriterion;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
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
        
        logger.info("Processing step started");

        setInputRecords(recordSet);
        RecordSet resultRecordSet = new RecordSet(); //initially the result is an empty recordset
        RecordSet tmpInputRecordSet = new RecordSet(); //initially the place to place intermediate results is an empty recordset
        RecordSet tmpOutputRecordSet = new RecordSet(); //initially the place to place intermediate results is an empty recordset
        
        //-- check for errors 
        if(steps == null || steps.size() == 0){
        	setOutputRecords(recordSet);
            return recordSet;
        }
        
        //== give the record set to each processing step and get the result set == 
        tmpInputRecordSet = recordSet;
        tmpOutputRecordSet = null;
        for (ProcessingStep step : steps) {
            //Initialize processing step
            step.initialize();
            step.setInputRecords(tmpInputRecordSet);
            
            //== processing of the record set by the processing step
            tmpOutputRecordSet = step.process(step.getInputRecords());
            setOutputRecords(tmpOutputRecordSet);
            
            resultRecordSet.addRecords(step.getOutputRecords());
            tmpInputRecordSet = resultRecordSet;
            
            logger.info("Size of intermediate result set = " + tmpOutputRecordSet.getSize());
            logger.info("Size of entire result set until now = " + resultRecordSet.getSize());
        }
  
        logger.info("Size of final result set = " + resultRecordSet.getRecords().size());
        logger.info("Processing step finished!");
        
        return resultRecordSet;
    }
    
    public void initialize() {}
    
    
    
}
