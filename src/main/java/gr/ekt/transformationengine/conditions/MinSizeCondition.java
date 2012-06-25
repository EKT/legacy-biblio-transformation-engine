package gr.ekt.transformationengine.conditions;

import gr.ekt.transformationengine.core.RecordSet;

/**
 * 
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public class MinSizeCondition extends Condition {

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Condition#check(gr.ekt.transformationengine.core.RecordSet)
	 */
	@Override
	public boolean check(RecordSet recordSet) {
		boolean toReturn = false;
		
		if (recordSet == null || recordSet.getSize() == 0) {
			toReturn =  false;
		} else if (recordSet.getSize() >= 100){
			toReturn =  true;
		} else {
			toReturn =  false;
		}
		
		return toReturn;
	}

}
