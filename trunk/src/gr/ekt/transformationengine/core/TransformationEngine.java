package gr.ekt.transformationengine.core;

import gr.ekt.transformationengine.conditions.Condition;
import gr.ekt.transformationengine.core.DataLoader;
import gr.ekt.transformationengine.core.OutputGenerator;
import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;
import gr.ekt.transformationengine.exceptions.UnknownClassifierException;
import gr.ekt.transformationengine.exceptions.UnknownInputFileType;
import gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode;
import gr.ekt.transformationengine.exceptions.UnsupportedCriterion;

import java.util.List;
import org.apache.log4j.Logger;


/**
 * 
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public class TransformationEngine  {

	DataLoader dataLoader = null;
	OutputGenerator outputGenerator = null;
	TransformationWorkflow workflow = null;
	List<Condition> processingConditions = null;
	List<Condition> outputConditions = null;
	RecordSet transformationResults = new RecordSet();  // Temp memory keeping incremental results from each data loading / tranformation iteration

	static Logger logger = Logger.getLogger(TransformationEngine.class);
	
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

	public Object transform() throws UnknownClassifierException, UnknownInputFileType ,
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
			transformationResults.addRecords(resultSet);

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
		
		outputGenerator.generateOutput(transformationResults);

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