package mcip.webapps.dao.mainPopup;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import mcip.webapps.dto.mainPopup.MainPopupDTO;
import mcip.webapps.dto.mainPopup.MainPopupEntity;

/**
 * 팝업 DAO
 * @author 추연상
 *
 */
@Mapper("mainPopupDAO")
public interface MainPopupDAO {

	/**
	 * 리스트 조회 개수
	 * @param mainPopup
	 * @return
	 */
	public int getPopMgmtListCnt(MainPopupEntity mainPopup);
	
	/**
	 * 리스트
	 * @param mainPopup
	 * @return
	 */
	public List<MainPopupDTO> getPopMgmtList(MainPopupEntity mainPopup);
		
	/**
	 * 등록
	 * @param mainPopup
	 * @return
	 */
	public int insPopMgmt(MainPopupEntity mainPopup);

	/**
	 * 상세 조회
	 * @param mainPopup
	 * @return
	 */
	public MainPopupDTO getPopMgmtDetail(MainPopupEntity mainPopup);

	/**
	 * 수정
	 * @param mainPopup
	 * @return
	 */
	public int updPopMgmt(MainPopupEntity mainPopup);
}
