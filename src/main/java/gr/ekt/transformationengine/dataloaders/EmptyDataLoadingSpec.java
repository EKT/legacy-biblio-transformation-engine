/**
 * 
 */
package gr.ekt.transformationengine.dataloaders;

/**
 * 
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public class EmptyDataLoadingSpec extends DataLoadingSpec {

	/**
	 * 
	 */
	public EmptyDataLoadingSpec() {}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.dataloaders.DataLoadingSpec#generateNextLoadingSpec()
	 */
	@Override
	public DataLoadingSpec generateNextLoadingSpec() {
		return this;
	}

}
