package mcip.framework.util;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelDownloadView extends AbstractExcelView {

	@Override
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res) throws Exception {
		//  Auto-generated method stub
		String userAgent = req.getHeader("User-Agent");
		String fileName =  (String)model.get("fileName");

		if(userAgent.indexOf("MSIE") > -1) {
			fileName = URLEncoder.encode(fileName, "utf-8");
		} else {
			fileName = new String(fileName.getBytes("euc-kr"), "iso-8859-1");
		}
		
		res.setContentType("Application/Msexcel");
		res.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls;");
		res.setHeader("Content-Transfer-Encoding", "binary");
		
		List<Map<String, Object>> downList = (List<Map<String, Object>>)model.get("list");
		
		// title cell style
        HSSFCellStyle tcs = this.getTitleCellStyle(workbook);
        
        // header cell style
        HSSFCellStyle hcs = this.getHeaderCellStyle(workbook);
        
        // content cell style
        HSSFCellStyle ccs = this.getContentCellStyle(workbook);
		
		String sheetName = (String)model.get("sheetName");
		HSSFSheet sheet = createFirstSheet(workbook, sheetName);
		
		String excelTitle = (String)model.get("excelTitle");  
		createColumnLabel(sheet, tcs, hcs, sheetName, excelTitle, excelTitle.split(",").length);
		
		for(int i=0; i < downList.size(); i++) {
			createPageRow(sheet, ccs, downList.get(i), i);
		}
		logger.debug("Excel DownLoad End");
	}
	  
	private HSSFSheet createFirstSheet(HSSFWorkbook workbook, String sheetName){
		 HSSFSheet sheet = workbook.createSheet();
		 
		 workbook.setSheetName(0, sheetName);
		 sheet.autoSizeColumn((short)1);
		 sheet.setColumnWidth(1, 256*30);
		 
		 return sheet;
	 }
	 
	private void createColumnLabel(HSSFSheet sheet, HSSFCellStyle tcs, HSSFCellStyle hcs, String sheetName, String excelTitle, int length) {
	     HSSFRow headerRow = sheet.createRow(0);
		 HSSFCell cell = null; 
		 
		 cell = headerRow.createCell(0);
		 cell.setCellValue(sheetName);
		 cell.setCellStyle(tcs);
		 sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, length -1));
		 
		 HSSFRow firstRow = sheet.createRow(1);
		 
		 String[] title = excelTitle.split(",");
		 
		 for (int i = 0; i < title.length; i++)
		 {
			 cell = firstRow.createCell(i);
			 cell.setCellValue(title[i]);
			 cell.setCellStyle(hcs);
		 }
	 }
	                                                            
	 private void createPageRow(HSSFSheet sheet, HSSFCellStyle ccs, Map<String, Object> downMap, int rowNum) throws Exception {
		HSSFRow row = sheet.createRow(rowNum + 2);
		  
		HSSFCell cell = null;
		
		String value = ""; 
		
		for (int i= 0; i < downMap.size(); i++) {
			sheet.autoSizeColumn((short)i);
		    sheet.setColumnWidth((int) i, (sheet.getColumnWidth(i))+1024);
			
			cell = row.createCell(i);
			String str = downMap.get("CELL_"+i).toString();
			
			value = String.valueOf(downMap.get("CELL_"+i));
			
			if (value.equals("null")) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(value);
			}
			cell.setCellStyle(ccs);
		}
	}
	 
    /**
     * cellStyle 정의
     * @param workbook
     * @return 타이틀 부분 셀 스타일
     */
    public HSSFCellStyle getTitleCellStyle(HSSFWorkbook workbook){
        
        // title font    : 순서 주의! cell style 보다 font 가 우선. font 를 cell style 에 적용.
        HSSFFont tf = workbook.createFont();
        tf.setFontHeightInPoints((short) 18);
        tf.setBold(true);
        
        // title cell style
        HSSFCellStyle tcs = workbook.createCellStyle();
        
        tcs.setAlignment(HorizontalAlignment.CENTER);					// 가로 가운데 정렬
        tcs.setVerticalAlignment(VerticalAlignment.CENTER);				// 세로 가운데 정렬
        tcs.setFont(tf);
        tcs.setFillForegroundColor(IndexedColors.YELLOW.getIndex());	// 배경색(패턴과 한 쌍을 이룸)
        tcs.setFillPattern(FillPatternType.SOLID_FOREGROUND);			// 배경색 그리기 패턴
        
        return tcs;
    }
    
    /**
     * cellStyle 정의
     * @param workbook
     * @return 헤더(컬럼명) 부분 셀 스타일
     */
    public HSSFCellStyle getHeaderCellStyle(HSSFWorkbook workbook){
        
        HSSFCellStyle hcs = workbook.createCellStyle();

        hcs.setAlignment(HorizontalAlignment.CENTER);							// 가운데 정렬
        hcs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());	// 배경색(패턴과 한 쌍을 이룸)
        hcs.setFillPattern(FillPatternType.SOLID_FOREGROUND);					// 배경색 그리기 패턴
        hcs.setBottomBorderColor(IndexedColors.BLACK.getIndex());               // 표 색상(표 선 : 상하좌우) 
        hcs.setBorderTop(BorderStyle.THIN);
        hcs.setBorderBottom(BorderStyle.THIN);
        hcs.setBorderLeft(BorderStyle.THIN);
        hcs.setBorderRight(BorderStyle.THIN);
        
        return hcs;
    }
    
    /**
     * cellStyle 정의
     * @param workbook
     * @return 내용부분 셀 스타일
     */
    public HSSFCellStyle getContentCellStyle(HSSFWorkbook workbook){
        
        HSSFCellStyle ccs = workbook.createCellStyle();
        ccs.setBottomBorderColor(IndexedColors.BLACK.getIndex());    // 표 색상(표 선 : 상하좌우)
        ccs.setBorderTop(BorderStyle.THIN);
        ccs.setBorderBottom(BorderStyle.THIN);                
        ccs.setBorderLeft(BorderStyle.THIN);
        ccs.setBorderRight(BorderStyle.THIN);
        
        return ccs;
    }
}

