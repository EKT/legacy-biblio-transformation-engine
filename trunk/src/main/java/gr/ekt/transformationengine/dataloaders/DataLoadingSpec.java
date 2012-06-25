/**
 * 
 */
package gr.ekt.transformationengine.dataloaders;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public abstract class DataLoadingSpec {
	
	public DataLoadingSpec() {}
	
	public abstract DataLoadingSpec generateNextLoadingSpec();
}
