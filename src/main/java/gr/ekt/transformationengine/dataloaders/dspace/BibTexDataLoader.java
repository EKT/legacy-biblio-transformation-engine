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

	private String fileName;

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


		if ((fileName==null)|| (fileName=="")){
			logger.info("No File name!");
			return null;
		}

		RecordSet rs = new RecordSet();

		try {

			Reader reader = new FileReader(fileName);
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
					
					Map<String, List<String>> map = new HashMap<String, List<String>>();
					
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
							ArrayList<String> tmp = new ArrayList<String>();
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

