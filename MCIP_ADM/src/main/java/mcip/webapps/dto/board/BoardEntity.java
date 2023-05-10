package mcip.webapps.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mcip.webapps.dto.DefaultDTO;

/**
 * 게시판 DTO(Request)
 * @author 신현우
 *
 */
@Setter
@Getter
@ToString
public class BoardEntity extends DefaultDTO {

	private static final long serialVersionUID = 7269239515211163162L;
	
	private int boardSeq = 0; 
	
}