package mcip.webapps.service.commCd.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcip.webapps.dao.commCd.CommCdDAO;
import mcip.webapps.dto.commCd.CommCdDTO;
import mcip.webapps.dto.commCd.CommCdEntity;
import mcip.webapps.dto.msg.MsgDTO;
import mcip.webapps.service.commCd.CommCdService;
import mcip.webapps.service.common.CommonService;

/**
 * 게시판 ServiceImpl
 * @author 이상민
 *
 */
@Service("commCdService")
public class CommCdServiceImpl implements CommCdService {

	@Autowired CommonService commonService;
	@Autowired CommCdDAO commCdDAO;
	
	/**
	 * 상위코드 리스트
	 * @param commCdEntity
	 * @return
	 */
	@Override
	public List<CommCdDTO> getUpCommCdList(CommCdEntity commCd) {
		return commCdDAO.getUpCommCdList(commCd);
	}

	/**
	 * 하위코드 리스트
	 * @param commCdEntity
	 * @return
	 */
	@Override
	public List<CommCdDTO> getDwCommCdList(CommCdEntity commCd) {
		return commCdDAO.getDwCommCdList(commCd);
	}
	
	/**
	 * 공통코드 상세
	 * @param commCdEntity
	 * @return
	 */
	public CommCdDTO getCommCdDetail(CommCdEntity commCd) {
		return commCdDAO.getCommCdDetail(commCd);
	}
	/**
	 * 공통코드 등록
	 * @param commCdEntity
	 * @return
	 */
	public MsgDTO insCommCd(CommCdEntity commCd) {
		MsgDTO result = new MsgDTO();
		commCd.setRegId("ADMIN"); 
		int dupCnt = commCdDAO.getDupCnt(commCd);
		
		if(dupCnt < 1) {
			if(commCdDAO.insCommCd(commCd) > 0) {
				//result = commonService.getCommonMent(true);
				result.setMsgCode("0000");
			} else {
				result.setMsgCode("9999");
			}
			
		} else {
			result.setMsgCode("9999");
			result.setMsgMent("코드가 중복됩니다.");
			
		}
		
		return result;
	}
	/**
	 * 공통코드 변경
	 * @param commCdEntity
	 * @return
	 */
	public MsgDTO updCommCd(CommCdEntity commCd) {
		MsgDTO result = new MsgDTO();
		
		commCd.setUpdId("ADMIN");
		
		if(commCdDAO.updCommCd(commCd) > 0) {
			//result = commonService.getCommonMent(true);
			result.setMsgCode("0000");
		} else {
			result.setMsgCode("9999");
		}
			
		return result;
	}
}
