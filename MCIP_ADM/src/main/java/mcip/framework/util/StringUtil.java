package mcip.framework.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mcip.egovframe.util.EgovStringUtil;



public class StringUtil {
	
	
	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
	/**
	 * URL Encode ( 기본값 : UTF-8 )
	 * @param value
	 * @return
	 */
	public static String encStr(String value){
		try {
			return URLEncoder.encode(value, "UTF-8");			
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.toString());
			return "";
		}
		
	}
	/**
	 * URL Encode
	 * @param value
	 * @param encType
	 * @return
	 */
	public static String encStr(String value, String encType){
		try {
			return URLEncoder.encode(value, encType);			
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.toString());
			return "";
		}
		
	}
	
	/**
	 * URL Decode ( 기본값 : UTF-8 )
	 * @param value
	 * @return
	 */
	public static String decStr(String value){
		try {
			return URLDecoder.decode(value, "UTF-8");			
		} catch (IllegalStateException | IOException e) {
			logger.debug(e.toString());
			return "";
		}
		
	}
	/**
	 * URL Decode
	 * @param value
	 * @param decType
	 * @return
	 */
	public static String decStr(String value, String decType){
		try {
			return URLDecoder.decode(value, decType);			
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.toString());
			return "";
		}
		
	}
	
	/**
	 * 영문+숫자 비밀번호 생성
	 * @param cnt
	 * @return
	 */
	public static String randomPwd(int cnt){
		
		Random rnd =new Random();

		StringBuffer str =new StringBuffer();
		for(int i=0;i<cnt;i++){
		    if(rnd.nextBoolean()){
		     str.append(String.valueOf((char)((int)(rnd.nextInt(26))+97)).toLowerCase());
		    }else{
		     str.append((rnd.nextInt(10))); 
		    }
		}
		
		return str.toString();
	}
	
	/**
	 * 이미지 태그 제거
	 * @param str
	 * @return
	 */
	public static String imgTagDelete(String str){		
		String result = "";		
		if(!EgovStringUtil.isEmpty(str)){
			Pattern p = Pattern.compile("<img .*?>", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(str);
			result = m.replaceAll("");
		}		
		return result;
	}
	
	/**
	 * html 태그 제거
	 * @param str
	 * @return
	 */
	public static String htmlTagDelete(String str){
		String result = "";		
		if(!EgovStringUtil.isEmpty(str)){			
			result = str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		}		
		return result;
	}
	
	/**
	 * html 태그 제거 후 글자 자르기
	 * @param str
	 * @return
	 */
	public static String htmlTagDeleteSubString(String str,int start,int end){
		
		String result = "";		
		if(!EgovStringUtil.isEmpty(str)){			
			result = str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");						
			if(end > result.length()){
				end = result.length();
			}			
			result = result.substring(start, end);			
		}		
		return result;
	}
	/**
	 * 아이디 마스킹처리
	 * @param str
	 * @return
	 */
	public static String maskId(String str){
		String result = "";
		if(!EgovStringUtil.isEmpty(str)){
			result = str.substring(0, str.length()-2) + "**";
		}
		return result;
	}
	/**
	 * 이메일 마스킹처리
	 * @param str
	 * @return
	 */
	public static String maskEmail(String str){
		String result = "";
		String[] strArr;
		if(!EgovStringUtil.isEmpty(str)){
			strArr = str.split("@");
			strArr[0] = strArr[0].substring(0, strArr[0].length()-2) + "**";
			for(int i = 0 ; i < strArr.length ; i++ ){
				if(i == strArr.length-1){
					result = result + strArr[i];
				}else{
					result = result + strArr[i] + "@";
				}
			}
		}
		return result;
	}
	/**
	 * 휴대폰번호 마스킹처리
	 * @param str
	 * @return
	 */
	public static String maskMobileNo(String str){
		String result = "";
		if(!EgovStringUtil.isEmpty(str) && str.length() > 8){
			String num1 = str.substring(0, 3);
			String num2 = str.substring(3, 5)+"**";
			String num3 = str.substring(7, str.length());
			result = num1 +"-"+num2 +"-"+num3;
		}else{
			result = str;
		}
		return result;
	}
	/**
	 * 숫자 3자리마다 콤마(정수형)
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String getDecimalAmountI(String str) throws Exception{
		if(str != null && !str.equals("")){
			String moneyString = new Integer(str).toString(); 
			String format = "#,##0"; 
			DecimalFormat df = new DecimalFormat(format); 
			DecimalFormatSymbols dfs = new DecimalFormatSymbols(); 
			
			dfs.setGroupingSeparator(',');// 구분자를 ,로 
			df.setGroupingSize(3);//3자리 단위마다 구분자처리 한다. 
			df.setDecimalFormatSymbols(dfs); 
			return (df.format(Integer.parseInt(moneyString))).toString(); 
		}else{
			return "0";
		}

   }
	
	/**
	 * 숫자 3자리마다 콤마(Long형)
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String getDecimalAmountL(String str) throws Exception{
		if(str == null || str.equals("")){
			str = "0";
		}
        String moneyString = new Long(str).toString(); 
		String format = "#,##0"; 
        DecimalFormat df = new DecimalFormat(format); 
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(); 

        dfs.setGroupingSeparator(',');// 구분자를 ,로 
        df.setGroupingSize(3);//3자리 단위마다 구분자처리 한다. 
        df.setDecimalFormatSymbols(dfs); 

        return (df.format(Long.parseLong(moneyString))).toString(); 
   }
	
	
	/**
	 * 단순 null 체크만 해서 파라메터가 null 일경우 빈값을 리턴.
	 * @return String
	 */
	public static String convertNullToSpace(Object str) {
		String rtnStr = null;
		try {
			rtnStr = str.toString();
		} catch (IllegalStateException e) {
			rtnStr = "";
		} 
		return rtnStr;
	}
	
	/**
	 * 문자길이 변환 (AES 암호화에 16변환 필요)
	 * @param str 대상문자열
	 * @return
	 */
	public static String strLengthCnvt(Object obj) {
		   
		int len = 0;
	   
		String result = obj.toString();
		int a = result.length();
		if(a < 16){
			len = 16;
		}else if(16 < a && a < 24 ){
			len = 23;
		}else if(24 < a && a < 32 ){
			len = 32;
		}else{
			len = 9999;
		}
		if(len != 9999){
			int templen   = len - result.length();
			for (int i = 0; i < templen; i++){
				result = "0" + result;
			}
		}else{
			result = result.substring(0, 31);
		}
		return result;
	}
	/**
	 * ID check 
	 * 시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하
	 * @param userID
	 * @return
	 */
	public static boolean userIDCheck(String userID) {
		//시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하
		String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
        
        if(Pattern.matches(regex, userID)){
        	return true;
        }else{
        	return false;
        }    
	}
	/**
	 * PW check
	 * 시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하
	 * @param password
	 * @return
	 */
	public static boolean userPWCheck(String password) {
		//시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하
		String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
        
        if(Pattern.matches(regex, password)){
        	return true;
        }else{
        	return false;
        }    
	}
	/**
	 * JSP 전달전 특수문자 치환 함수
	 * @param str
	 * @return
	 */
	public static String stringTansSChar(String str) {
		String result = str;
		if(str.indexOf("!") > 0){
			result = result.replaceAll("!", "&#33;");
		}
		if(str.indexOf("\"") > 0){
			result = result.replaceAll("\"", "&#34;");
		}
		if(str.indexOf("#") > 0){
			result = result.replaceAll("#", "&#35;");
		}
		if(str.indexOf("$") > 0){
			result = result.replaceAll("$", "&#36;");
		}
		if(str.indexOf("%") > 0){
			result = result.replaceAll("%", "&#37;");
		}
		if(str.indexOf("&") > 0){
			result = result.replaceAll("&", "&#38;");
		}
		if(str.indexOf("\'") > 0){
			result = result.replaceAll("\'", "&#39;");
		}
		if(str.indexOf("(") > 0){
			result = result.replaceAll("(", "&#40;");
		}
		if(str.indexOf(")") > 0){
			result = result.replaceAll(")", "&#41;");
		}
		if(str.indexOf("*") > 0){
			result = result.replaceAll("*", "&#42;");
		}
		if(str.indexOf("+") > 0){
			result = result.replaceAll("+", "&#43;");
		}
		if(str.indexOf(",") > 0){
			result = result.replaceAll(",", "&#44;");
		}
		if(str.indexOf("-") > 0){
			result = result.replaceAll("-", "&#45;");
		}
		if(str.indexOf(".") > 0){
			result = result.replaceAll(".", "&#46;");
		}
		if(str.indexOf("/") > 0){
			result = result.replaceAll("/", "&#47;");
		}
		if(str.indexOf(":") > 0){
			result = result.replaceAll(":", "&#58;");
		}
		if(str.indexOf(";") > 0){
			result = result.replaceAll(";", "&#59;");
		}
		if(str.indexOf("<") > 0){
			result = result.replaceAll("<", "&#60;");
		}
		if(str.indexOf("=") > 0){
			result = result.replaceAll("=", "&#61;");
		}
		if(str.indexOf(">") > 0){
			result = result.replaceAll(">", "&#62;");
		}
		if(str.indexOf("?") > 0){
			result = result.replaceAll("?", "&#63;");
		}
		if(str.indexOf("@") > 0){
			result = result.replaceAll("@", "&#64;");
		}
		if(str.indexOf("[") > 0){
			result = result.replaceAll("[", "&#91;");
		}
		if(str.indexOf("\\") > 0){
			result = result.replaceAll("\\", "&#92;");
		}
		if(str.indexOf("]") > 0){
			result = result.replaceAll("]", "&#93;");
		}
		if(str.indexOf("^") > 0){
			result = result.replaceAll("^", "&#94;");
		}
		if(str.indexOf("`") > 0){
			result = result.replaceAll("`", "&#96;");
		}
		if(str.indexOf("{") > 0){
			result = result.replaceAll("{", "&#123;");
		}
		if(str.indexOf("|") > 0){
			result = result.replaceAll("|", "&#124;");
		}
		if(str.indexOf("}") > 0){
			result = result.replaceAll("}", "&#125;");
		}
		if(str.indexOf("~") > 0){
			
			result = result.replaceAll("~", "&#126;");
		}
		if(str.indexOf("\"") > -1){
			result = result.replaceAll("\"", "&quot;");
		}
		if(str.indexOf("\'") > -1){
			result = result.replaceAll("\'", "&apos;");
		}
		return result;
	}
	
	public String sCharTansString(String str) {
		String result = str;
		if(str.indexOf("&#33;") > -1){
			result = result.replaceAll("&#33;", "!");
		}
		if(str.indexOf("&#34;") > -1){
			result = result.replaceAll("&#34;", "\"");
		}
		if(str.indexOf("&#35;") > -1){
			result = result.replaceAll("&#35;", "#");
		}
		if(str.indexOf("&#36;") > -1){
			result = result.replaceAll("&#36;", "\\$");
		}
		if(str.indexOf("&#37;") > -1){
			result = result.replaceAll("&#37;", "%");
		}
		if(str.indexOf("&#38;") > -1){
			result = result.replaceAll("&#38;", "&");
		}
		if(str.indexOf("&#39;") > -1){
			result = result.replaceAll("&#39;", "\'");
		}
		if(str.indexOf("&#40;") > -1){
			result = result.replaceAll("&#40;", "(");
		}
		if(str.indexOf("&#41;") > -1){
			result = result.replaceAll("&#41;", ")");
		}
		if(str.indexOf("&#42;") > -1){
			result = result.replaceAll("&#42;", "*");
		}
		if(str.indexOf("&#43;") > -1){
			result = result.replaceAll("&#43;", "+");
		}
		if(str.indexOf("&#44;") > -1){
			result = result.replaceAll("&#44;", ",");
		}
		if(str.indexOf("&#45;") > -1){
			result = result.replaceAll("&#45;", "-");
		}
		if(str.indexOf("&#46;") > -1){
			result = result.replaceAll("&#46;", ".");
		}
		if(str.indexOf("&#47;") > -1){
			result = result.replaceAll("&#47;", "/");
		}
		if(str.indexOf("&#58;") > -1){
			result = result.replaceAll("&#58;", ":");
		}
		if(str.indexOf("&#59;") > -1){
			result = result.replaceAll("&#59;", ";");
		}
		if(str.indexOf("&#60;") > -1){
			result = result.replaceAll("&#60;", "<");
		}
		if(str.indexOf("&#61;") > -1){
			result = result.replaceAll("&#61;", "=");
		}
		if(str.indexOf("&#62;") > -1){
			result = result.replaceAll("&#62;", ">");
		}
		if(str.indexOf("&#63;") > -1){
			result = result.replaceAll("&#63;", "?");
		}
		if(str.indexOf("&#64;") > -1){
			result = result.replaceAll("&#64;", "@");
		}
		if(str.indexOf("&#91;") > -1){
			result = result.replaceAll("&#91;", "[");
		}
		if(str.indexOf("&#92;") > -1){
			result = result.replaceAll("&#92;", "\\\\");
		}
		if(str.indexOf("&#93;") > -1){
			result = result.replaceAll("&#93;", "]");
		}
		if(str.indexOf("&#94;") > -1){
			result = result.replaceAll("&#94;", "^");
		}
		if(str.indexOf("&#95;") > -1){
			result = result.replaceAll("&#95;", "_");
		}
		if(str.indexOf("&#96;") > -1){
			result = result.replaceAll("&#96;", "`");
		}
		if(str.indexOf("&#123;") > -1){
			result = result.replaceAll("&#123;", "{");
		}
		if(str.indexOf("&#124;") > -1){
			result = result.replaceAll("&#124;", "|");
		}
		if(str.indexOf("&#125;") > -1){
			result = result.replaceAll("&#125;", "}");
		}
		if(str.indexOf("&#126;") > -1){
			result = result.replaceAll("&#126;", "~");
		}
		if(str.indexOf("&lt;") > -1){
			result = result.replaceAll("&lt;", "<");
		}
		if(str.indexOf("&gt;") > -1){
			result = result.replaceAll("&gt;", ">");
		}
		if(str.indexOf("&quot;") > -1){
			result = result.replaceAll("&quot;", "\"");
		}
		if(str.indexOf("&apos;") > -1){
			result = result.replaceAll("&apos;", "\'");
		}
		
		return result;
	}
}
