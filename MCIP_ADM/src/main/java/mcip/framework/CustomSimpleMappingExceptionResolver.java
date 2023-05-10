package mcip.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	 
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		logger.info("CustomSimpleMappingExceptionResolverrequest::" + request.getHeader("Accept"));

		logger.error("CustomSimpleMappingExceptionResolver: " + ex);

		String str = "";
		
		if(request.getHeader("Accept") != null){
			str = request.getHeader("Accept").toLowerCase();
		}

		if (str.indexOf("json") > -1) {

			String viewName = determineViewName(ex, request);
			Integer statusCode = determineStatusCode(request, viewName);
			if(statusCode != null){
				applyStatusCodeIfPossible(request, response, statusCode);
			}
			ModelAndView mv = getModelAndView(viewName, ex, request);
			return mv;
		} else {
			return super.doResolveException(request, response, handler, ex);
		}
	}
}

