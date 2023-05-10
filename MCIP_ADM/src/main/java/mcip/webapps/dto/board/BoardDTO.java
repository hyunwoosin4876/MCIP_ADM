package mcip.webapps.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mcip.webapps.dto.DefaultDTO;

/**
 * 게시판 DTO(Respons)
 * @author 신현우
 *
 */
@Setter
@Getter
@ToString
public class BoardDTO extends DefaultDTO {	 
	private static final long serialVersionUID = 7026908564169172889L;
	
	private int boardSeq = 0;  // 게시판 일련번호
	private String boardTitle; 
	private String boardContent;
	private String boardUseYn;
	private int comCodeSeq;
	private String comCode;
}