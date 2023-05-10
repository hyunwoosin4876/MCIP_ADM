package mcip.framework.util;

import mcip.framework.util.enc.DecModule;
import mcip.framework.util.enc.EncModule;

public class EncriptUtil {
	public String encModule(String encType, String encData, String encKey){
		EncModule em = new EncModule();
		String result = "";
		switch (encType) {
		case "SECU002002":
			result = em.doEncriptBase64(encData + encKey);
			break;
		case "SECU002003":
			// result = em.doEncriptMD5(encKey, encData);
			break;
		case "SECU002004":
			result = em.doEncriptSHA128(encKey, encData);
			break;
		case "SECU002005":
			result = em.doEncriptSHA256(encKey, encData);
			break;
		case "SECU002006":
			result = em.doEncriptAES(encKey, encData);
			break;
		default:
			break;
		}
		return result;
	}
	public String decModule(String decType, String encData, String encKey){
		DecModule dm = new DecModule();
		String result = "";
		switch (decType) {
		case "SECU002002":
			result = dm.doDecriptBase64(encData).replace(encKey, "");
			break;
		default:
			break;
		}
		return result;
	}
}
