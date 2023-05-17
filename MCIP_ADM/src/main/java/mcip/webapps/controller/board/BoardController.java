package mcip.webapps.controller.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import mcip.webapps.dto.board.BoardDTO;
import mcip.webapps.dto.board.BoardEntity;
import mcip.webapps.dto.msg.MsgDTO;
import mcip.webapps.service.board.BoardService;


/**
 * 게시판 controller
 * @author 신현우
 *
 */
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired BoardService boardService;
	
	@GetMapping("/list.do")
	public String list(Model model, BoardEntity board) {
		log.debug("loginLogList page Start");
		model.addAttribute("topMenu", "stats");
		model.addAttribute("leftMenu", "userLogin");
		
		int boardCnt = boardService.getBoardCnt(board);
		List<BoardDTO> boardList = boardService.getBoardList(board);
		
		model.addAttribute("boardTotCnt", boardCnt);
		model.addAttribute("boardList", boardList);
		
		return "board/boardList.tiles";
	}
	
	@GetMapping("/detail.do")
	public String detail(Model model, BoardEntity board) {
		log.debug("loginLogList page Start");
		model.addAttribute("topMenu", "stats");
		model.addAttribute("leftMenu", "userLogin");
		
		int boardCnt = boardService.getBoardCnt(board);
		List<BoardDTO> boardList = boardService.getBoardList(board);
		
		model.addAttribute("boardTotCnt", boardCnt);
		model.addAttribute("boardList", boardList);
		
		return "board/boardList.tiles";
	}
	
	@GetMapping("/insert.do")
	public String insert(Model model, BoardEntity board) {
		log.debug("loginLogList page Start");
		model.addAttribute("topMenu", "stats");
		model.addAttribute("leftMenu", "userLogin");
		
		int boardCnt = boardService.getBoardCnt(board);
		List<BoardDTO> boardList = boardService.getBoardList(board);
		
		model.addAttribute("boardTotCnt", boardCnt);
		model.addAttribute("boardList", boardList);
		
		return "board/boardList.tiles";
	}
	
	@PostMapping("/insertAjax.do")
	public MsgDTO insertAjax(Model model, BoardEntity board) {
		log.debug("loginLogList page Start");
		
		MsgDTO result = new MsgDTO();		
		result = boardService.insBoard(board);
		
		return result;
	}
	
	@GetMapping("/updateAjax.do")
	public MsgDTO updateAjax(Model model, BoardEntity board) {
		log.debug("loginLogList page Start");
		
		MsgDTO result = new MsgDTO();		
		result = boardService.insBoard(board);
		
		return result;
		
	}
}
