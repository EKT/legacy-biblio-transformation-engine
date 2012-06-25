package gr.ekt.transformationengine.conditions;

import gr.ekt.transformationengine.core.RecordSet;

/**
 * 
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public class EmptyCondition extends Condition {

	/**
	 * 
	 */
	public EmptyCondition() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Condition#check(gr.ekt.transformationengine.core.RecordSet)
	 */
	@Override
	public boolean check(RecordSet recordSet) {
		return true;
	}

}
