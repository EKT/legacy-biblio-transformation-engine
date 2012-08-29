// Copyright (c) 2007-2012 National Documentation Centre (EKT, www.ekt.gr)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
// 
//   1. Redistributions of source code must retain the above copyright notice,
//      this list of conditions and the following disclaimer.
// 
//   2. Redistributions in binary form must reproduce the above copyright
//      notice, this list of conditions and the following disclaimer in the
//      documentation and/or other materials provided with the distribution.
// 
//   3. The name of the author may be used to endorse or promote products
//      derived from this software without specific prior written permission.
// 
// THIS SOFTWARE IS PROVIDED BY THE AUTHOR "AS IS" AND ANY EXPRESS OR IMPLIED
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
// EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
// OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
// WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
// OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
// ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// 
///////////////////////////////////////////////////////////////////////////////

package gr.ekt.transformationengine.dataloaders;

import gr.ekt.transformationengine.core.DataLoader;
import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.records.MapRecord;

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
