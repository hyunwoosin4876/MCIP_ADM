package mcip.webapps.service.common.impl;

import java.io.File;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mcip.framework.util.FileUtil;
import mcip.webapps.dao.file.FileDAO;
import mcip.webapps.dao.msg.MsgDAO;
import mcip.webapps.dto.file.FileDTO;
import mcip.webapps.dto.file.FileEntity;
import mcip.webapps.dto.msg.MsgDTO;
import mcip.webapps.service.common.CommonService;

/**
 *
 */
@Slf4j
@Service("commonService")
public class CommonServiceImpl implements CommonService {
	@Resource(name="applProperties")
	private Properties applProperties;

	@Autowired FileDAO fileDAO;
	@Autowired MsgDAO msgDAO;
	
	@Override
	public void getFileUpload(HttpServletRequest request, FileEntity file) {
		try {
			String path = applProperties.getProperty("file.diag.uploadpath");
			file = new FileUtil().fileUploadNomal(request, path, file.getFileInputName());
		} catch (IllegalStateException e) {
			log.debug(e.toString());
		}
		log.debug(file.toString());
	}
	
	@Override
	public String getSaveFileNameNumber(FileEntity file) {
		String saveFileNameNumber = fileDAO.getSaveFileNameNumber(file);
		return saveFileNameNumber;
	}

	@Override
	public void getFileDown(FileEntity fileDTO, HttpServletRequest request, HttpServletResponse response) {

		try {
			if(fileDTO.getFSeq() == 0){
				if(fileDTO.getOriFileName().equals("excelAssets")){
					File file = new PathMatchingResourcePatternResolver().getResource("/egovframework/eVMS/publicFile/AssetsExampleFile.xlsx").getFile();
					new FileUtil().publicFileDown(request, response, file);
				}
			}else{
				FileDTO tFilevo = fileDAO.getFileInfo(fileDTO);
				FileDTO fileVO = (FileDTO) fileDTO;
				FileUtil.fileDown(fileVO, tFilevo, request, response);
			}
		} catch (Exception e) {
			log.debug(e.toString());
		}
	}

	@Override
	public MsgDTO getCommonMent(boolean bool) {
		MsgDTO result = null;
		if(bool) {
			result = msgDAO.getMsgData("0000");
		} else {
			result = msgDAO.getMsgData("9999");			
		}
		 
		return result;
	}
	@Override
	public MsgDTO getMsgData(String string) {
		return msgDAO.getMsgData(string);
	}
}
