/**
 * 
 */
package gr.ekt.transformationengine.modifiers;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaxen.function.NameFunction;

/**
 * @author Kostas Stamatis
 * @author National Documentation Center
 *
 */
public class FileKeyRenameModifier extends KeyRenameModifier {

	String filename;

	/**
	 * 
	 */
	public FileKeyRenameModifier() {
		// TODO Auto-generated constructor stub
		super();
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.modifiers.KeyRenameModifier#loadMapping()
	 */
	@Override
	public void loadMapping() {

		//Read the mapping from the filename
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(filename));
			List maps = document.getRootElement().elements("map");
			for (Object mapObject : maps){
				Element mapElement = (Element)mapObject;
				String nameBefore = mapElement.element("key_before").getText();
				String nameAfter = mapElement.element("key_after").getText();
				if (nameAfter!=null && nameBefore!=null){
					keyMapping.put(nameBefore, nameAfter);
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
		this.loadMapping();
	}	
}