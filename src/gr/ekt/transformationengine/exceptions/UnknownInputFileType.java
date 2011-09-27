package gr.ekt.transformationengine.exceptions;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public class UnknownInputFileType extends Exception{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 93056242639666233L;

	/** 
     * Constractor
     */
    public UnknownInputFileType() {
        
        super();
    }
    
    /** 
     * Constractor with message
     */
    public UnknownInputFileType(String message) {
        
        super(message);
    }
    
}
