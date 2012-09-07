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
 * RecordSet represents the knowledge about a set of End Note Records
 *
 * Version: 1
 *
 * Date: $Date: 2007-9-21 (Fri, 21 Sep 2007) $
 *
 */

public class RecordSet 
	implements Serializable, Iterable<Record>  {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1629197241860199501L;

	private List<Record> records;
    
    // Define a static logger variable 
    static Logger logger = Logger.getLogger(RecordSet.class);
    
    /**
     * Constructor
     */
    public RecordSet() {
        
        records = new ArrayList<Record>();   
    }
    
    public void addRecord(Record record){
        
        records.add(record);
    }
    
    public List<Record> getRecords() {
	
        return records;

    }

    public void setRecords(List<Record> records) {

        this.records = records;

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
