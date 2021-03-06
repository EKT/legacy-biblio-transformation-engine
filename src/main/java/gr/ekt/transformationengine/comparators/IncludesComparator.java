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
 * EqualsComparator.java
 *
 * Created on October 1, 2007, 2:18 PM
 *
 */

package gr.ekt.transformationengine.comparators;

import gr.ekt.transformationengine.core.Comparator;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

public class IncludesComparator implements Comparator{
    
    // Define a static logger variable
    static Logger logger = Logger.getLogger(IncludesComparator.class);
    
    /**
     * Creates a new instance of EqualsComparator
     */
    public IncludesComparator() {
    }
    
    public  boolean compare(List valuesList, String recordValue){
    
        //-- check for error parameters
        if( (recordValue != null) && (valuesList != null) ){
            recordValue = recordValue.trim();
            
        }else 
            return false;
        
        Iterator it = valuesList.iterator();
         
         //== For each element in valuesList ==
         while(it.hasNext()){
             
             String currentValue = (String) it.next();
             
             //== check if the one string contains the other ==
             if((  (currentValue.indexOf(recordValue)) != -1 ) || ((recordValue.indexOf(currentValue)) != -1) ){
                 return true;
             }
         }
         
         //== if there were not a match
         return false;
    }
}
