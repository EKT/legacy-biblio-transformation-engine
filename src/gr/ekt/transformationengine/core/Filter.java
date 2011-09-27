package gr.ekt.transformationengine.core;

import gr.ekt.transformationengine.core.Initializer;
import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;
import gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode;
import gr.ekt.transformationengine.exceptions.UnsupportedCriterion;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public abstract class Filter extends ProcessingStep {
   
    public Initializer initializer = null; //the initializer object
	protected List<Object> valuesList = null; //the result of initialize
	public Comparator comparator = null;
	
    /**
     * Default constuctor
     */
    public Filter() {
    }
    
    public Filter(Map<String,String> map){
        
    }

    /*
     * This method initializes the SimpleFilter and returns a List of objects
     */
    public void initialize() {
        
        Initializer currInit = this.getInitializer();
        valuesList = (currInit != null) ? currInit.initialize() : null;
    }
    
	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.ProcessingStep#process(gr.ekt.transformationengine.core.Record)
	 */
	@Override
	public boolean process(Record record) throws UnimplementedAbstractMethod,
			UnsupportedComparatorMode, UnsupportedCriterion {
		
		return this.filter(record);
	
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.ProcessingStep#process(gr.ekt.transformationengine.core.RecordSet)
	 */
	@Override
	public RecordSet process(RecordSet recordSet)
			throws UnimplementedAbstractMethod, UnsupportedComparatorMode,
			UnsupportedCriterion {
		
		RecordSet resultRecordSet = new RecordSet();

		Iterator<Record> it = recordSet.getRecords().iterator();
		while (it.hasNext()) {
			Record record = (Record) it.next();

			if(!process(record)) {
				resultRecordSet.getRecords().add(record);
			}
		}
		
		return resultRecordSet;		
	}

	/**
	 * 
	 * @param record
	 * @return
	 * @throws UnimplementedAbstractMethod
	 * @throws UnsupportedComparatorMode
	 * @throws UnsupportedCriterion
	 */
    public abstract boolean filter(Record record)throws UnimplementedAbstractMethod, UnsupportedComparatorMode,UnsupportedCriterion;

	/**
	 * @return the valuesList
	 */
	public List<Object> getValuesList() {
		return valuesList;
	}

	/**
	 * @param valuesList the valuesList to set
	 */
	public void setValuesList(List<Object> valuesList) {
		this.valuesList = valuesList;
	}

	/**
	 * @return the comparator
	 */
	public Comparator getComparator() {
		return comparator;
	}

	/**
	 * @param comparator the comparator to set
	 */
	public void setComparator(Comparator comparator) {
		this.comparator = comparator;
	}
}
