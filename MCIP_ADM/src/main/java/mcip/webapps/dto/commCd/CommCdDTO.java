package mcip.webapps.dto.commCd;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mcip.webapps.dto.DefaultDTO;

/**
 * 게시판 DTO(Respons)
 * @author 이상민
 *
 */
@Setter
@Getter
@ToString
public class CommCdDTO extends DefaultDTO {	 
	private static final long serialVersionUID = 7026908564169172889L;
	
	private int commCdSeq = 0;
	private String commCd = "";
	private String cdNm = "";
	private String parentCd = "";
	private String parentCdNm = "";
	private String cdOrder = "";
	private String cdUseYn = "";
}