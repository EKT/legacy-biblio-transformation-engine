package gr.ekt.transformationengine.records;

import gr.ekt.transformationengine.core.Record;

import java.util.ArrayList;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public abstract class DspaceRecord extends Record{
    
	String handle;
	String handlePrefix;
	ArrayList<String> metadataPrefixes = new ArrayList<String>();

    /*
     * Default constructor
     */
    public DspaceRecord() {
        
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

	/**
	 * @return the metadataPrefixes
	 */
	public ArrayList<String> getMetadataPrefixes() {
		return metadataPrefixes;
	}

	/**
	 * @param metadataPrefixes the metadataPrefixes to set
	 */
	public void setMetadataPrefixes(ArrayList<String> metadataPrefixes) {
		this.metadataPrefixes = metadataPrefixes;
	}

    public void addMetadataPrefix(String prefix){
    	this.metadataPrefixes.add(prefix);
    }

	/**
	 * @return the handlePrefix
	 */
	public String getHandlePrefix() {
		return handlePrefix;
	}

	/**
	 * @param handlePrefix the handlePrefix to set
	 */
	public void setHandlePrefix(String handlePrefix) {
		this.handlePrefix = handlePrefix;
	}
    
    
}
