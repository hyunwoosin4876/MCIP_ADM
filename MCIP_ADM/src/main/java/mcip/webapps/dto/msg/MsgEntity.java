package mcip.webapps.dto.msg;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class MsgEntity {
	/**
	 * MsgCode
	 */
	private String msgCode = "0040";
	/**
	 * Messge Ment
	 */
	private String msgMent = null;
	/**
	 * Message Type
	 * 0 : alert
	 * 1 : View
	 */
	private String msgType = null;
	/**
	 * Msg Lange Type
	 * @return
	 */
	private String location = "ko_kr";
	/**
	 * 임시 데이터들
	 */
	private Object pValue = null;
	
	/**
	 * Msg Lange Type
	 * @return
	 */
	private String msgFlag = "N";
	
	/**
	 * 임시 데이터들
	 */
	private Object resultData = null;
	
}
