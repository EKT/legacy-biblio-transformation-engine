package gr.ekt.transformationengine.core;

import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.dataloaders.DataLoadingSpec;

/** 
 * DataLoader
 *
 * Version: 1
 *
 * Date: $Date: 2007-9-21 (Fri, 21 Sep 2007) $
 *
 * this abstact class provides the basic functionality that specific loader must has 
 *
 */
public abstract class DataLoader {
    
	DataLoadingSpec loadingSpec;
	
    /**
     * this method reads the End Notes and creates a RecordSet list
     * @return a RecordSet
     */
    public abstract RecordSet loadData();

    public DataLoadingSpec getLoadingSpec() {
		return loadingSpec;
	}

	public void setLoadingSpec(DataLoadingSpec spec) {
		this.loadingSpec = spec;
	}
}
