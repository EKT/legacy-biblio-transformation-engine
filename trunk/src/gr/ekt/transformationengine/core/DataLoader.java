package gr.ekt.transformationengine.core;

import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.dataloaders.DataLoadingSpec;

/**
 * 
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public abstract class DataLoader {
    
	DataLoadingSpec loadingSpec;
	
    /**
     * this method reads the End Notes and creates a RecordSet list
     * @return a RecordSet
     */
    public abstract RecordSet loadData();

    public abstract RecordSet loadData(String set, String metadataPrefix);
    
	public DataLoadingSpec getLoadingSpec() {
		return loadingSpec;
	}

	public void setLoadingSpec(DataLoadingSpec spec) {
		this.loadingSpec = spec;
	}
}
