package mcip.webapps.dto.mainPopup;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mcip.webapps.dto.DefaultDTO;

/**
 * 팝업 Entity(Request)
 * @author 추연상
 *
 */
@Setter
@Getter
@ToString
public class MainPopupEntity extends DefaultDTO {
	
	private static final long serialVersionUID = 7269239515211163162L;
	
	private int popSeq = 0;
	private String popTitle = "";
	private String popUseYn = "";	
	private String popSizeWidth = "";
	private String popSizeHeight = "";
	private String popLocationX = "";
	private String popLocationY = "";	
	private String popUseStartDate = "";
	private String popUseEndDate = "";	
	private String popLinkType = "";
	private String popLinkUrl = "";
	private String popContent = "";
	private String popUldFileNm = "";
	private String popOrgnlFileNm = "";
	private int popFileSize = 0;
	private String popFilePath = "";
	
	/* 페이징 */
	private String pageLimit = "";
	private String pageOffset = "";
	
	/* 이미지 수정 여부 */
	private String updImgFileYn = "";
}