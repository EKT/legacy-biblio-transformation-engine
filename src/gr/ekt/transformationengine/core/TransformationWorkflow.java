package gr.ekt.transformationengine.core;

import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;
import gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode;
import gr.ekt.transformationengine.exceptions.UnsupportedCriterion;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public abstract class TransformationWorkflow extends ProcessingStep{
    
    protected String outputFileName = null; //the output file name
    protected List<ProcessingStep> steps = null; //the list of used filters for this classifier 
  
   
    // Define a static logger variable
    static Logger logger = Logger.getLogger(TransformationWorkflow.class);
    
    public TransformationWorkflow(){
    	steps = new ArrayList<ProcessingStep>();
    }
    
	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.ProcessingStep#initialize()
	 */
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.ProcessingStep#process(gr.ekt.transformationengine.core.Record)
	 */
	@Override
	public boolean process(Record record) throws UnimplementedAbstractMethod,
			UnsupportedComparatorMode, UnsupportedCriterion {
		
		return false;
	}

	/**
	 * @param steps the steps to set
	 */
	public void setSteps(List<ProcessingStep> steps) {
		this.steps = steps;
	}
}
