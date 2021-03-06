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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXParser;
import org.jbibtex.BibTeXString;
import org.jbibtex.Key;
import org.jbibtex.LaTeXObject;
import org.jbibtex.LaTeXParser;
import org.jbibtex.LaTeXPrinter;
import org.jbibtex.ParseException;
import org.jbibtex.Value;

import au.com.bytecode.opencsv.CSVReader;

public class BibTexDataLoader extends DataLoader{

	static Logger logger = Logger.getLogger(BibTexDataLoader.class);

	/*
	 * Default constructor
	 */
	public BibTexDataLoader() {

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

			Reader reader = new FileReader(getFileName());
			BibTeXDatabase database;

			try {
				BibTeXParser parser = new BibTeXParser(){

					@Override
					public void checkStringResolution(Key key, BibTeXString string){

						if(string == null){
							System.err.println("Unresolved string: \"" + key.getValue() + "\"");
						}
					}

					@Override
					public void checkCrossReferenceResolution(Key key, BibTeXEntry entry){

						if(entry == null){
							System.err.println("Unresolved cross-reference: \"" + key.getValue() + "\"");
						}
					}
				};

				database = parser.parse(reader);

				Collection<BibTeXEntry> entries = (database.getEntries()).values();
				for(BibTeXEntry entry : entries){
					Map<Key, Value> fields = entry.getFields();
					
					Map<String, List<Object>> map = new HashMap<String, List<Object>>();
					
					for (Key key : fields.keySet()){
						Value value = entry.getField(key);
						// The field is not defined
						if(value == null){
							continue;
						}

						try {
							String latexString = value.toUserString();
							//System.out.println(latexString);

							List<LaTeXObject> objects = parseLaTeX(latexString);

							String plainTextString = printLaTeX(objects);
							//System.out.println(plainTextString);
							ArrayList<Object> tmp = new ArrayList<Object>();
							tmp.add(plainTextString);
							map.put(key.getValue(), tmp);
						} catch(Exception e){
							e.printStackTrace(System.out);
						}
					}
					
					MapDSpaceRecord mapRecord = new MapDSpaceRecord(map);
					rs.addRecord(mapRecord);
				}

			} finally {
				reader.close();
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return rs;
	}

	static
	public List<LaTeXObject> parseLaTeX(String string) throws IOException, ParseException {
		Reader reader = new StringReader(string);

		try {
			LaTeXParser parser = new LaTeXParser();

			return parser.parse(reader);
		} finally {
			reader.close();
		}
	}

	static
	public String printLaTeX(List<LaTeXObject> objects){
		LaTeXPrinter printer = new LaTeXPrinter();

		return printer.print(objects);
	}
}

