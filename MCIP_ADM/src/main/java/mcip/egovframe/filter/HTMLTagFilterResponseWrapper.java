/*
 * Copyright 2008-2009 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mcip.egovframe.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class HTMLTagFilterResponseWrapper extends HttpServletResponseWrapper {
    protected CharArrayWriter charWriter;

	public HTMLTagFilterResponseWrapper(HttpServletResponse response) {
		super(response);
		charWriter = new CharArrayWriter();
	}
	
	@Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(charWriter);
    }
	@Override public String toString() { 
		return charWriter.toString(); 
	}

}