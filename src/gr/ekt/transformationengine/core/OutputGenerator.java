package gr.ekt.transformationengine.core;

import java.util.List;

import gr.ekt.transformationengine.core.RecordSet;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public abstract class OutputGenerator {
	
	public abstract boolean generateOutput(RecordSet recordSet);
    
    public boolean generateOutput(List<RecordSet> recordSetList){
    	boolean returnValue = true;
    	for (RecordSet recordSet : recordSetList){
    		returnValue = generateOutput(recordSet);
    	}
    	return returnValue;
    } 
}
