package mcip.webapps.service.mainPopup;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mcip.webapps.dto.mainPopup.MainPopupDTO;
import mcip.webapps.dto.mainPopup.MainPopupEntity;
import mcip.webapps.dto.msg.MsgDTO;

/**
 * 팝업 Service
 * @author 추연상
 *
 */
public interface MainPopupService {

	/**
	 * 리스트 조회 개수
	 * @param mainPopup
	 * @return
	 */
	public int getPopMgmtListCnt(MainPopupEntity mainPopup);
	
	/**
	 * 리스트 조회
	 * @param mainPopup
	 * @return
	 */
	public List<MainPopupDTO> getPopMgmtList(MainPopupEntity mainPopup);

	/**
	 * 등록
	 * @param mainPopup
	 * @return
	 */
	public MsgDTO insPopMgmt(MainPopupEntity mainPopup, HttpServletRequest request);

	/**
	 * 상세 조회
	 * @param mainPopup
	 * @return
	 */
	public MainPopupDTO getPopMgmtDetail(MainPopupEntity mainPopup);

	/**
	 * 수정
	 * @param mainPopup
	 * @param request
	 * @return
	 */
	public MsgDTO updPopMgmt(MainPopupEntity mainPopup, HttpServletRequest request);
}
