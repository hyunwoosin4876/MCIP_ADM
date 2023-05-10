package mcip.webapps.dto.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mcip.webapps.dto.DefaultDTO;

@Setter
@Getter
@ToString
public class FileEntity extends DefaultDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4987166720923567829L;
	
	/**
	 * 파일고유번호
	 */
	private long fSeq;
	/**
	 * 본래파일명
	 */
	private String oriFileName = null;
	/**
	 * 저장된파일명
	 */
	private String saveFileName = null;
	/**
	 * 파일확장자
	 */
	private String fileExtens = null;
	/**fSeq
	 */
	private String fileType = null;
	/**
	 * 파일용량
	 */
	private double fileSize;
	/**
	 * 파일경로
	 */
	private String filePath = null;
	/**
	 * 파일URL
	 */
	private String fileUrl = null;
	/**
	 * view form input name
	 */
	private String fileInputName;
	/**
	 * 다운받은수
	 */
	private int downNum;
}
