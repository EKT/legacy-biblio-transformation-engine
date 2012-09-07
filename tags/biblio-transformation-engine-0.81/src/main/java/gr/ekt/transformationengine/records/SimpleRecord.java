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

/**
 * 
 */
package gr.ekt.transformationengine.records;

import java.util.ArrayList;
import java.util.List;

import gr.ekt.transformationengine.core.Record;

/**
 * @author kstamatis
 *
 */
public abstract class SimpleRecord extends Record {

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#getByName(java.lang.String)
	 */
	@Override
	public abstract List<Object> getByName(String elementName);

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#printByName(java.lang.String)
	 */
	@Override
	public abstract  void printByName(String elementName);

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#removeField(java.lang.String)
	 */
	@Override
	public abstract void removeField(String fieldName);

	/*
	 * (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#removeValueFromField(java.lang.String, java.lang.String)
	 */
	@Override
	public abstract void removeValueFromField(String fieldName, Object value);
	
	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#addField(java.lang.String, java.util.ArrayList)
	 */
	@Override
	public void addField(String fieldName, ArrayList<Object> fieldValues) {
		for (Object s : fieldValues)
			addValueToField(fieldName, s);
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Record#addFieldValue(java.lang.String, java.lang.String)
	 */
	@Override
	public abstract void addValueToField(String fieldName, Object fieldValue);
	
}
