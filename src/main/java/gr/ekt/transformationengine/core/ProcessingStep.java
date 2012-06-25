/**
 * 
 */
package gr.ekt.transformationengine.core;

import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;
import gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode;
import gr.ekt.transformationengine.exceptions.UnsupportedCriterion;

/**
 * @author Kostas Stamatis
 *
 */
public abstract class ProcessingStep {

	/**
	 * 
	 */
	public ProcessingStep() {
		// TODO Auto-generated constructor stub
	}

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
	
}
