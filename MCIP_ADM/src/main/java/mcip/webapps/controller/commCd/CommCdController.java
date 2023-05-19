package mcip.webapps.controller.commCd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import mcip.webapps.dto.commCd.CommCdDTO;
import mcip.webapps.dto.commCd.CommCdEntity;
import mcip.webapps.dto.msg.MsgDTO;
import mcip.webapps.service.board.BoardService;
import mcip.webapps.service.commCd.CommCdService;


/**
 * 공통코드 controller
 * @author 이상민
 *
 */
@Slf4j
@Controller
@RequestMapping("/commCd")
public class CommCdController {
	
	@Autowired CommCdService commCdService;
	
	@GetMapping("/commCdMgmt.do")
	public String commCdMgmt(Model model, CommCdEntity commCd) {
		log.debug("commCdMgmt controller Start");
		
		log.debug("commCdMgmt controller end");
		
		return "commCd/commCdMgmt.tiles";
	}
	
	@ResponseBody
	@PostMapping("/upCommCdListAjax.do")
	public HashMap getUpCommCdList(Model model, CommCdEntity commCd) {
		log.debug("getUpCommCdList controller Start");
		HashMap rMap = new HashMap();
		
		List<CommCdDTO> commCdList = commCdService.getUpCommCdList(commCd);
		
		rMap.put("commCdList", commCdList);
		rMap.put("commCd", "AAAAAAAAAAA");
		
		log.debug("getUpCommCdList controller end");
		
		return rMap;
	}
	
	@ResponseBody
	@PostMapping("/dwCommCdListAjax.do")
	public HashMap getDwCommCdList(Model model, CommCdEntity commCd) {
		log.debug("getDwCommCdList controller Start");
		HashMap rMap = new HashMap();

		List<CommCdDTO> commCdList = commCdService.getDwCommCdList(commCd);
		
		rMap.put("commCdList", commCdList);
		rMap.put("commCd", "AAAAAAAAAAA");
		
		log.debug("getDwCommCdList controller end");
		
		return rMap;
	}
	
	@ResponseBody
	@PostMapping("/getCommCdDetailAjax.do")
	public HashMap getCommCdDetailAjax(Model model, CommCdEntity commCd) {
		log.debug("getCommCdDetailAjax controller Start");
		HashMap rMap = new HashMap();

		CommCdDTO commCdDetail = commCdService.getCommCdDetail(commCd);
		
		rMap.put("commCdDetail", commCdDetail);
		
		log.debug("getCommCdDetailAjax controller end");
		
		return rMap;
	}
	
	@ResponseBody
	@PostMapping("/insCommCdAjax.do")
	public HashMap insCommCdAjax(Model model, CommCdEntity commCd) {
		log.debug("insCommCdAjax controller Start");
		HashMap rMap = new HashMap();
		MsgDTO result = new MsgDTO();		
		
		result = commCdService.insCommCd(commCd);
		
		rMap.put("result", result);
		
		log.debug("insCommCdAjax controller end");
		
		return rMap;
	}
	
	@ResponseBody
	@PostMapping("/updCommCdAjax.do")
	public HashMap updCommCdAjax(Model model, CommCdEntity commCd) {
		log.debug("updCommCdAjax controller Start");
		HashMap rMap = new HashMap();
		MsgDTO result = new MsgDTO();		
		
		result = commCdService.updCommCd(commCd);
		
		rMap.put("result", result);
		
		log.debug("updCommCdAjax controller end");
		
		return rMap;
	}
	
}
