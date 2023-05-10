package mcip.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import mcip.webapps.dto.file.FileDTO;

/**
 * 파일 유틸
 *  sudo : 네이버 클라우드 사용 하도록 수정 해야 함 
 * @author 신현우
 *
 */
public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	List<String> filesListInDir = new ArrayList<String>();
	
	public static List<File> fileUpload(HttpServletRequest request, String savePath, String upName){
		List<File> result = new ArrayList<>();
		
		try {
			MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;	
			List<MultipartFile> fileNameArr = multiReq.getFiles(upName);	
			
				for(int i = 0; i < fileNameArr.size(); i++){
					if(!fileNameArr.get(i).isEmpty()){
						
						String tempFileOriName = fileNameArr.get(i).getOriginalFilename();
						String saveDirPath = savePath ;
						String saveFileName =tempFileOriName;
						File dir = new File(saveDirPath);
						File tmpFile = new File(saveDirPath + File.separator + saveFileName);
						if(!dir.exists()) { 
							dir.mkdirs();
						}
						/*if(tmpFile.exists()){
							tmpFile.delete();
						}*/
						fileNameArr.get(i).transferTo(tmpFile);
						result.add(tmpFile);
					}
				}
		} catch (IllegalStateException | IOException e) {
			logger.debug(e.toString());
		}
		return result;
	}
	
	public FileDTO fileUploadNomal(HttpServletRequest request, String path, String inputName){
		

		FileDTO fileVo = new FileDTO();
		  try
		  {
				MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;
				MultipartFile file = multiReq.getFile(inputName); //실제 넘어 온 파일	

				if(file.getSize() > 0){
					String _date = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
					
					fileVo.setOriFileName(file.getOriginalFilename());
					fileVo.setFilePath(path + File.separator + _date);
					fileVo.setFileExtens(fileVo.getOriFileName().substring(fileVo.getOriFileName().lastIndexOf(".") + 1).toLowerCase());
					fileVo.setFileSize(file.getSize());
					fileVo.setSaveFileName(String.valueOf(System.currentTimeMillis()));
					
					File dir = new File(fileVo.getFilePath());
					if(!dir.exists()) { 
						//저장할 디렉토리가 없을 경우 디렉토리 생성
						dir.mkdirs();
					}
					
					file.transferTo(new File(fileVo.getFilePath() +File.separator+ fileVo.getSaveFileName()));
				}
			  
		  } catch (IllegalStateException | IOException e) {
			  logger.debug(e.toString());
		  }				
		  return fileVo;
	}

	/**
	 * 파일 업로드 네이트 View
	 * @param request
	 * @param path
	 * @param inputName
	 * @param inputName 
	 * @return
	 */
	public static FileDTO fileUploadNaver(HttpServletRequest request, String path, String urlPath, String inputName) {
		FileDTO fileVo = new FileDTO();
		  try
		  {
				MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;
				MultipartFile file = multiReq.getFile(inputName); //실제 넘어 온 파일	

				if(file.getSize() > 0){
					String _date = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
					
					fileVo.setOriFileName(file.getOriginalFilename());
					fileVo.setFilePath(path + File.separator + _date);
					fileVo.setFileUrl(urlPath + "/" + _date + "/");
					fileVo.setFileExtens(fileVo.getOriFileName().substring(fileVo.getOriFileName().lastIndexOf(".") + 1).toLowerCase());
					fileVo.setFileSize(file.getSize());
					fileVo.setSaveFileName(String.valueOf(System.currentTimeMillis()));
					
					File dir = new File(fileVo.getFilePath());
					if(!dir.exists()) { 
						//저장할 디렉토리가 없을 경우 디렉토리 생성
						dir.mkdirs();
					}
					
					file.transferTo(new File(fileVo.getFilePath() +File.separator+ fileVo.getSaveFileName()));
				}
			  
		  } catch (IllegalStateException | IOException e) {
			  logger.debug(e.toString());
		  }				
		  return fileVo;
	}

	public boolean fileCreateText(String fileName, String result, HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1.파일다운로드
		 * 2.파일다운로드에 대한 멘트 호출 및 전달  
		 */
		boolean checkB = false;
		BufferedWriter output = null;
		try {
			//1.브라우져별로 파일에 대한 기본다운로드 기능 셋팅
			String Agent=request.getHeader("USER-AGENT");
			if(Agent.indexOf("MSIE")>=0 || Agent.indexOf("WOW")>=0){	//IE
				int i=Agent.indexOf('M',2);//두번째 'M'자가 있는 위치
				String IEV=Agent.substring(i+5,i+8);
				if(IEV.equalsIgnoreCase("5.5")){
					response.setHeader( "content-disposition","filename="+ URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20")+";");
				}else{
					response.setHeader( "content-disposition","attachment;filename="+URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20")+";");
				}
			}else{	
				//파이어 폭스, 사파리
				response.setHeader( "content-disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"ISO-8859-1").replace("+", " "));
			}
			//2.파일다운로드
			output = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(response.getOutputStream()), "ISO-8859-1"));
			output.write(new String(result.getBytes("UTF-8"),"ISO-8859-1"));
			checkB = true;
		} catch (IllegalStateException e) {
			logger.debug(e.toString());
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.toString());
		} catch (IOException e) {
			logger.debug(e.toString());
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				logger.debug(e.toString());
			}			
		}
		
		return checkB;
	}
	/**
	 * 파일 정보 비교하여 다운로드
	 * @param fileVo
	 * @param tFileVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static boolean fileDown(FileDTO fileVo, FileDTO tFileVo, HttpServletRequest request, HttpServletResponse response)throws Exception {
		/*
		 * 1.파일 다운로드 정보 존재확인 ( Seq, fileName, FileTmepName )
		 * 2.브라우져별로 파일에 대한 기본다운로드 기능 셋팅
		 * 3.파일다운로드
		 * 4.파일다운로드에 대한 멘트 호출 및 전달  
		 */
		boolean result = false;
		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;
		
		try {
			//1.파일 다운로드 정보 존재확인 ( Seq, fileName, FileTmepName )
			if(fileVo.getFSeq() != 0 
					&& (fileVo.getOriFileName() != null && !fileVo.getOriFileName().equals("")) 
					&& (fileVo.getSaveFileName() != null && !fileVo.getSaveFileName().equals(""))){
				 //2.파일 정보 Seq에 맞춘 정확한 파일인지 확인
				if(tFileVo.getSaveFileName().equals(fileVo.getSaveFileName()) && tFileVo.getOriFileName().equals(fileVo.getOriFileName())){
					//3.브라우져별로 파일에 대한 기본다운로드 기능 셋팅
					String Agent=request.getHeader("USER-AGENT");
					if(Agent.indexOf("MSIE")>=0 || Agent.indexOf("WOW")>=0){	//IE
						int i=Agent.indexOf('M',2);//두번째 'M'자가 있는 위치
						fileVo.setOriFileName(URLEncoder.encode(tFileVo.getOriFileName(),"UTF-8").replaceAll("\\+", "%20"));
						String IEV=Agent.substring(i+5,i+8);
						if(IEV.equalsIgnoreCase("5.5")){
							response.setHeader( "content-disposition","filename="+fileVo.getOriFileName()+";");
						}else{
							response.setHeader( "content-disposition","attachment;filename="+fileVo.getOriFileName()+";");
						}
					}else{	//파이어 폭스, 사파리
						fileVo.setOriFileName(new String(fileVo.getOriFileName().getBytes("UTF-8"),"ISO-8859-1").replace("+", " "));
						response.setHeader( "content-disposition","attachment;filename="+fileVo.getOriFileName());
					}
					byte[] buf = new byte[4096];
					File file = new File(tFileVo.getFilePath() + File.separator + tFileVo.getSaveFileName());
					//4.파일다운로드
					fin = new BufferedInputStream(new FileInputStream(file));
					outs = new BufferedOutputStream(response.getOutputStream());
					int read = 0;
					while((read = fin.read(buf)) != -1){
						outs.write(buf,0,read);
					}
					//5.파일다운로드에 대한 멘트 호출 및 전달  
					result = true;
				}
			}
		} catch (IllegalStateException | IOException e) {
			logger.debug(e.toString());
		} finally {
			outs.close();
			fin.close();				
			
		}
		return result;
	}
	/**
	 * 전달되는 파일 다운로드
	 * @param request
	 * @param response
	 * @param file
	 * @throws Exception
	 */
	public void publicFileDown(HttpServletRequest request, HttpServletResponse response, File file)throws Exception {
		/*
		 * 1.파일다운로드
		 * 2.파일다운로드에 대한 멘트 호출 및 전달  
		 */
		//1.브라우져별로 파일에 대한 기본다운로드 기능 셋팅
		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;
		
		try {
			String Agent=request.getHeader("USER-AGENT");
			if(Agent.indexOf("MSIE")>=0 || Agent.indexOf("WOW")>=0){	//IE
				int i=Agent.indexOf('M',2);//두번째 'M'자가 있는 위치
				String IEV=Agent.substring(i+5,i+8);
				if(IEV.equalsIgnoreCase("5.5")){
					response.setHeader( "content-disposition","filename="+URLEncoder.encode(file.getName(),"UTF-8").replaceAll("\\+", "%20")+";");
				}else{
					response.setHeader( "content-disposition","attachment;filename="+URLEncoder.encode(file.getName(),"UTF-8").replaceAll("\\+", "%20")+";");
				}
			}else{	//파이어 폭스, 사파리
				response.setHeader( "content-disposition","attachment;filename="+new String(file.getName().getBytes("UTF-8"),"ISO-8859-1").replace("+", " "));
			}
			byte[] buf = new byte[4096];
			//2.파일다운로드
			fin = new BufferedInputStream(new FileInputStream(file));
			outs = new BufferedOutputStream(response.getOutputStream());
			int read = 0;
			while((read = fin.read(buf)) != -1){
				outs.write(buf,0,read);
			}
			
		} catch (IllegalStateException | IOException e) {
			logger.debug(e.toString());
		} finally {
			outs.close();
			fin.close();
			
		}
	}
	/**
	 * 가이드 진단시 파일 다운로드 Example ( DownLoad )
	 * @param request
	 * @param response
	 * @param htmlFile 
	 * @param htmlStr
	 * @param fileVo
	 * @param imageList 
	 * @throws IOException 
	 */
	/*public boolean zipGuideFileDown(HttpServletRequest request, HttpServletResponse response, List<File> list, File htmlFile, String folderName) throws IOException {*/
		/*
         * 1. 임시 저장 경로에 맞춰 가이드 저장 ( tmpDir + 날짜+system.currentMill() + 가이드 문서.html
         * 2. 해당 디렉토링 로 파일들 카피
         * 3. 설정된 디렉토리 압축~
         */
		/*if(folderName == null || folderName.equals("")) {
			folderName = "img";
		}
		zipGuideFileExpressCopy(list, htmlFile, folderName);
		
		File file = zipFileMake(this.filesListInDir,htmlFile.getName() + ".zip", htmlFile);
		
		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;
		try {
			String Agent=request.getHeader("USER-AGENT");
			if(Agent.indexOf("MSIE")>=0 || Agent.indexOf("WOW")>=0){	//IE
				int i=Agent.indexOf('M',2);//두번째 'M'자가 있는 위치
				String IEV=Agent.substring(i+5,i+8);
				if(IEV.equalsIgnoreCase("5.5")){
						response.setHeader( "content-disposition","filename="+URLEncoder.encode(htmlFile.getName(),"UTF-8").replaceAll("\\+", "%20")+";");
					
				}else{
					response.setHeader( "content-disposition","attachment;filename="+URLEncoder.encode(file.getName(),"UTF-8").replaceAll("\\+", "%20")+";");
				}
			}else{	//파이어 폭스, 사파리
				response.setHeader( "content-disposition","attachment;filename="+new String(file.getName().getBytes("UTF-8"),"ISO-8859-1").replace("+", " "));
			}
			byte[] buf = new byte[4096];
			//2.파일다운로드
			fin = new BufferedInputStream(new FileInputStream(file));
			outs = new BufferedOutputStream(response.getOutputStream());
			int read = 0;
			while((read = fin.read(buf)) != -1){
				outs.write(buf,0,read);
			}
			return true;
		} catch (IOException e) {
			logger.debug(e.toString());
			return false;
		} finally {
			outs.close();
			fin.close();
			
		}
	}*/

	/*private void zipGuideFileExpressCopy(List<File> list, File htmlFile, String folderName) {
		File zipExpFoler = new File(getDirPath(htmlFile.getAbsolutePath()));
		File dir = new File(zipExpFoler + File.separator + folderName); 
		if(!dir.exists()){
			dir.mkdirs();
		}
		for (File imgFile : list) {
			String destFileSource = dir.getAbsolutePath() + File.separator + imgFile.getName();
			try {
				FileNio2Copy(imgFile.getAbsolutePath(), destFileSource);
			} catch (IOException e) {
				logger.debug(e.toString());
			}
		}
	}*/

	/**
	 * 해당디렉토리 하위의 폴더 및 파일 모두 가져 오기
	 * @param zipExpFoler
	 * @return
	 */
	/*public void getAllFileDir(File zipExpFoler) {
		File[] files = zipExpFoler.listFiles();
        for(File file : files){
            if(file.isFile()) this.filesListInDir.add(file.getAbsolutePath());
            else getAllFileDir(file);
        }
	}*/
	
	/**
	 * 전체경로에서 마지막의 내용 뺀 경로 보여 주기
	 * @param absolutePath
	 * @return
	 */
	public String getDirPath(String absolutePath) {
		String[] paths = absolutePath.split("\\\\");
		if(paths.length < 2){
			paths = absolutePath.split("\\");
		}
		String result = "";
		for (int sCnt = 0; sCnt < paths.length; sCnt++) {
			String path = paths[sCnt];
			if(sCnt < paths.length -1){
				result += path + File.separator;
			}else{
				break;
			}
		}
		return result;
	}

	/**
	 * Zip 파일 생성
	 * @param list
	 * @param htmlFile 
	 * @return
	 */
	/*private File zipFileMake(List<String> list, String fileName, File htmlFile) {
		try {
			getAllFileDir(new File(getDirPath(htmlFile.getAbsolutePath())));
			FileOutputStream fos = new FileOutputStream(getDirPath(htmlFile.getAbsolutePath()) + fileName);
            ZipOutputStream zos = new ZipOutputStream(fos);
			for (String filePath : list) {
                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
                ZipEntry ze = new ZipEntry(filePath.substring(getDirPath(htmlFile.getAbsolutePath()).length(), filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
			}
			zos.close();
            fos.close();
			return new File(getDirPath(htmlFile.getAbsolutePath()) + fileName);
		} catch (IOException e) {
			logger.debug(e.toString());
		}
		return null;
	}*/

	/**
	 * String > 파일로 저장
	 * @param htmlStr
	 * @param pathName
	 * @param string 
	 * @return
	 */
	public static File createTextFile(String htmlStr, String savePath, String saveFileName) {
		BufferedWriter fw = null;
				
		try {
			File saveDir = new File(savePath);
			if(!saveDir.exists()){
				saveDir.mkdirs();
			}
            // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
            fw = new BufferedWriter(new FileWriter(savePath + File.separator + saveFileName, true));
             
            // 파일안에 문자열 쓰기
            fw.write(htmlStr);
            fw.flush();
 
            // 객체 닫기
             
		} catch (IOException e) {
			logger.debug(e.toString());
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				logger.debug(e.toString());
			}
			
		}
		
		return new File(savePath + File.separator + saveFileName);
	}
	/**
	 *  Java IO Stream Fullbuffer Copy
	 *  속도 : NIO.2 > NIO > IO
	 * @param source
	 * @param dest
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static boolean FileIoCopy(String source, String dest) throws FileNotFoundException, IOException {
	    try (FileInputStream fis = new FileInputStream(source);
	            FileOutputStream fos = new FileOutputStream(dest);) {
	        int availableLen = fis.available();
	        byte[] buf = new byte[availableLen];
	        fis.read(buf);
	        fos.write(buf);
	        return true;
	    } catch (IllegalStateException | IOException e) {
	    	logger.debug(e.toString());
	    	return false;
		}
	}
	 
	/**
	 *  Java IO Stream buffering Copy
	 *  속도 : NIO.2 > NIO > IO
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static boolean FileIoBufferCopy(String source, String dest) throws IOException {
	    try (FileInputStream fis = new FileInputStream(source);
	            FileOutputStream fos = new FileOutputStream(dest);
	            ) {
	 
	        byte[] buf = new byte[1024];   
	        int read;
	        while((read = fis.read(buf)) != -1) {
	            fos.write(buf, 0, read);
	        }
	        return true;
	    } catch (IllegalStateException | IOException e) {
	    	logger.debug(e.toString());
	    	return false;
		}
	}
	/**
	 * Java NIO Fullbuffer Copy
	 * 속도 : NIO.2 > NIO > IO
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static boolean FileNioFullBufferCopy(String source, String dest) throws IOException {
	    try (FileInputStream fis = new FileInputStream(source);
	            FileOutputStream fos = new FileOutputStream(dest);
	            FileChannel isbc = fis.getChannel();
	            FileChannel ogbc = fos.getChannel();) {
	         
	        ByteBuffer buf = ByteBuffer.allocateDirect(fis.available());
	        isbc.read(buf);
	        buf.flip();
	        ogbc.write(buf);
	        return true;
	    } catch (IllegalStateException | IOException e) {
	    	logger.debug(e.toString());
	    	return false;
		}
	}
	 
	/**
	 * Java NIO ByteBuffering Copy
	 * 속도 : NIO.2 > NIO > IO
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static boolean FileNioByteBufferingCopy(String source, String dest) throws IOException {
	    try (FileInputStream fis = new FileInputStream(source);
	            FileOutputStream fos = new FileOutputStream(dest);
	            FileChannel isbc = fis.getChannel();
	            FileChannel ogbc = fos.getChannel();) {
	 
	        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
	        while (isbc.read(buf) != -1) {
	            buf.flip();
	            ogbc.write(buf);
	            buf.clear();
	        }
	        return true;
	    } catch (IllegalStateException | IOException e) {
	    	logger.debug(e.toString());
	    	return false;
		}
	}
	 
	/**
	 * Java NIO FileNioMappedByteBuffer Copy
	 * 속도 : NIO.2 > NIO > IO
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static boolean FileNioMappedByteBufferCopy(String source, String dest) throws IOException {
	    try (FileInputStream fis = new FileInputStream(source);
	            FileOutputStream fos = new FileOutputStream(dest);
	            FileChannel ifc = fis.getChannel();
	            FileChannel ofc = fos.getChannel();) {
	 
	        MappedByteBuffer mbb =  ifc.map(FileChannel.MapMode.READ_ONLY, 0, ifc.size());
	        ofc.write(mbb);
	        return true;
	    } catch (IllegalStateException | IOException e) {
	    	logger.debug(e.toString());
	    	return false;
		}
	}
	 
	/**
	 * Java NIO Channel Copy #1
	 * 속도 : NIO.2 > NIO > IO
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static boolean FileNioChannelFromCopy(String source, String dest) throws IOException {
	    try (FileInputStream fis = new FileInputStream(source);
	            FileOutputStream fos = new FileOutputStream(dest);
	            FileChannel fic = fis.getChannel();
	            FileChannel foc = fos.getChannel();) {
	            foc.transferFrom(fic, 0, fic.size());
	            return true;
	    } catch (IllegalStateException | IOException e) {
	    	logger.debug(e.toString());
	    	return false;
		}
	}
	 
	/**
	 * Java NIO Channel Copy #2
	 * 속도 : NIO.2 > NIO > IO
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static boolean FileNioChannelToCopy(String source, String dest) throws IOException {
	    try (FileInputStream fis = new FileInputStream(source);
	            FileOutputStream fos = new FileOutputStream(dest);
	            FileChannel fic = fis.getChannel();
	            FileChannel foc = fos.getChannel();) {
	            fic.transferTo(0, fic.size(), foc);
	            return true;
	    } catch (IllegalStateException | IOException e) {
	    	logger.debug(e.toString());
	    	return false;
		}
	}
	/**
	 * Java NIO.2 Copy
	 * 속도 : NIO.2 > NIO > IO
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static boolean FileNio2Copy(String source, String dest) throws IOException {
	    try {
			Files.copy(new File(source).toPath(), new File(dest).toPath());
			return true;
	    } catch (IllegalStateException | IOException e) {
			logger.debug(e.toString());
			return false;
		}
	}

	/**
	 * 기본 파일 업로드 
	 * @param request
	 * @param path
	 * @param inputName
	 * @param urlPath
	 * @return
	 */
	public List<FileDTO> fileUploadListNomal(HttpServletRequest request, String path, String inputName, String urlPath){
		List<FileDTO> result = new ArrayList<FileDTO>();
		try{
			MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;
			List<MultipartFile> fileNameArr = multiReq.getFiles(inputName);	
			if(fileNameArr != null){
				for(int i = 0; i < fileNameArr.size(); i++){
					MultipartFile file = fileNameArr.get(i);
					if(file.getSize() > 0){
						FileDTO fileVo = new FileDTO();
						String _date = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
						
						fileVo.setOriFileName(file.getOriginalFilename());
						if(File.separator != "/"){
							fileVo.setFilePath(path + "/" + _date);
						} else {
							fileVo.setFilePath(path + File.separator + _date);
						}
						fileVo.setFileExtens(fileVo.getOriFileName().substring(fileVo.getOriFileName().lastIndexOf(".") + 1).toLowerCase());
						fileVo.setFileType(getFileType(file, "MIME"));
						fileVo.setFileSize(file.getSize());
						fileVo.setSaveFileName(String.valueOf(System.currentTimeMillis()));
						fileVo.setFileUrl(urlPath + "/" + _date + "/" + fileVo.getSaveFileName());
						
						File dir = new File(fileVo.getFilePath());
						if(!dir.exists()) { 
							//저장할 디렉토리가 없을 경우 디렉토리 생성
							dir.mkdirs();
						}
						
						if(File.separator != "/"){
							file.transferTo(new File(fileVo.getFilePath() + "/" + fileVo.getSaveFileName()));
						} else {
							file.transferTo(new File(fileVo.getFilePath() +File.separator+ fileVo.getSaveFileName()));
						}
						
						result.add(fileVo);
					}
				}
			}
			  
		} catch (IllegalStateException | IOException e) {
			logger.debug(e.toString());
		}				
		return result;
	}

	/**
	 * MultiPartFile과 얻고자 하는 타입을 전달하여 File의 tika로 파일의 정보 호출
	 * OFFICE 군에서 정의되는 정보 삭제 준비 중임
	 * @param file
	 * @param type
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getFileType(MultipartFile file, String type) {
		String result = "";
		File rFile = FileFromMultiPartFile(file);
		ContentHandler contentHandler = new BodyContentHandler();
		FileInputStream is = null;
		try {
				is = new FileInputStream(rFile);
				
				Metadata md = new Metadata();
				md.set(Metadata.RESOURCE_NAME_KEY, file.getName());
				Parser parser = new AutoDetectParser();
				parser.parse(is, contentHandler, md,  new ParseContext());
				if(type.equals("MIME")){
					result = md.get(Metadata.CONTENT_TYPE);
				}else if(type.equals("TITLE")){
					result = md.get(Metadata.TITLE);
				}else if(type.equals("AUTHOR")){
					result = md.get(Metadata.AUTHOR);
				}else if(type.equals("CONTENT")){
					result = contentHandler.toString();
				}else{
					logger.debug("Another Type Add Devlop");
				}
		} catch (IOException e) {
			logger.debug(e.toString());
		} catch (SAXException e) {
			logger.debug(e.toString());
		} catch (TikaException e) {
			logger.debug(e.toString());
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.debug(e.toString());
				}
			}
		}
		
		return result;
	}
	/**
	 * File과 얻고자 하는 타입을 전달하여 File의 tika로 파일의 정보 호출
	 * OFFICE 군에서 정의되는 정보 삭제 준비 중임
	 * @param rFile
	 * @param type
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getFileType(File rFile, String type) {
		String result = "";
		ContentHandler contentHandler = new BodyContentHandler();
		FileInputStream is = null;
		try {
				is = new FileInputStream(rFile);
				
				Metadata md = new Metadata();
				md.set(Metadata.RESOURCE_NAME_KEY, rFile.getName());
				Parser parser = new AutoDetectParser();
				parser.parse(is, contentHandler, md,  new ParseContext());
				if(type.equals("MIME")){
					result = md.get(Metadata.CONTENT_TYPE);
				}else if(type.equals("TITLE")){
					result = md.get(Metadata.TITLE);
				}else if(type.equals("AUTHOR")){
					result = md.get(Metadata.AUTHOR);
				}else if(type.equals("CONTENT")){
					result = contentHandler.toString();
				}else{
					logger.debug("Another Type Add Devlop");
				}
		} catch (IOException e) {
			logger.debug(e.toString());
		} catch (SAXException e) {
			logger.debug(e.toString());
		} catch (TikaException e) {
			logger.debug(e.toString());
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.debug(e.toString());
				}
			}
		}
		
		return result;
	}
	/**
	 * MultiFile을 Java File Object로 변환(메모리 상에 남으므로 무거워질수 잇음)
	 * @param file
	 * @return
	 */
	public File FileFromMultiPartFile(MultipartFile file){
		File rFile = new File(file.getOriginalFilename());
		FileOutputStream fos = null;
		try {
			if(rFile.createNewFile()){
				fos = new FileOutputStream(rFile);
				fos.write(file.getBytes());
				fos.close();
				logger.debug("Succ Tmpe File");
			}else{
				logger.debug("Fail Tmpe File");
			}
		} catch (IOException e) {
			logger.debug(e.toString());
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.debug(e.toString());
				}
			}
		}
		return rFile;
	}

	/**
	 * 디렉토리안의 파일 삭제
	 * @param fDir
	 */
	/*public void deleteFileInDir(File fDir) {
		if (!fDir.exists()) {
			return;
		}
		File[] fList = fDir.listFiles();
		for (int i = 0; i < fList.length; i++) {
			if (fList[i].isDirectory())
				deleteDirAll(fList[i]);
			else {
				fList[i].delete();
			}
		}
	}*/
	/**
	 * 디렉토리 삭제
	 * @param fDir
	 */
	/*public void deleteDir(File fDir){
		if (!fDir.exists()) {
			return;
		}
		File[] fList = fDir.listFiles();
		for (int i = 0; i < fList.length; i++) {
			if (fList[i].isDirectory())
				deleteDirAll(fList[i]);
			else {
				fList[i].delete();
			}
	   }
	   fDir.delete();
	}*/

	/**
	 * 디렉토리 안의 파일 모두 삭제
	 * @param fDir
	 */
	/*public void deleteDirAll(File fDir){
		if (!fDir.exists()) {
			return;
		}
		File[] fList = fDir.listFiles();
		for (int i = 0; i < fList.length; i++) {
			if (fList[i].isDirectory())
				deleteDirAll(fList[i]);
			else {
				fList[i].delete();
			}
		}
		fDir.delete();
	}*/
  
	/**
	 * 파일삭제
	 * @param file
	 */
	public boolean deleteFile(File file){
		boolean fileResult = false;

		fileResult = file.delete();
		
		return fileResult;
	}
}