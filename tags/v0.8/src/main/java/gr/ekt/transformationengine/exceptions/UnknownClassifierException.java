package gr.ekt.transformationengine.exceptions;
/** 
 * UnknownClassifierException
 *
 * Version: 1
 *
 * Date: $Date: 2007-9-21 (Fri, 21 Sep 2007) $
 *
 * This class represent an exception when unknown type of classifier is specified by the user
 *
 */
public class UnknownClassifierException extends Exception{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2715467392153419150L;

	/** 
     * Constractor
     */
    public UnknownClassifierException() {
        
        super();
    }
    
    /** 
     * Constractor
     */
    public UnknownClassifierException(String message) {
        
        super(message);
    }
    
}
