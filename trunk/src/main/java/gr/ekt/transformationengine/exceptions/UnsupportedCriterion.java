/*
 * UnsupportedCriterion.java
 *
 * Created on September 28, 2007, 1:18 PM
 *
 */
package gr.ekt.transformationengine.exceptions;


public class UnsupportedCriterion extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5676309821383085795L;

	/** Creates a new instance of UnsupportedComparatorMode */
    public UnsupportedCriterion() {
        
        super();
        
    }
    
     /** 
     * Constractor
     */
    public UnsupportedCriterion(String message) {
        
        super(message);
    }
    
}
