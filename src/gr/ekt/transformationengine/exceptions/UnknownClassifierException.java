package gr.ekt.transformationengine.exceptions;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
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
