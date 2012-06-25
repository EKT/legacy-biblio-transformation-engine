/**
 * 
 */
package gr.ekt.transformationengine.modifiers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gr.ekt.transformationengine.core.Modifier;
import gr.ekt.transformationengine.core.Record;
import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;

/**
 * @author kstamatis
 *
 */
public abstract class KeyRenameModifier extends Modifier {

	Map<String, String> keyMapping = new HashMap<String, String>();

	/**
	 * 
	 */
	public KeyRenameModifier() {
		// TODO Auto-generated constructor stub	
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Modifier#modify(gr.ekt.transformationengine.core.Record)
	 */
	@Override
	public void modify(Record record) throws UnimplementedAbstractMethod {
		// TODO Auto-generated method stub

		for (String key : keyMapping.keySet()){
			List<String> values = record.getByName(key);
			ArrayList<String> values2 = new ArrayList<String>();
			for (String s : values){
				values2.add(s);
			}
			if (values!=null){
				record.removeField(key);
				record.addField(keyMapping.get(key), values2);
			}
		}
	}

	public Map<String, String> getKeyMapping() {
		return keyMapping;
	}

	public void setKeyMapping(Map<String, String> keyMapping) {
		this.keyMapping = keyMapping;
	}

	//Abstract method to load the mapping
	public abstract void loadMapping();
}
