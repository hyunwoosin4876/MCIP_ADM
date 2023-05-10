package mcip.webapps.dao.file;


import egovframework.rte.psl.dataaccess.mapper.Mapper;
import mcip.webapps.dto.file.FileEntity;
import mcip.webapps.dto.file.FileDTO;

@Mapper("fileDAO")
public interface FileDAO {

	/**
	 * 파일 상세정보 호출
	 * @param fileDTO
	 * @return
	 */
	public FileDTO getFileInfo(FileEntity file);
	
	
	/**
	 * 저장 파일 명 중복 체크
	 * @param fileDTO
	 * @return
	 */
	public String getSaveFileNameNumber(FileEntity file);


	/**
	 * 파일 정보 등록
	 * @param file
	 * @return
	 */
	public int doInsertFileInfo(FileEntity file);


	/**
	 * 다운받은수 업데이트
	 * @param tempDTO
	 */
	public int downNumPlus(FileEntity temp);


	/**
	 * 파일 정보 업데이트
	 * @param fileDTO
	 * @return
	 */
	public int doUpdateFile(FileEntity file);

}
