package mcip.framework.util;

import java.util.UUID;

public class UuidUtil {
     
    public static String getUuid() {
    	String rtnStr = "";    	
    	rtnStr = UUID.randomUUID().toString();    	    	
    	return rtnStr;
    }
}