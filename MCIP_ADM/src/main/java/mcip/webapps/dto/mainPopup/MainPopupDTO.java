package mcip.webapps.dto.mainPopup;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mcip.webapps.dto.DefaultDTO;

/**
 * 팝업 DTO(Response)
 * @author 추연상
 *
 */
@Setter
@Getter
@ToString
public class MainPopupDTO extends DefaultDTO {
	
	private static final long serialVersionUID = 7026908564169172889L;
		
	private int popSeq = 0;
	private String popTitle = "";
	private String popUseYn = "";
	private int popSizeWidth = 0;
	private int popSizeHeight = 0;
	private int popLocationX = 0;
	private int popLocationY = 0;
	private String popUseStartDate = "";
	private String popUseEndDate = "";
	private String popLinkType = "";
	private String popLinkUrl = "";
	private String popContent = "";
	private String popUldFileNm = "";
	private String popOrgnlFileNm = "";
	private int popFileSize = 0;
	private String popFilePath = "";
	private String imgFileYn = "";
}