package mcip.webapps.dao.board;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import mcip.webapps.dto.board.BoardDTO;
import mcip.webapps.dto.board.BoardEntity;

/**
 * 게시판 DAO
 * @author 신현우
 *
 */
@Mapper("boardDAO")
public interface BoardDAO {

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
	public int insBoard(BoardEntity board);
	
	/**
	 * 리스트
	 * @param boardEntity
	 * @return
	 */
	public int updBoard(BoardEntity board);
	
	/**
	 * 리스트
	 * @param boardEntity
	 * @return
	 */
	public int updBoardUseYn(BoardEntity board);

	/**
	 * 리스트
	 * @param boardEntity
	 * @return
	 */
	public int delBoard(BoardEntity board);
}
