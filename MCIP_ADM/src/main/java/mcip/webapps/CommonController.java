package mcip.webapps;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class CommonController {
	
	@RequestMapping("/favicon.ico") public String favIconForward(){ return "forward:/resources/favicon.ico"; }
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping(value = {"/",""})
	public String home(HttpSession session, Model model) {
		log.debug("========================== NO URL ==============================");
		return "/index.jsp";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/workList.do")
	public String workList(HttpSession session, Model model) {
		log.debug("========================== workList ==============================");
		return "pub/layout.title";
	}
}
