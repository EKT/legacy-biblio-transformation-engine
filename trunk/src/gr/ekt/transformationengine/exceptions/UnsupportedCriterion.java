package gr.ekt.transformationengine.exceptions;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
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
