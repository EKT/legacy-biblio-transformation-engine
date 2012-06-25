package gr.ekt.transformationengine.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * Initializer
 *
 * Version: 1
 *
 * Date: $Date: 2007-9-21 (Fri, 21 Sep 2007) $
 *
 * This interface defines the functionality that an actual Initializer must has
 *
 */
public abstract class Initializer {
   
    private Map<String, String> initParams = new HashMap<String, String>();
	
   /**
     * 
     */
    public Initializer() {
    }

    /*
     * @return the target name of this initializer may be a file name or a database name
     */
    public abstract String getTargetName();
    
    /**
     * @param initParams
     */
    public Initializer(Map<String, String> initParams) {
            this.initParams = initParams;
    }

    /*
     * This method initializes the Classifier and returns a List of objects
     */
    public abstract List initialize();

    /**
     * @return the initParams
     */
    public Map<String, String> getInitParams() {
            return initParams;
    }

    /**
     * @param initParams the initParams to set
     */
    public void setInitParams(Map<String, String> initParams) {

            this.initParams = initParams;
    }
}
