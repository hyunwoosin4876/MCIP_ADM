package mcip.webapps.service.common;

import java.net.NetworkInterface;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mcip.webapps.dto.file.FileEntity;
import mcip.webapps.dto.msg.MsgDTO;

/**
 *
 */
public interface CommonService {

	/**
	 * 파일업로드
	 * @param request
	 * @return
	 */
	public void getFileUpload(HttpServletRequest request, FileEntity file);

	/**
	 * 파일 다운로드
	 * @param filevo
	 * @param request
	 * @param response
	 */
	public void getFileDown(FileEntity file, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 파일 save_file_name 중보 체크
	 * @param fileVo
	 * @return
	 */
	public String getSaveFileNameNumber(FileEntity file);

	/**
	 * 공용멘트 값 호출
	 * @param bool
	 * @return
	 */
	public MsgDTO getCommonMent(boolean bool);

	/**
	 * 멘트 데이터 호출
	 * @param string
	 * @return
	 */
	public MsgDTO getMsgData(String string);
}
