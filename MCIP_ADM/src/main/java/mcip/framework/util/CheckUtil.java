/**
 * 
 */
package mcip.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckUtil {
	private static final Logger logger = LoggerFactory.getLogger(CheckUtil.class);

	public boolean checkPWValidationCheck(String userpw, String userid) {

		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String alpha2 = "abcdefghijklmnopqrstuvwxyz";
		String number = "1234567890";
		String sChar = "-_=+\\|()*&^%$#@!~`?></;,.:'";

		int sChar_Count = 0;
		boolean alphaCheck = false;
		boolean alpha2Check = false;
		boolean numberCheck = false;

		if (8 < userpw.length()) {
			for (int i = 0; i < userpw.length(); i++) {
				if (sChar.indexOf(userpw.charAt(i)) != -1) {
					sChar_Count++;
				}
				if (alpha.indexOf(userpw.charAt(i)) != -1) {
					alphaCheck = true;
				}
				if (alpha2.indexOf(userpw.charAt(i)) != -1) {
					alpha2Check = true;
				}
				if (number.indexOf(userpw.charAt(i)) != -1) {
					numberCheck = true;
				}
			}

			char temp;
			for (int i = 0; i < userpw.length() - 2; i++) {
				temp = userpw.charAt(i);
				if (temp == userpw.charAt(i + 1) && temp == userpw.charAt(i + 2)) {
					logger.debug("동일한 문자를 3회 이상 반복 사용할 수 없습니다.");
					return false;
				}
			}

			int cnt2 = 1, cnt3 = 1;
			for (int i = 0; i < userpw.length() - 1; i++) {
				char temp_pass1 = userpw.charAt(i);
				char temp_p = userpw.charAt(i + 1);

				int next_pass = (int) temp_pass1 + 1;
				int temp_pass2 = (int) temp_p;

				if (temp_pass2 == next_pass)
					cnt2 = cnt2 + 1;
				else
					cnt2 = 1;
				if (temp_pass1 == temp_p)
					cnt3 = cnt3 + 1;
				else
					cnt3 = 1;

				if (cnt2 > 2)
					break;
				if (cnt3 > 2)
					break;
			}
			if (cnt2 > 2) {
				logger.debug("비밀번호에 연속된 문자나 순차적인 숫자를 3자 이상 사용해서는 안됩니다.");
				return false;
			}

			if (sChar_Count < 1 || alphaCheck != true || numberCheck != true || alpha2Check != true) {
				logger.debug("비밀번호는 9자 이상  영어 대소문자,숫자,특수문자의 혼합으로 입력해야 합니다.");
				return false;
			}

			if (userid != null) {
				if (userpw == userid) {
					logger.debug("아이디와 동일한 비밀번호는 사용할 수 없습니다.");
					return false;
				}

			}

		} else {
			logger.debug("비밀번호는 9자 이상 영어 대소문자,숫자,특수문자의 혼합으로 입력해야 합니다.");
			return false;
		}

		return true;

	}

	/**
	 * 이메일 형식체크
	 * 
	 * @return
	 */
	public boolean regEmailChkFull(String value) {
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);

		if (!m.matches()) {
			return false;
		}

		return true;
	}

	/**
	 * 휴대폰 형식 체크
	 * @param pattern
	 * @return
	 */
	public boolean regCellNumChkFull(String pattern) {
		String regex = "^01(?:0|1[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(pattern);
		if (!m.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 전화번호 형식 체크
	 * @param pattern
	 * @return
	 */
	public boolean regPhoneNumChkFull(String pattern) {
		String regex = "^\\d{2,3}[.-]?\\d{3,4}[.-]?\\d{4}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(pattern);

		if (!m.matches()) {
			return false;
		}
		return true;
	}
}
