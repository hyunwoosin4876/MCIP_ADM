package mcip.framework.util;

import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Cell;

public class ExcellUtil {
	public String getCellValue(Cell cell){
		String value = "";
		switch (cell.getCellTypeEnum()) {
		case FORMULA:
			value = cell.getCellFormula();
			break;
		case NUMERIC:
			value = "" + new BigDecimal(cell.getNumericCellValue());
			break;
		case STRING:
			value = "" + cell.getStringCellValue();
			break;
		case BLANK:
			value = "";
			break;
		case ERROR:
			value = "" + cell.getErrorCellValue();
			break;
		case BOOLEAN:
			value = "" + cell.getBooleanCellValue();
			break;
		case _NONE:
			value = "";
			break;
		default:
		}
		
		return value.trim();
	}
}
