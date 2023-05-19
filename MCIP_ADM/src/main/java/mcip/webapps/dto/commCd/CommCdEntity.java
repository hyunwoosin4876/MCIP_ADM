package mcip.webapps.dto.commCd;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mcip.webapps.dto.DefaultDTO;

/**
 * 게시판 DTO(Request)
 * @author 이상민
 *
 */
@Setter
@Getter
@ToString
public class CommCdEntity extends DefaultDTO {

	private static final long serialVersionUID = 7269239515211163162L;
	
	private String commCd = "";
	private String cdNm = "";
	private String parentCd = "";
	private String parentCdNm = "";
	private String cdOrder = "";
	private String cdUseYn = "";
	
	private String commCdM = "";
	private String cdNmM = "";
	private String parentCdM = "";
	private String parentCdNmM = "";
	private String cdOrderM = "";
	private String cdUseYnM = "";
	
}