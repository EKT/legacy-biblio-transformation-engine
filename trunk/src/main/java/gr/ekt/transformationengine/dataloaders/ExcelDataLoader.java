package gr.ekt.transformationengine.dataloaders;

import gr.ekt.transformationengine.core.DataLoader;
import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.records.MapRecord;
//import gr.ekt.transformationengine.records.MapRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.apache.log4j.Logger;



public class ExcelDataLoader extends DataLoader {

	static Logger logger = Logger.getLogger(ExcelDataLoader.class);

	private String fileName;
	private int sheetIndex;

	/*
	 * Default constructor
	 */
	public ExcelDataLoader() {

	}


	public RecordSet loadData(){

		if ((fileName==null)||(fileName=="")) {
			logger.info("No file name!");
			return null;
		}

		RecordSet rs = new RecordSet();
		try{
			File f = new File(fileName);
			System.out.println("looking in " + f.getAbsolutePath());
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setEncoding( "Cp1252" );

			Workbook workbook = Workbook.getWorkbook(f,workbookSettings);

			Sheet sheet = workbook.getSheet(sheetIndex);				

			for (int i=1; i<sheet.getRows(); i++)
			{
				Map<String, List<String>> map = new HashMap<String, List<String>>();
				for (int j=0; j<sheet.getColumns(); j++)
				{  				 
					Cell headCell = sheet.getCell(j,0);
					String headString = headCell.getContents().trim();
					Cell valuesCell = sheet.getCell(j,i);
					String valuesString = valuesCell.getContents();
					ArrayList<String> tmp = new ArrayList<String>();  		
					tmp.add(valuesString);
					map.put(headString, tmp);     

				}

				MapRecord mapRecord = new MapRecord(map);
				rs.addRecord(mapRecord);		
			}

		}catch (FileNotFoundException e) {
			e.printStackTrace();

		}catch (IOException e) {
			e.printStackTrace();

		}catch (Exception e) {
			e.printStackTrace();

		}
		return rs; 
	}

	public int getSheetIndex() {
		return sheetIndex;
	}


	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
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
