package gr.ekt.transformationengine.exceptions;
//== This class represent an exception when unknown type of classifier is specified by the user ==

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
