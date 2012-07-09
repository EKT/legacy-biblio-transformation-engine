package gr.ekt.transformationengine.dataloaders.ese;

import gr.ekt.transformationengine.core.DataLoader;
import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.records.MapEseRecord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class RISDataLoader extends DataLoader{

	static Logger logger = Logger.getLogger(RISDataLoader.class);

	private String fileName;

	/*
	 * Default constructor
	 */
	public RISDataLoader() {

	}    

	/**
	 * This method loads the Excel file and creates the Record Set
	 *
	 * @return the RecordSet with Excel Recordss
	 */


	public RecordSet loadData(){


		if ((fileName==null)|| (fileName=="")){
			logger.info("No File name!");
			return null;
		}

		RecordSet rs = new RecordSet();

		try {
			BufferedReader readbuffer = new BufferedReader(new FileReader(fileName));
			String strRead;

			boolean recordFinished = false;
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			
			int counter = 0;
			while ((strRead=readbuffer.readLine())!=null){
				if (recordFinished){
					MapEseRecord mapRecord = new MapEseRecord(map);
					mapRecord.setIdentifier(""+counter);
					counter++;
					rs.addRecord(mapRecord);
					map = new HashMap<String, List<String>>();
					recordFinished=false;
				}
				else {
					if (strRead.equals("")){
						//do nothing
					}
					else if (strRead.startsWith("ER")){
						recordFinished=true;
					}
					else {
						String code = strRead.substring(0,2);
						String value = strRead.substring(6).trim();
						
						if (map.containsKey(code)){
							map.get(code).add(value);
						}
						else {
							ArrayList<String> values = new ArrayList<String>();
							values.add(value);
							map.put(code, values);
						}
					}	
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rs;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}

