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
public abstract class ProcessingStep {

	/**
	 * 
	 */
	public ProcessingStep() {
		// TODO Auto-generated constructor stub
	}

	protected RecordSet inputRecords = null; // The input record set - before processing
	protected RecordSet outputRecords = null; // The output record set - after processing
	
    public Initializer initializer = null; //the initializer object

    /**
     *  Initialization of the filter
     */
    public abstract void initialize();
    
    
    public abstract RecordSet process(RecordSet recordSet)throws UnimplementedAbstractMethod, UnsupportedComparatorMode,UnsupportedCriterion;
    

    public abstract boolean process(Record record)throws UnimplementedAbstractMethod, UnsupportedComparatorMode,UnsupportedCriterion;

	/**
	 * @return the initializer
	 */
	public Initializer getInitializer() {
		return initializer;
	}

	/**
	 * @param initializer the initializer to set
	 */
	public void setInitializer(Initializer initializer) {
		this.initializer = initializer;
	}


	public RecordSet getInputRecords() {
		return inputRecords;
	}


	public void setInputRecords(RecordSet inputRecords) {
		this.inputRecords = inputRecords;
	}


	public RecordSet getOutputRecords() {
		return outputRecords;
	}


	public void setOutputRecords(RecordSet outputRecords) {
		this.outputRecords = outputRecords;
	}
	
}
