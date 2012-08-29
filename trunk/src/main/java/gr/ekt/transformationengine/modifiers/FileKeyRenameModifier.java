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

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaxen.function.NameFunction;

/**
 * @author Kostas Stamatis
 * @author National Documentation Center
 *
 */
public class FileKeyRenameModifier extends KeyRenameModifier {

	String filename;

	/**
	 * 
	 */
	public FileKeyRenameModifier() {
		// TODO Auto-generated constructor stub
		super();
	}

	/* (non-Javadoc)
	 * @see gr.ekt.transformationengine.modifiers.KeyRenameModifier#loadMapping()
	 */
	@Override
	public void loadMapping() {

		//Read the mapping from the filename
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(filename));
			List maps = document.getRootElement().elements("map");
			for (Object mapObject : maps){
				Element mapElement = (Element)mapObject;
				String nameBefore = mapElement.element("key_before").getText();
				String nameAfter = mapElement.element("key_after").getText();
				if (nameAfter!=null && nameBefore!=null){
					keyMapping.put(nameBefore, nameAfter);
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
		this.loadMapping();
	}	
}
