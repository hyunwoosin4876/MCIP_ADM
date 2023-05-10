package mcip.framework.util.enc;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mcip.framework.util.StringUtil;

public class EncModule {
	private static final Logger logger = LoggerFactory.getLogger(EncModule.class);
	
	byte[] a128 = {18, 89, 101, 17, 69, 53, 1, -88, -56, -2, -103, 16, 18, 52, -2, 9};
	byte[] a256 = {104, 89, 16, 17, -111, 0, 1, -120, 73, -2, 18, -88, 18, 52, -23, 9, 1, 49, 68, 64, 99, 105, 119, -1, 0, 14, 3, -43, 53, 18, 17, 38};
	byte[] lv = {1, -6, -70, -77, 29, 14, 4, 16, 105, 34, -35, -128, -52, -97, -54, 20};
	
	/**
	 * Base64 Encript
	 * @param encData
	 * @return
	 */
	public String doEncriptBase64(String encData) {
		
		Encoder enCoder = Base64.getEncoder();
		byte[] enCodeByte = enCoder.encode(encData.getBytes());

		return new String(enCodeByte);
	}
	/**
	 * MD5 방식 암호화
	 *
	 * @param message
	 * @return
	 * @throws Exception
	 */
//	public String doEncriptMD5(String key, String data) {
//		//MD5 방식 암호화
//		String message = key+":"+data;
//		StringBuilder sb = new StringBuilder();
//		MessageDigest md5;
//		try {
//			md5 = MessageDigest.getInstance("MD5");
//			md5.update(message.getBytes());
//			byte[] md5encrypt = md5.digest(); 
//			for(int i = 0 ; i < md5encrypt.length ; i++){
//				sb.append(Integer.toHexString((int)md5encrypt[i] & 0xFF));
//			}
//		} catch (NoSuchAlgorithmException e) {
//			LOGGER.debug(e.toString());
//		}
//		return sb.toString();
//	}
	/**
	 * SHA128 암호화 
	 * @param encKey
	 * @param encData
	 * @return
	 */
	public String doEncriptSHA128(String encKey, String encData){
		 try {
			String message = encKey+":"+encData;
		 	MessageDigest md = MessageDigest.getInstance("SHA-1"); // 이 부분을 SHA-256, MD5로만 바꿔주면 된다.
	 		md.update(message.getBytes()); // "세이프123"을 SHA-1으로 변환할 예정!
		 
	 		byte byteData[] = md.digest();
	 		StringBuffer sb = new StringBuffer(); 
	 		for(int i=0; i<byteData.length; i++) {
	 			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
	 		}
	 		return sb.toString();
		} catch(NoSuchAlgorithmException e){
		    logger.debug(e.toString()); 
		    return null;
		}
	}
	/**
	 * AES 암호화(16진수 문자열 배열로 변환)
	 * @param encKey
	 * @param encData
	 * @return
	 */
	public String doEncriptAES(String encKey, String encData) {
			if(encKey.length() != 16 && encKey.length() != 24 && encKey.length() != 32 ){
				encKey = StringUtil.strLengthCnvt(encKey);
			}
			//AES 방식 암호화 
			SecretKeySpec skeySpec = new SecretKeySpec(encKey.getBytes(), "AES");
			// Instantiate the cipher
			Cipher cipher;
			try {
				cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
				byte[] encrypted = cipher.doFinal(encData.getBytes());
				return byteArrayToHex(encrypted);
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				logger.debug(e.toString());
				return null;
			} catch (InvalidKeyException e) {
				logger.debug(e.toString());
				return null;
			} catch (IllegalBlockSizeException e) {
				logger.debug(e.toString());
				return null;
			} catch (BadPaddingException e) {
				logger.debug(e.toString());
				return null;
			}
		
	}
	/**
	 * SRD 전용 암호화
	 * @param key
	 * @param value
	 * @return
	 * @throws InvalidAlgorithmParameterException
	 */
	public byte[] doEncriptAESCBC(int ackType, byte[] value) throws InvalidAlgorithmParameterException {
		byte[] originalByte = value;

		try {
			SecretKeySpec skeySpec = new SecretKeySpec(ackType == 1 ? this.a128 : this.a256, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(this.lv));
			
			originalByte = cipher.doFinal(value);
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			// logger.debug(e.toString());
			e.printStackTrace();
		}
		
		return originalByte;
	}
	/**
	 * byte[] to hex : unsigned byte(바이트) 배열을 16진수 문자열로 바꾼다.
	 *
	 * @param ba        byte[]
	 * @return
	 */
	public static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	}
	/**
	 * SHA256 암호화
	 * @param encKey
	 * @param encData
	 * @return
	 */
	public String doEncriptSHA256(String encKey, String encData) {
		try {
			String message = encKey+":"+encData;
		 	MessageDigest md = MessageDigest.getInstance("SHA-256"); 
	 		md.update(message.getBytes());
		 
	 		byte byteData[] = md.digest();
	 		StringBuffer sb = new StringBuffer(); 
	 		for(int i=0; i<byteData.length; i++) {
	 			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
	 		}
	 		return sb.toString();
		} catch(NoSuchAlgorithmException e){
		    logger.debug(e.toString()); 
		    return null;
		}
	}
}
