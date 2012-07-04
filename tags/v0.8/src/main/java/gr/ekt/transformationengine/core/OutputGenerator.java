package gr.ekt.transformationengine.core;

import java.util.List;

import gr.ekt.transformationengine.core.RecordSet;


public abstract class OutputGenerator {
	
    /**
     * This method generates the output file having a RecordSet
     * @param recordSet
     * @param outputFile
     * @return
     */
	public abstract boolean generateOutput(RecordSet recordSet);
    
    /**
     * This method generates the output file having a RecordSet recursively obtained from a list
     * @param recordSetList
     * @param outputFile
     * @return
     */
    public boolean generateOutput(List<RecordSet> recordSetList){
    	boolean returnValue = true;
    	for (RecordSet recordSet : recordSetList){
    		System.out.println("Output generator outputs "+recordSet.getSize()+" records!");
    		returnValue = generateOutput(recordSet);
    	}
    	return returnValue;
    }
}
