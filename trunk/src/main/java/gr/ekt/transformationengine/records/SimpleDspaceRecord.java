package gr.ekt.transformationengine.records;

import gr.ekt.transformationengine.core.Record;

public abstract class SimpleDspaceRecord extends Record{
    
	String handle;

    /*
     * Default constructor
     */
    public SimpleDspaceRecord() {
        
    }

	/**
	 * @return the handle
	 */
	public String getHandle() {
		return handle;
	}

	/**
	 * @param handle the handle to set
	 */
	public void setHandle(String handle) {
		this.handle = handle;
	}
    
}
