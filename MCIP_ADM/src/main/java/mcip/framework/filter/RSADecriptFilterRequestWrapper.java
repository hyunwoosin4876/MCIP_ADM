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
package mcip.framework.filter;

import java.security.PrivateKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mcip.egovframe.util.EgovStringUtil;
import mcip.framework.util.enc.DecModule;

public class RSADecriptFilterRequestWrapper extends HttpServletRequestWrapper {

	private static final Logger logger = LoggerFactory.getLogger(RSADecriptFilterRequestWrapper.class);

	HttpSession session;

	public RSADecriptFilterRequestWrapper(HttpServletRequest request) {
		super(request);
		this.session = request.getSession();
	}
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if(values==null){
			return null;			
		}else{
			if(parameter.startsWith("enc")){
				String newName = parameter.replaceAll("enc", "");
				newName = newName.substring(0, 1).toLowerCase() + newName.substring(1, newName.length());
				PrivateKey privateKey = (PrivateKey)session.getAttribute("privateKey");
				if(privateKey != null){
					for (int i = 0; i < values.length; i++) {
						if (values[i] != null) {
							String decriptData = new DecModule().rsaDecript(values[i], privateKey);
							logger.debug(newName + " : " +decriptData);
							values[i] = decriptData;
						}else{
						}
					}
					
				}
			}
		}
		return values;
}

	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if(value==null){
			return null;			
		}else{
			if(parameter.startsWith("enc")){
				String newName = parameter.replaceAll("enc", "");
				newName = newName.substring(0, 1).toLowerCase() + newName.substring(1, newName.length());
				PrivateKey privateKey = (PrivateKey)session.getAttribute("privateKey");
				if(privateKey != null){
					String decriptData = new DecModule().rsaDecript(super.getParameter(parameter), privateKey);
					if(EgovStringUtil.isEmpty(decriptData)){
					}else{
						value = decriptData;
						logger.debug(newName + " : " +decriptData);
					}
				}else{
				}
			}
		}
		return value;
	}
}