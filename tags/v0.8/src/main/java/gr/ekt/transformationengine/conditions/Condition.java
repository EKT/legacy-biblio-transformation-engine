package gr.ekt.transformationengine.conditions;

import gr.ekt.transformationengine.core.RecordSet;

/**
 * 
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public abstract class Condition {

	/**
	 * 
	 */
	public Condition() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract boolean check(RecordSet recordSet);

}
