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
public class MapEseRecord extends MapRecord {

	String identifier;
	ArrayList<String> metadataPrefixes = new ArrayList<String>();
	
	/**
	 * @param recordValue
	 */
	public MapEseRecord(Map<String, List<String>> recordValue) {
		super(recordValue);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public MapEseRecord() {
		// TODO Auto-generated constructor stub
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
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
