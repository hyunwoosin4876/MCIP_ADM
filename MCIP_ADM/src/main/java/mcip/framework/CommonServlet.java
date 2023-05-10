package mcip.framework;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.web.servlet.DispatcherServlet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonServlet extends DispatcherServlet{

	private static final long serialVersionUID = -207970589095320128L;
	
	public CommonServlet() {
		super();
	}
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);
			//기본 설정 호출
			initConfig();
		} catch (Exception e) {
			log.error("Can't initialize Spring CommonServlet ", e);
			throw new ServletException(e);
		}
	}
	private void initConfig() {
		// SiteInfoService siteInfo = (SiteInfoService) getWebApplicationContext().getBean("siteInfoService");
		// siteInfo.initConfig();
	}
}

