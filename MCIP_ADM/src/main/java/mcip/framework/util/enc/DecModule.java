package mcip.framework.util.enc;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mcip.egovframe.util.EgovStringUtil;
import mcip.framework.util.StringUtil;

public class DecModule {
	private static final Logger logger = LoggerFactory.getLogger(DecModule.class);
	/**
	 * SRD 용 키 및 LV
	 */
	byte[] a128 = {18, 89, 101, 17, 69, 53, 1, -88, -56, -2, -103, 16, 18, 52, -2, 9};
	byte[] a256 = {104, 89, 16, 17, -111, 0, 1, -120, 73, -2, 18, -88, 18, 52, -23, 9, 1, 49, 68, 64, 99, 105, 119, -1, 0, 14, 3, -43, 53, 18, 17, 38};
	byte[] lv = {1, -6, -70, -77, 29, 14, 4, 16, 105, 34, -35, -128, -52, -97, -54, 20};
	/** 
	 * AES 방식의 복호화
	 *
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public String doDecriptAES(String key, String value) {
		String originalString = value;
		if(!EgovStringUtil.isEmpty(originalString)){	    			    	
			try {
				if(key.length() != 16 && key.length() != 24 && key.length() != 32 ){
					key = StringUtil.strLengthCnvt(key);
				}
				SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.DECRYPT_MODE, skeySpec);
				byte[] original = cipher.doFinal(hexToByteArray(value));
				originalString = new String(original);
			} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
				logger.debug(e.toString());
			}
		}
		return originalString;
	}
	/**
	 * SRD 전용 해제화
	 * @param key
	 * @param value
	 * @return
	 * @throws InvalidAlgorithmParameterException
	 */
	public byte[] doDecriptAESCBC(int ackType, byte[] value) throws InvalidAlgorithmParameterException {
		byte[] originalByte = value;

		try {
			SecretKeySpec skeySpec = new SecretKeySpec(ackType == 1 ? this.a128 : this.a256, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(this.lv));
			
			originalByte = cipher.doFinal(value);
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			logger.debug(e.toString());
			e.printStackTrace();
		}
		
		return originalByte;
	}
	
	/**
	 * hex to byte[] : 16진수 문자열을 바이트 배열로 변환한다.
	 *
	 * @param hex    hex string
	 * @return
	 */
	public static byte[] hexToByteArray(String hex) {
		/*if (hex == null || hex.length() == 0) {
			return null;
		}
		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;*/
		if (hex == null || hex.length() % 2 != 0) {
            return new byte[]{};
        }

        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
	}

	public String rsaDecript(String parameter, PrivateKey key){
		String decryptedValue = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			byte[] encryptedBytes = hexToByteArray(parameter);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			logger.debug(e.toString());
		} catch (InvalidKeyException e) {
			logger.debug(e.toString());
		} catch (IllegalBlockSizeException e) {
			logger.debug(e.toString());
		} catch (BadPaddingException e) {
			logger.debug(e.toString());
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.toString());
		}
        return decryptedValue;
	}

	/**
	 * base64 디크립트
	 * @param encData
	 * @return
	 */
	public String doDecriptBase64(String encData) {
		Decoder decoder = Base64.getDecoder(); 
		byte[] decodedBytes = decoder.decode(encData.trim().getBytes());

		return new String(decodedBytes);
	}
}
