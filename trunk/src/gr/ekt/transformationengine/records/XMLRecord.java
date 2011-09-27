package gr.ekt.transformationengine.records;

import gr.ekt.transformationengine.core.Record;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Element;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public abstract class XMLRecord extends Record{

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
		List<String> result = getByName(xpath);
		for (String s : result){
			System.out.println(s+" | ");
		}
		return ;
	}

	@Override
	public void addField(String fieldName, ArrayList<String> fieldValues) {	
		for (String s : fieldValues){
				currentElement.addElement(fieldName).addText(s);
		}
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
}
