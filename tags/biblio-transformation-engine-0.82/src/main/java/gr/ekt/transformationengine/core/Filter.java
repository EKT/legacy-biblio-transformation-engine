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

import gr.ekt.transformationengine.core.Initializer;
import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;
import gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode;
import gr.ekt.transformationengine.exceptions.UnsupportedCriterion;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** 
 * Filter.java
 *
 * Version: 1
 *
 * Date: $Date: 2007-9-21 (Fri, 21 Sep 2007) $
 *
 * This is the core class having the main method 
 *
 */

/** 
 * Abstracts the filtering process providing the basic functionality that 
 * a Filter implementor must have
 */
public abstract class Filter extends ProcessingStep {
   
    public Initializer initializer = null; //the initializer object
	protected List<Object> valuesList = null; //the result of initialize
	public Comparator comparator = null;
	
    /**
     * Default constuctor
     */
    public Filter() {
    }
    
    public Filter(Map<String,String> map){
        
    }

    /*
     * This method initializes the SimpleFilter and returns a List of objects
     */
    public void initialize() {
        
        Initializer currInit = this.getInitializer();
        valuesList = (currInit != null) ? currInit.initialize() : null;
    }
    
	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.ProcessingStep#process(gr.ekt.transformationengine.core.Record)
	 */
	@Override
	public boolean process(Record record) throws UnimplementedAbstractMethod,
			UnsupportedComparatorMode, UnsupportedCriterion {
		
		return this.filter(record);
	
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.ProcessingStep#process(gr.ekt.transformationengine.core.RecordSet)
	 */
	@Override
	public RecordSet process(RecordSet recordSet)
			throws UnimplementedAbstractMethod, UnsupportedComparatorMode,
			UnsupportedCriterion {
		
		RecordSet resultRecordSet = new RecordSet();

		Iterator<Record> it = recordSet.getRecords().iterator();
		while (it.hasNext()) {
			Record record = (Record) it.next();

			if(!process(record)) {
				resultRecordSet.getRecords().add(record);
			}
		}
		
		return resultRecordSet;		
	}

	/**
	 * 
	 * @param record
	 * @return
	 * @throws UnimplementedAbstractMethod
	 * @throws UnsupportedComparatorMode
	 * @throws UnsupportedCriterion
	 */
    public abstract boolean filter(Record record)throws UnimplementedAbstractMethod, UnsupportedComparatorMode,UnsupportedCriterion;

	/**
	 * @return the valuesList
	 */
	public List<Object> getValuesList() {
		return valuesList;
	}

	/**
	 * @param valuesList the valuesList to set
	 */
	public void setValuesList(List<Object> valuesList) {
		this.valuesList = valuesList;
	}

	/**
	 * @return the comparator
	 */
	public Comparator getComparator() {
		return comparator;
	}

	/**
	 * @param comparator the comparator to set
	 */
	public void setComparator(Comparator comparator) {
		this.comparator = comparator;
	}
}
