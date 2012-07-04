/*
 * UnsupportedComparatorMode.java
 *
 * Created on September 26, 2007, 1:06 PM
 *
 */
package gr.ekt.transformationengine.exceptions;


public class UnsupportedComparatorMode extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5432887959492158594L;

	/** Creates a new instance of UnsupportedComparatorMode */
    public UnsupportedComparatorMode() {
        
        super();
        
    }
    
     /** 
     * Constractor
     */
    public UnsupportedComparatorMode(String message) {
        
        super(message);
    }
    
}
