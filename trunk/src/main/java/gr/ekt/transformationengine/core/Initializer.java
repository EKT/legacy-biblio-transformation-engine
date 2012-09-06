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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * Initializer
 *
 * Version: 1
 *
 * Date: $Date: 2007-9-21 (Fri, 21 Sep 2007) $
 *
 * This interface defines the functionality that an actual Initializer must has
 *
 */
public abstract class Initializer {
   
    private Map<String, String> initParams = new HashMap<String, String>();
	
   /**
     * 
     */
    public Initializer() {
    }

    /*
     * @return the target name of this initializer may be a file name or a database name
     */
    public abstract String getTargetName();
    
    /**
     * @param initParams
     */
    public Initializer(Map<String, String> initParams) {
            this.initParams = initParams;
    }

    /*
     * This method initializes the Classifier and returns a List of objects
     */
    public abstract List initialize();

    /**
     * @return the initParams
     */
    public Map<String, String> getInitParams() {
            return initParams;
    }

    /**
     * @param initParams the initParams to set
     */
    public void setInitParams(Map<String, String> initParams) {

            this.initParams = initParams;
    }
}
