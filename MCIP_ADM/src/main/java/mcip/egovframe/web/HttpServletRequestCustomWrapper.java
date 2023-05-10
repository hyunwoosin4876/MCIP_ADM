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
package mcip.egovframe.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpServletRequestCustomWrapper extends HttpServletRequestWrapper {
	private Map<String, Object> request;
	@SuppressWarnings("unchecked")
	public HttpServletRequestCustomWrapper(HttpServletRequest request) {
		super(request);
		this.request = request.getParameterMap();
	}
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if(values==null){
			return null;			
		}
		return values;
	}
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if(value==null){
			return null;
		}
		return value;
	}
	public void setParameter(String name, String value) {

        String[] oneParam = {value};
        setParameter(name, oneParam);

    }
    public void setParameter(String name, String[] values) {
        this.request.put(name, values);
    }
}