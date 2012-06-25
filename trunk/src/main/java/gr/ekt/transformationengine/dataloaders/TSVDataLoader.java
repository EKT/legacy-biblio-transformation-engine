package gr.ekt.transformationengine.dataloaders;

import gr.ekt.transformationengine.core.DataLoader;
import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.records.MapRecord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class TSVDataLoader extends DataLoader{

	static Logger logger = Logger.getLogger(TSVDataLoader.class);

	private String fileName;

	/*
	 * Default constructor
	 */
	public TSVDataLoader() {

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

			boolean firstLine = true;
			String headers[] = null;
			while ((strRead=readbuffer.readLine())!=null){
				if (firstLine){
					headers = strRead.split("\t");
					firstLine = false;
				}
				else {
					String valuesArray[] = strRead.split("\t");
					Map<String, List<String>> map = new HashMap<String, List<String>>();
					for (int i=0; i<valuesArray.length; i++){
						ArrayList<String> tmp = new ArrayList<String>();
						tmp.add(valuesArray[i]);
						map.put(headers[i], tmp);
					}
					MapRecord mapRecord = new MapRecord(map);
					rs.addRecord(mapRecord);
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

