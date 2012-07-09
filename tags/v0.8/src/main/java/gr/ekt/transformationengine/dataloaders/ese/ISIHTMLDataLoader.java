package gr.ekt.transformationengine.dataloaders.ese;

import gr.ekt.transformationengine.core.DataLoader;
import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.records.MapEseRecord;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ISIHTMLDataLoader extends DataLoader{

	static Logger logger = Logger.getLogger(ISIHTMLDataLoader.class);

	private String fileName;

	/*
	 * Default constructor
	 */
	public ISIHTMLDataLoader() {

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

			StringBuffer fileData = new StringBuffer(1000);
			BufferedReader reader = new BufferedReader(
					new FileReader(fileName));
			char[] buf = new char[1024];
			int numRead=0;
			while((numRead=reader.read(buf)) != -1){
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
				buf = new char[1024];
			}
			reader.close();
			String fileString = fileData.toString();
			fileString = fileString.replace("<hr>", "<hr/>");
			fileString = fileString.replace("<br>", "<br/>");
			
			Document document = (new SAXReader()).read(new ByteArrayInputStream(fileString.getBytes("UTF-8")));
			List records = document.getRootElement().selectNodes("//table/table");

			int counter = 0;
			for (Iterator iter = records.iterator(); iter.hasNext(); ) {
				Element record = (Element) iter.next();
				
				Map<String, List<String>> map = new HashMap<String, List<String>>();
				
				for (Object trO : record.elements("tr")){
					Element tr = (Element)trO;
					List tds = tr.elements("td");
					ArrayList<String> tmp = new ArrayList<String>();
					tmp.add(((Element)tds.get(1)).getText().trim());
					map.put(((Element)tds.get(0)).getText().trim(), tmp);
				}
				
				MapEseRecord mapRecord = new MapEseRecord(map);
				mapRecord.setIdentifier(""+counter);
				counter++;
				rs.addRecord(mapRecord);
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
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

