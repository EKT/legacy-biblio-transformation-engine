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

/*
 * ConjunctionClassifier.java
 *
 * Created on September 26, 2007, 12:45 PM
 *
 */

package gr.ekt.transformationengine.core;

import java.util.Iterator;

import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;
import gr.ekt.transformationengine.exceptions.UnsupportedComparatorMode;
import gr.ekt.transformationengine.exceptions.UnsupportedCriterion;

public class ConjunctionTransformationWorkflow extends TransformationWorkflow {
    
	public ConjunctionTransformationWorkflow(){
		super();
	}

    /**
     * The implementation of filtering process for the ConjuctionClassifier
     *
     * @param recordSet
     *  The RecordSet to be classified 
     *
     * @return the result RecordSet 
     */
    public RecordSet process(RecordSet recordSet) throws UnimplementedAbstractMethod, UnsupportedComparatorMode,UnsupportedCriterion {
        
        logger.info("filter started");

        RecordSet resultRecordSet = recordSet; //initially the result is the same record set
        
        //-- check for errors 
        if(steps == null || steps.size() == 0){
            return recordSet;
        }
        
        //== give the record set to each filter and get the result set ==
        Iterator<ProcessingStep> it = steps.iterator();
        while(it.hasNext()){
 			
            ProcessingStep currentStep =  it.next();
            
            //Initialize filter
            currentStep.initialize();
            
            //== give the record set to the filter
            resultRecordSet = currentStep.process(resultRecordSet);
            
            System.out.println("Size of result set = " + resultRecordSet.getRecords().size());
        }
        
        logger.info("filter finished");
        
        return resultRecordSet;
    }
    
    public void initialize() {}
    
    
    
}
