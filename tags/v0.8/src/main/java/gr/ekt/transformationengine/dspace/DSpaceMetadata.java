/**
 * 
 */
package gr.ekt.transformationengine.dspace;

/**
 * @author kstamatis
 *
 */
public class DSpaceMetadata {

	
	String schema;
	String element;
	String qualifier;
	String language;
	/**
	 * 
	 */
	public DSpaceMetadata() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the schema
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @param schema the schema to set
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	 * @return the element
	 */
	public String getElement() {
		return element;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(String element) {
		this.element = element;
	}

	/**
	 * @return the qualifier
	 */
	public String getQualifier() {
		return qualifier;
	}

	/**
	 * @param qualifier the qualifier to set
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
}
