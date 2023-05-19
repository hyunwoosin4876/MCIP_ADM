package mcip.webapps.dao.commCd;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import mcip.webapps.dto.commCd.CommCdDTO;
import mcip.webapps.dto.commCd.CommCdEntity;
import mcip.webapps.dto.msg.MsgDTO;

/**
 * 공통코드 DAO
 * @author 이상민
 *
 */
@Mapper("commCdDAO")
public interface CommCdDAO {

	/**
	 * 리스트
	 * @param commCdEntity
	 * @return
	 */
	public List<CommCdDTO> getUpCommCdList(CommCdEntity commCd);
	
	
	/**
	 * 리스트
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
	 * 공통코드 중복체크
	 * @param commCdEntity
	 * @return
	 */
	public int getDupCnt(CommCdEntity commCd);
	
	/**
	 * 공통코드 등록
	 * @param commCdEntity
	 * @return
	 */
	public int insCommCd(CommCdEntity commCd);
	
	/**
	 * 공통코드 변경
	 * @param commCdEntity
	 * @return
	 */
	public int updCommCd(CommCdEntity commCd);
}
