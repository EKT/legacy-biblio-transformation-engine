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
package gr.ekt.transformationengine.modifiers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gr.ekt.transformationengine.core.Modifier;
import gr.ekt.transformationengine.core.Record;
import gr.ekt.transformationengine.exceptions.UnimplementedAbstractMethod;

/**
 * @author kstamatis
 *
 */
public abstract class KeyRenameModifier extends Modifier {

	Map<String, String> keyMapping = new HashMap<String, String>();

	/**
	 * 
	 */
	public KeyRenameModifier() {
		// TODO Auto-generated constructor stub	
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.core.Modifier#modify(gr.ekt.transformationengine.core.Record)
	 */
	@Override
	public void modify(Record record) throws UnimplementedAbstractMethod {
		// TODO Auto-generated method stub

		for (String key : keyMapping.keySet()){
			List<Object> values = record.getByName(key);
			ArrayList<Object> values2 = new ArrayList<Object>();
			for (Object s : values){
				values2.add(s);
			}
			if (values!=null){
				record.removeField(key);
				record.addField(keyMapping.get(key), values2);
			}
		}
	}

	public Map<String, String> getKeyMapping() {
		return keyMapping;
	}

	public void setKeyMapping(Map<String, String> keyMapping) {
		this.keyMapping = keyMapping;
	}

	//Abstract method to load the mapping
	public abstract void loadMapping();
}
