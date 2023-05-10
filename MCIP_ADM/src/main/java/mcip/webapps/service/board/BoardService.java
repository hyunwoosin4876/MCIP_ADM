package mcip.webapps.service.board;

import java.util.List;

import mcip.webapps.dto.board.BoardDTO;
import mcip.webapps.dto.board.BoardEntity;
import mcip.webapps.dto.msg.MsgDTO;

/**
 * 게시판 Service
 * @author 신현우
 *
 */
public interface BoardService {

	/**
	 * 전체 갯수
	 * @param boardEntity
	 * @return
	 */
	public int getBoardCnt(BoardEntity board);
	
	/**
	 * 리스트
	 * @param boardEntity
	 * @return
	 */
	public List<BoardDTO> getBoardList(BoardEntity board);
	
	/**
	 * 리스트
	 * @param boardEntity
	 * @return
	 */
	public BoardDTO getBoardDetail(BoardEntity board);
	
	/**
	 * 리스트
	 * @param boardEntity
	 * @return
	 */
	public MsgDTO insBoard(BoardEntity board);
	
	/**
	 * 리스트
	 * @param boardEntity
	 * @return
	 */
	public MsgDTO updBoard(BoardEntity board);
	
	/**
	 * 리스트
	 * @param boardEntity
	 * @return
	 */
	public MsgDTO updBoardUseYn(BoardEntity board);

	/**
	 * 리스트
	 * @param boardEntity
	 * @return
	 */
	public MsgDTO delBoard(BoardEntity board);
}
