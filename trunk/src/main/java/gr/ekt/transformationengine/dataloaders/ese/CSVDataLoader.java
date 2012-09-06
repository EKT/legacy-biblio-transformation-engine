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

package gr.ekt.transformationengine.dataloaders.ese;

import gr.ekt.transformationengine.core.DataLoader;
import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.records.MapEseRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

public class CSVDataLoader extends DataLoader{

	static Logger logger = Logger.getLogger(CSVDataLoader.class);

	private String fileName;

	/*
	 * Default constructor
	 */
	public CSVDataLoader() {

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
			CSVReader reader = new CSVReader(new FileReader(fileName));
			
			String [] nextLine;
			Boolean firstLine = true;
			String headers[] = null;
			
			int counter = 0;
		    while ((nextLine = reader.readNext()) != null) {
		    	if (firstLine){
					headers = nextLine;
					firstLine = false;
				}
				else {
					Map<String, List<Object>> map = new HashMap<String, List<Object>>();
					for (int i=0; i<nextLine.length; i++){
						ArrayList<Object> tmp = new ArrayList<Object>();
						tmp.add(nextLine[i]);
						map.put(headers[i], tmp);
					}
					MapEseRecord mapRecord = new MapEseRecord(map);
					mapRecord.setIdentifier(""+counter);
					counter++;
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

