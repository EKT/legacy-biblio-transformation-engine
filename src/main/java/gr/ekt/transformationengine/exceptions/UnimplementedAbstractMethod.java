/*
 * UnimplementedAbstractMethod.java
 *
 * Created on September 26, 2007, 1:06 PM
 *
 */

package gr.ekt.transformationengine.exceptions;


public class UnimplementedAbstractMethod extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1196659558484344633L;

	/** Creates a new instance of UnimplementedAbstractMethod */
    public UnimplementedAbstractMethod() {
        
        super();
        
    }
    
     /** 
     * Constractor
     */
    public UnimplementedAbstractMethod(String message) {
        
        super(message);
    }
    
}
