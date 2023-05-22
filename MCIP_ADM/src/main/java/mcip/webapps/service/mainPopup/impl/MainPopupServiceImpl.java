package mcip.webapps.service.mainPopup.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import mcip.framework.util.FileUtil;
import mcip.webapps.dao.mainPopup.MainPopupDAO;
import mcip.webapps.dto.file.FileDTO;
import mcip.webapps.dto.mainPopup.MainPopupDTO;
import mcip.webapps.dto.mainPopup.MainPopupEntity;
import mcip.webapps.dto.msg.MsgDTO;
import mcip.webapps.service.common.CommonService;
import mcip.webapps.service.mainPopup.MainPopupService;

/**
 * 팝업 ServiceImpl
 * @author 추연상
 *
 */
@Service("mainPopupService")
public class MainPopupServiceImpl implements MainPopupService {
	private static final Logger logger = LoggerFactory.getLogger(MainPopupServiceImpl.class);
	
	@Resource(name="applProperties")
	private Properties applProperties;
	
	@Autowired CommonService commonService;
	@Autowired MainPopupDAO mainPopupDAO;	
		
	@Override
	public int getPopMgmtListCnt(MainPopupEntity mainPopup) {
		return mainPopupDAO.getPopMgmtListCnt(mainPopup);
	}

	@Override
	public List<MainPopupDTO> getPopMgmtList(MainPopupEntity mainPopup) {
		return mainPopupDAO.getPopMgmtList(mainPopup);
	}

	@Override
	public MsgDTO insPopMgmt(MainPopupEntity mainPopup, HttpServletRequest request) {		
		FileDTO fileDTO = imageUpload(request);
		if(!"".equals(fileDTO.getOriFileName())) {
			mainPopup.setPopOrgnlFileNm(fileDTO.getOriFileName());
			mainPopup.setPopUldFileNm(fileDTO.getSaveFileName());
			mainPopup.setPopFileSize(Integer.parseInt(String.valueOf(Math.round(fileDTO.getFileSize()))));
			mainPopup.setPopFilePath(fileDTO.getFilePath());
		}					
		
		MsgDTO result = new MsgDTO();
		if (mainPopupDAO.insPopMgmt(mainPopup) > 0) {
			result.setMsgCode("0000");
		} else {
			result.setMsgCode("9999");
		}
		
		return result;
	}

	@Override
	public MainPopupDTO getPopMgmtDetail(MainPopupEntity mainPopup) {
		return mainPopupDAO.getPopMgmtDetail(mainPopup);
	}
	
	@Override
	public MsgDTO updPopMgmt(MainPopupEntity mainPopup, HttpServletRequest request) {
		MsgDTO result = new MsgDTO();		
		if("Y".equals(mainPopup.getUpdImgFileYn())) {
			FileDTO fileDTO = imageUpload(request);
			if(!StringUtils.isEmpty(fileDTO.getOriFileName())) {
				mainPopup.setPopOrgnlFileNm(fileDTO.getOriFileName());
				mainPopup.setPopUldFileNm(fileDTO.getSaveFileName());
				mainPopup.setPopFileSize(Integer.parseInt(String.valueOf(Math.round(fileDTO.getFileSize()))));
				mainPopup.setPopFilePath(fileDTO.getFilePath());
			}else {
				mainPopup.setPopOrgnlFileNm("");
				mainPopup.setPopUldFileNm("");
				mainPopup.setPopFileSize(0);
				mainPopup.setPopFilePath("");
			}
		}		
		if (mainPopupDAO.updPopMgmt(mainPopup) > 0) {
			result.setMsgCode("0000");
		} else {
			result.setMsgCode("9999");
		}
		
		return result;
	}
	
	public FileDTO imageUpload(HttpServletRequest request) {
		FileDTO fileVo = new FileDTO();
		
		try {
			String path = applProperties.getProperty("file.diag.uploadpath");
			MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;
			MultipartFile file = multiReq.getFile("image");

			if (file != null) {
				String _date = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
				String fullFilePath = path + File.separator + _date;
				
				fileVo.setOriFileName(file.getOriginalFilename());
				fileVo.setFilePath(File.separator+FilenameUtils.getPath(fullFilePath+File.separator));				
				fileVo.setFileExtens(fileVo.getOriFileName().substring(fileVo.getOriFileName().lastIndexOf(".") + 1).toLowerCase());
				fileVo.setFileSize(file.getSize());
				fileVo.setSaveFileName(String.valueOf(System.currentTimeMillis()));
				
				File dir = new File(fullFilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				file.transferTo(new File(fullFilePath + File.separator + fileVo.getSaveFileName()));
			}			
		} catch (IllegalStateException | IOException e) {
			logger.debug(e.toString());
		}
		
		return fileVo;
	}
}
