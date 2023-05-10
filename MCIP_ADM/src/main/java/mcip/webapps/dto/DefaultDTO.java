package mcip.webapps.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 */
@Setter
@Getter
@ToString
public class DefaultDTO implements Serializable {
	
	private static final long serialVersionUID = 297412998341549023L;
	
	private int totalCount;					// 총 갯수
	private int pageCount;					// 페이지당 조회 개수
	private int pageNumber;					// 페이지번호(1부터 시작)드
	private int startCnt, endCnt;
	private String pagingYn = null;			//페이지 유무	
	/**
	 * 등록자
	 */
	private String regId = null;
	
	/**
	 * 등록일
	 */
	private Date regDt;
	/**
	 * 수정자
	 */
	private String updId = null;
	
	/**
	 * 수정일
	 */
	private Date updDt;
	
	public DefaultDTO(){
		pageCount = 10;
		pageNumber = 1;
		endCnt = pageCount;
		pagingYn = "Y";
	}
	
	public int getStartCnt() {
		startCnt = (((pageNumber-1) * pageCount));
		return startCnt;
	}
	
	public int getEndCnt() {
		endCnt = pageCount;
		return endCnt;
	}
}

