package gr.ekt.transformationengine.core;

import gr.ekt.transformationengine.core.Initializer;
import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;
import gr.ekt.transformationengine.exceptions.UnknownInputFileType;
import gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode;
import gr.ekt.transformationengine.exceptions.UnsupportedCriterion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/** 
 * Classifier.java
 *
 * Version: 1
 *
 * Date: $Date: 2007-9-21 (Fri, 21 Sep 2007) $
 *
 * This class extends a Filter class and implements the a specific classification process
 *
 */

public abstract class TransformationWorkflow extends ProcessingStep{
    
    String outputFileName = null; //the output file name
    List<ProcessingStep> steps = null;         //the list of used filters for this classifier 
  
   
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
