package gr.ekt.transformationengine.exceptions;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
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
