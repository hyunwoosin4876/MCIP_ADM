package mcip.webapps.service.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcip.webapps.dao.board.BoardDAO;
import mcip.webapps.dto.board.BoardDTO;
import mcip.webapps.dto.board.BoardEntity;
import mcip.webapps.dto.msg.MsgDTO;
import mcip.webapps.service.board.BoardService;
import mcip.webapps.service.common.CommonService;

/**
 * 게시판 ServiceImpl
 * @author 신현우
 *
 */
@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired CommonService commonService;
	@Autowired BoardDAO boardDAO;
		
	@Override
	public int getBoardCnt(BoardEntity board) {
		return boardDAO.getBoardCnt(board);
	}

	@Override
	public List<BoardDTO> getBoardList(BoardEntity board) {
		return boardDAO.getBoardList(board);
	}

	@Override
	public BoardDTO getBoardDetail(BoardEntity board) {
		return boardDAO.getBoardDetail(board);
	}

	@Override
	public MsgDTO insBoard(BoardEntity board) {
		MsgDTO result = new MsgDTO();
		if(boardDAO.insBoard(board) > 0) {
			result = commonService.getCommonMent(true);
		} else {
			result = commonService.getCommonMent(false);
		}
		
		return result;
	}

	@Override
	public MsgDTO updBoard(BoardEntity board) {
		MsgDTO result = new MsgDTO();
		if(boardDAO.updBoard(board) > 0) {
			result = commonService.getCommonMent(true);
		} else {
			result = commonService.getCommonMent(false);
		}
		
		return result;
	}

	@Override
	public MsgDTO updBoardUseYn(BoardEntity board) {
		MsgDTO result = new MsgDTO();
		if(boardDAO.updBoardUseYn(board) > 0) {
			result = commonService.getCommonMent(true);
		} else {
			result = commonService.getCommonMent(false);
		}
		return result;
	}

	@Override
	public MsgDTO delBoard(BoardEntity board) {
		MsgDTO result = new MsgDTO();
		if(boardDAO.delBoard(board) > 0) {
			result = commonService.getCommonMent(true);
		} else {
			result = commonService.getCommonMent(false);
		}
		return result;
	}
	
}
