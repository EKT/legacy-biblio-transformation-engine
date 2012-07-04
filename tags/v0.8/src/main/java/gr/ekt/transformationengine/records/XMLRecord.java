package gr.ekt.transformationengine.records;

import gr.ekt.transformationengine.records.SimpleRecord;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Element;

/**
 * XMLRecord class represent the knowledge about a simple record in XML form
 *
 * Version: 1
 *
 * Date: $Date: 2007-9-21 (Fri, 21 Sep 2007) $
 *
 */
public abstract class XMLRecord extends SimpleRecord{

	public Element currentElement = null;

	// Define a static logger variable
	static Logger logger = Logger.getLogger(XMLRecord.class);

	/**
	 * Constructor
	 * @param currentElement of JDom
	 */
	public XMLRecord(Element currentElement) {

		this.currentElement = currentElement;
	}

	public XMLRecord() {

	}

	/**
	 * Given a fieldname, return the xpath that shoulf get the value of this field from the xml
	 */
	public abstract String mapFieldNameWithXpath(String fieldName);

	@Override
	public List<String> getByName(String fieldName){
		String xpath = mapFieldNameWithXpath(fieldName);
		return getByXpath(xpath);
	}

	/*
	 * (non-Javadoc)
	 * @see gr.ekt.repositories.utils.classification.core.Record#getByName(java.lang.String)
	 */
	public List<String> getByXpath(String xpath){

		List<String> result = new ArrayList<String>();

		if (currentElement == null){return result;}
		//-- if the criterion is authors

		if(xpath!=null){
			List list = currentElement.selectNodes(xpath);

			for(int i=0; i<list.size(); i++){
				Object obj=list.get(i);
				if (obj instanceof Element){
					result.add(((Element)obj).getText().trim());
				}else if (obj instanceof Attribute){
					result.add(((Attribute)obj).getValue().trim());
				}
			}
		}

		return result;
	}

	/**
	 * this method prints the values of a specific part in XML End Note
	 *
	 * @param elementName
	 *  the name of the information requested to perint (not always match with the name of the XML element)
	 *
	 */
	public void printByName(String xpath){

		//System.out.println("XMLRecord::printByName");
		List<String> result = getByName(xpath);
		for (String s : result){
			System.out.println(s+" | ");
		}

		return ;
	}

	@Override
	public void removeField(String fieldName) {
		this.removeFieldByXpath(mapFieldNameWithXpath(fieldName));
	}

	public void removeFieldByXpath(String xpath) {
		if (currentElement != null){

			if(xpath!=null){
				List list = currentElement.selectNodes(xpath);

				for(int i=0; i<list.size(); i++){
					Object obj=list.get(i);
					if (obj instanceof Element){
						((Element)obj).detach();
					}
				}
			}
		}
	}

	@Override
	public void addValueToField(String fieldName, String fieldValue) {
		currentElement.addElement(fieldName).addText(fieldValue);
	}
}
