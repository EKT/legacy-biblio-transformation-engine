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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;
import gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode;
import gr.ekt.transformationengine.exceptions.UnsupportedCriterion;

/**
 * A Modifier is a ProcessingStep that performs updates to the record contents (e.g. add/remove/update fields).
 * @author kstamatis
 *
 */
public abstract class Modifier extends ProcessingStep {

	/**
	 * 
	 */
	public Modifier() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.ProcessingStep#initialize()
	 */
	@Override
	public void initialize() {
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.ProcessingStep#process(gr.ekt.transformationengine.core.Record)
	 */
	@Override
	public boolean process(Record record) throws UnimplementedAbstractMethod,
			UnsupportedComparatorMode, UnsupportedCriterion {

		this.modify(record);
		
		return true;
		
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

			if(process(record)) {
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
	 */
    //public abstract Map<String, ArrayList<String>> modify(Record record)throws UnimplementedAbstractMethod;

    public abstract void modify(Record record)throws UnimplementedAbstractMethod;
    
}
