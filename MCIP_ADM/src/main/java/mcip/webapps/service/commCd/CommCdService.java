package mcip.webapps.service.commCd;

import java.util.List;

import mcip.webapps.dto.commCd.CommCdDTO;
import mcip.webapps.dto.commCd.CommCdEntity;
import mcip.webapps.dto.msg.MsgDTO;

/**
 * 공통코드 Service
 * @author 이상민
 *
 */
public interface CommCdService {

	/**
	 * 상위코드 리스트
	 * @param commCdEntity
	 * @return
	 */
	public List<CommCdDTO> getUpCommCdList(CommCdEntity commCd);
	
	/**
	 * 하위코드 리스트
	 * @param commCdEntity
	 * @return
	 */
	public List<CommCdDTO> getDwCommCdList(CommCdEntity commCd);
	
	/**
	 * 공통코드 상세
	 * @param commCdEntity
	 * @return
	 */
	public CommCdDTO getCommCdDetail(CommCdEntity commCd);
	
	/**
	 * 공통코드 등록
	 * @param commCdEntity
	 * @return
	 */
	public MsgDTO insCommCd(CommCdEntity commCd);
	
	/**
	 * 공통코드 변경
	 * @param commCdEntity
	 * @return
	 */
	public MsgDTO updCommCd(CommCdEntity commCd);
	
	

	
	
	
	
}
