package mcip.webapps.controller.mainPopup;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import mcip.webapps.dto.mainPopup.MainPopupDTO;
import mcip.webapps.dto.mainPopup.MainPopupEntity;
import mcip.webapps.dto.msg.MsgDTO;
import mcip.webapps.service.mainPopup.MainPopupService;

/**
 * 팝업 controller
 * @author 추연상
 *
 */
@Slf4j
@Controller
@RequestMapping("/mainPopup")
public class MainPopupController {
	
	@Autowired MainPopupService mainPopupService;
	
	@GetMapping("/popMgmt.do")
	public String popMgmt(Model model) {
		log.debug("#########/mainPopup/popMgmt.do Start##########");		
		model.addAttribute("topMenu", "stats");
		model.addAttribute("leftMenu", "userLogin");		
		log.debug("#########/mainPopup/popMgmt.do End##########");
		
		return "mainPopup/popMgmt.tiles";
	}
	
	@ResponseBody
	@PostMapping("/getPopMgmtListAjax.do")
	public HashMap getPopMgmtListAjax(MainPopupEntity mainPopup) {
		log.debug("#########/mainPopup/getPopMgmtListAjax.do Start##########");				
		int popMgmtListCnt = mainPopupService.getPopMgmtListCnt(mainPopup);
		List<MainPopupDTO> popMgmtList = mainPopupService.getPopMgmtList(mainPopup);
		
		HashMap rMap = new HashMap();		
		rMap.put("popMgmtListCnt", popMgmtListCnt);
		rMap.put("popMgmtList", popMgmtList);
		log.debug("#########/mainPopup/getPopMgmtListAjax.do End##########");
		
		return rMap;		
	}
	
	@GetMapping("/popRegist.do")
	public String popRegist(Model model) {
		log.debug("#########/mainPopup/popRegist.do Start##########");		
		model.addAttribute("topMenu", "stats");
		model.addAttribute("leftMenu", "userLogin");		
		log.debug("#########/mainPopup/popRegist.do End##########");
		
		return "mainPopup/popRegist.tiles";
	}
	
	@PostMapping("/popDetail.do")
	public String popDetail(Model model, MainPopupEntity mainPopup) {
		log.debug("#########/mainPopup/popDetail.do Start##########");		
		model.addAttribute("topMenu", "stats");
		model.addAttribute("leftMenu", "userLogin");

		MainPopupDTO popMgmt = mainPopupService.getPopMgmtDetail(mainPopup);		
		model.addAttribute("popMgmt", popMgmt);
		log.debug("#########/mainPopup/popDetail.do End##########");
		
		return "mainPopup/popDetail.tiles";
	}
	
	@ResponseBody
	@PostMapping("/popRegistAjax.do")
	public HashMap popRegistAjax(MainPopupEntity mainPopup, HttpServletRequest request) {
		log.debug("#########/mainPopup/popRegistAjax.do Start##########");		
		MsgDTO result = mainPopupService.insPopMgmt(mainPopup, request);		
				
		HashMap rMap = new HashMap();
		rMap.put("result", result);		
		log.debug("#########/mainPopup/popRegistAjax.do End##########");
		
		return rMap;
	}
	
	@PostMapping("/popModify.do")
	public String popModify(Model model, MainPopupEntity mainPopup) {
		log.debug("#########/mainPopup/popDetail.do Start##########");
		model.addAttribute("topMenu", "stats");
		model.addAttribute("leftMenu", "userLogin");
		
		MainPopupDTO popMgmt = mainPopupService.getPopMgmtDetail(mainPopup);		
		model.addAttribute("popMgmt", popMgmt);
		log.debug("#########/mainPopup/popDetail.do End##########");
		
		return "mainPopup/popModify.tiles";
	}
		
	@ResponseBody
	@PostMapping("/popModifyAjax.do")
	public HashMap popModifyAjax(MainPopupEntity mainPopup, HttpServletRequest request) {
		log.debug("#########/mainPopup/popModifyAjax.do Start##########");		
		MsgDTO result = mainPopupService.updPopMgmt(mainPopup, request);
				
		HashMap rMap = new HashMap();
		rMap.put("result", result);
		log.debug("#########/mainPopup/popModifyAjax.do End##########");
		
		return rMap;
	}	
}
