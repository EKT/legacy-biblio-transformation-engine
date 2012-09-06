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
import java.util.Map;

/**
 * @author kstamatis
 *
 */
public class MapDSpaceRecord extends MapRecord {

	String handle;
	String handlePrefix;
	ArrayList<String> metadataPrefixes = new ArrayList<String>();
	
	/**
	 * @param recordValue
	 */
	public MapDSpaceRecord(Map<String, List<Object>> recordValue) {
		super(recordValue);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public MapDSpaceRecord() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the handle
	 */
	public String getHandle() {
		return handle;
	}

	/**
	 * @param handle the handle to set
	 */
	public void setHandle(String handle) {
		this.handle = handle;
	}

	/**
	 * @return the metadataPrefixes
	 */
	public ArrayList<String> getMetadataPrefixes() {
		return metadataPrefixes;
	}

	/**
	 * @param metadataPrefixes the metadataPrefixes to set
	 */
	public void setMetadataPrefixes(ArrayList<String> metadataPrefixes) {
		this.metadataPrefixes = metadataPrefixes;
	}

    public void addMetadataPrefix(String prefix){
    	this.metadataPrefixes.add(prefix);
    }

	/**
	 * @return the handlePrefix
	 */
	public String getHandlePrefix() {
		return handlePrefix;
	}

	/**
	 * @param handlePrefix the handlePrefix to set
	 */
	public void setHandlePrefix(String handlePrefix) {
		this.handlePrefix = handlePrefix;
	}
}
