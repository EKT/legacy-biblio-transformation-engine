package gr.ekt.transformationengine.core;

import gr.ekt.transformationengine.conditions.Condition;
import gr.ekt.transformationengine.core.DataLoader;
import gr.ekt.transformationengine.core.OutputGenerator;
import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;
import gr.ekt.transformationengine.exceptions.UnknownClassifierException;
import gr.ekt.transformationengine.exceptions.UnknownInputFileType;
import gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode;
import gr.ekt.transformationengine.exceptions.UnsupportedCriterion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

/** 
 * TransformationEngine.java
 *
 * Version: 1
 *
 * Date: $Date: 2007-9-21 (Fri, 21 Sep 2007) $
 *
 * This class initializes 
 *
 */


public class TransformationEngine  {

	DataLoader dataLoader = null;
	OutputGenerator outputGenerator = null; //the output Generator
	TransformationWorkflow workflow = null;   //the classifiers list
	List<Condition> processingConditions = new ArrayList<Condition>();
	List<Condition> outputConditions = new ArrayList<Condition>();
	
	// Define a static logger variable
	static Logger logger = Logger.getLogger(TransformationEngine.class);

	public TransformationEngine(){

	}
	
	/**
	 * Constructor
	 *
	 * @param recordSet
	 *  the RecordSet with End Notes to be classified
	 *
	 * @param outputGenerator
	 *  the OutputGenerator to be used for generating output files
	 *
	 * @param classifierSpecificationList
	 *  the list with specifications for each Classifier defined by the user
	 *
	 */
	public TransformationEngine(OutputGenerator outputGenerator){
		this.outputGenerator = outputGenerator;
	}

	/**
	 * This method performs the classification process
	 * @return boolean value in success true in error false
	 * 
	 * @throws gr.ekt.transformationengine.exceptions.UnknownClassifierException 
	 * @throws gr.ekt.transformationengine.exceptions.UnknownInputFileType 
	 * @throws gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod 
	 * @throws gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode 
	 * @throws gr.ekt.transformationengine.exceptions.UnsupportedCriterion 
	 */
	public  boolean transform() throws UnknownClassifierException , UnknownInputFileType ,
	UnimplementedAbstractMethod, UnsupportedComparatorMode, UnsupportedCriterion {

		RecordSet recordSet = null;
		RecordSet resultSet = null;
		
		boolean readyToProcess = false;
		boolean readyToOutput = false;
		
		while (!readyToOutput) {
			while (!readyToProcess) {
				recordSet = dataLoader.loadData();
				
				readyToProcess = true;
				for (Condition c : processingConditions) {
					if (c.check(recordSet) == false) {
						readyToProcess = false;
						dataLoader.setLoadingSpec(dataLoader.getLoadingSpec().generateNextLoadingSpec());
						break;
					}
				}
			}

			logger.info("Workflow started");

			logger.debug("Call process() for current processing step");
			resultSet = workflow.process(recordSet);

			logger.debug("Items in ResultSet: " + resultSet.getSize());

			readyToOutput = true;
			for (Condition c : outputConditions) {
				if (c.check(resultSet) == false) {
					readyToOutput = false;
					readyToProcess = false;
					dataLoader.setLoadingSpec(dataLoader.getLoadingSpec().generateNextLoadingSpec());
					break;
				}
			}
		}
		
		outputGenerator.generateOutput(resultSet);

		logger.debug("Transformation Engine ended");

		return true;
	}

	/**
	 * @param outputGenerator the outputGenerator to set
	 */
	public void setOutputGenerator(OutputGenerator outputGenerator) {
		this.outputGenerator = outputGenerator;
	}


	/**
	 * @param dataLoader the dataLoader to set
	 */
	public void setDataLoader(DataLoader dataLoader) {
		this.dataLoader = dataLoader;
	}

	public TransformationWorkflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(TransformationWorkflow workflow) {
		this.workflow = workflow;
	}	
}