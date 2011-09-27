package gr.ekt.transformationengine.exceptions;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
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
