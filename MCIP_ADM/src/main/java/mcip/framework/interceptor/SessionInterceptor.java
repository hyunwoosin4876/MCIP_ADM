package mcip.framework.interceptor;

import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;
import mcip.webapps.dto.msg.MsgDTO;

@Slf4j
public class SessionInterceptor extends HandlerInterceptorAdapter {
	@Resource(name = "applProperties")
	private Properties applProperties;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession(false);
		MsgDTO result = new MsgDTO();
		
		return true;
	}
}