package gr.ekt.transformationengine.records;

import gr.ekt.transformationengine.core.Record;

import java.util.ArrayList;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public abstract class ESERecord extends Record{
    
	String identifier;
	ArrayList<String> metadataPrefixes = new ArrayList<String>();

    /*
     * Default constructor
     */
    public ESERecord() {
        
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
