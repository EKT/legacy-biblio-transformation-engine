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

package gr.ekt.transformationengine.dataloaders.dspace;

import gr.ekt.transformationengine.core.DataLoader;
import gr.ekt.transformationengine.core.RecordSet;
import gr.ekt.transformationengine.records.MapDSpaceRecord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class EndnoteDataLoader extends DataLoader{

	static Logger logger = Logger.getLogger(EndnoteDataLoader.class);

	/*
	 * Default constructor
	 */
	public EndnoteDataLoader() {

	}    

	/**
	 * This method loads the Excel file and creates the Record Set
	 *
	 * @return the RecordSet with Excel Recordss
	 */


	public RecordSet loadData(){


		if ((getFileName()==null)|| (getFileName()=="")){
			logger.info("No File name!");
			return null;
		}

		RecordSet rs = new RecordSet();

		try {
			BufferedReader readbuffer = new BufferedReader(new FileReader(getFileName()));
			String strRead;

			boolean recordFinished = false;
			Map<String, List<Object>> map = new HashMap<String, List<Object>>();
			String code = "";
			
			//First two lines are useless
			readbuffer.readLine();
			readbuffer.readLine();
			
			while ((strRead=readbuffer.readLine())!=null){
				if (recordFinished){
					MapDSpaceRecord mapRecord = new MapDSpaceRecord(map);
					rs.addRecord(mapRecord);
					map = new HashMap<String, List<Object>>();
					recordFinished=false;
				}
				else {
					if (strRead.equals("")){
						//do nothing
					}
					else if (strRead.startsWith("ER")){
						recordFinished=true;
					}
					else if (strRead.startsWith("EF")){
						break;
					}
					else {
						if (!strRead.substring(0,2).trim().equals(""))
							code = strRead.substring(0,2);
						String value = strRead.substring(3).trim();
						
						if (map.containsKey(code)){
							map.get(code).add(value);
						}
						else {
							ArrayList<Object> values = new ArrayList<Object>();
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
}

