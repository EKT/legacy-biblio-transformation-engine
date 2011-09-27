package gr.ekt.transformationengine.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * 
 * @author Kosta Stamatis (kstamatis@ekt.gr)
 * @author Nikos Houssos (nhoussos@ekt.gr)
 * @copyright 2011 - National Documentation Center
 */
public class RecordSet 
	implements Serializable, Iterable<Record>  {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1629197241860199501L;

	private List<Record> records;
    
    static Logger logger = Logger.getLogger(RecordSet.class);
    
    /**
     * Constructor
     */
    public RecordSet() {
        
        records = new ArrayList<Record>();   
    }
    
    public void addRecord(Record record){
        if (records == null){
        	records = new ArrayList<Record>(); 
        }
        records.add(record);
    }
    
    public List<Record> getRecords() {
	
        return records;

    }

    public void setRecords(List<Record> records) {

        this.records = records;

    }
	
    public void addRecords(RecordSet recordSet){
    	if (records == null){
        	records = new ArrayList<Record>(); 
        }
    	
        records.addAll(recordSet.getRecords());
    }
    
    /**
     * Returns the number of records in the RecordSet.
     * 
     * @return Number of records in the RecordSet.
     */
	public int getSize() {
		return getRecords().size();
	}

	/**
     * Prints information about the RecordSet.
     */
    public void printInfo(){
        
        Iterator<Record> it = records.iterator();
        
        while(it.hasNext()){
            
            Record currentRecord = (Record)it.next();
           
            logger.debug("---- New Record ---");
            currentRecord.printByName("title");
            currentRecord.printByName("authors");
        }
        
    } 
 
    public Iterator<Record> iterator() {
    	return records.iterator();
    }
    
    /**
     * Serializes the result set using an ObjectOutputStream
     * 
     * @param out the ObjectOutputStream to output the serialized object
     * @throws IOException In case the output file does not exist
     */
    private void writeObject(ObjectOutputStream out) throws IOException{
    	out.defaultWriteObject();
    }
    
    /**
     * Restoring a serialized item
     * @param in The ObjectInputStream from which the serialized object will be read
     * @throws IOException In case the imput file does not exist
     * @throws ClassNotFoundException In case the input object is of different Class
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
    	in.defaultReadObject();
    }
    
    
}
