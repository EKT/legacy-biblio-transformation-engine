/**
 * 
 */
package gr.ekt.transformationengine.records;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kstamatis
 *
 */
public class MapDSpaceRecord extends MapRecord {

	String handle;
	String handlePrefix;
	ArrayList<String> metadataPrefixes = new ArrayList<String>();
	
	/**
	 * @param recordValue
	 */
	public MapDSpaceRecord(Map<String, List<String>> recordValue) {
		super(recordValue);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public MapDSpaceRecord() {
		// TODO Auto-generated constructor stub
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
