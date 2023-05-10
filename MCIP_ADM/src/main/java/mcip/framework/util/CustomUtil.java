/**
 * 
 */
package mcip.framework.util;

import java.net.UnknownHostException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUtil.class);

	public String objectToJson(Object object){
		ObjectMapper map = new ObjectMapper();
		String json = null;
		try {
			json = map.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			LOGGER.debug(e.toString());
		}
		
		return json;
	}

	public String getRandomAuthKey(int length) {
		String result = "";
		Random rd = new Random();
		for (int i = 0; i < length; i++) {
			result += String.valueOf(rd.nextInt(10));
		}
		LOGGER.debug("random Key : {}", result);
		return result;
	}
	/**
	 * 세션에서 값 확인하여 세션 타입에 맞추어 전달
	 * @param type
	 * @return
	 */
	public String getSessionData(String type){
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
		return String.valueOf(session.getAttribute(type));
	}
	
	/**
	 * <pre>
	 * 클라이언트 IP 가져오기
	 * </pre>
	 * @Class Name : CommonUtil
	 * @Method Name : getUserIp
	 * @return
	 * @throws UnknownHostException
	 */
	public String getUserIp() throws UnknownHostException {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0) ip = req.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0) ip = req.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0) ip = req.getRemoteAddr();
        return ip;
	}
}
