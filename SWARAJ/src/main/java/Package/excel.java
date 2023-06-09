package Package;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeTest;

public class excel {
	
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;      //XML SPREAD SHEET FORMAT  AFTER 2007   //HORRAIBLE SPREAD SHEET FORMAT BEFORE 2007
	public static XSSFSheet sheet;                         //XLSX                                    //XLS
	
	@BeforeTest(alwaysRun=true)	
	public static void ExcelConnect() {
		
		try {
			fis = new FileInputStream("F:\\GAURAV\\eclips_ssd\\SWARAJ\\src\\main\\resources\\testdata.xlsx");
			workbook = new XSSFWorkbook(fis);
		} 
		catch (IOException e) {
			
		} 
		
		sheet = workbook.getSheetAt(0);
		
		XSSFRow rrr=sheet.getRow(0);
		XSSFCell ccc=rrr.getCell(0);
	
		
	}
	
	public static String getStringCellData(String TestCaseName,String ColumnName) {
		//ExcelConnect();
		String value="";
		Iterator<Row> rowiterator=sheet.rowIterator();
		while(rowiterator.hasNext()){
			Row row=rowiterator.next();
			int column=getColumnNumber(ColumnName);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(TestCaseName)) {
				switch(row.getCell(column).getCellType()) {
				case STRING:
					return value=row.getCell(column).getStringCellValue();
				case BLANK:
					break;
				default:
					break;
			}
		}		
	}
		return value;
	}
	
	public void getCellData(String TestCaseName,String ColumnName) {
		Iterator<Row> rowiterator=sheet.rowIterator();
		while(rowiterator.hasNext()){
			Row row=rowiterator.next();
			int column=getColumnNumber(ColumnName);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(TestCaseName)) {
				
				switch(row.getCell(column).getCellType()) {
				
				case STRING:
					row.getCell(column).getStringCellValue();
					break;
					
				case NUMERIC:
					row.getCell(column).getNumericCellValue();
					break;
				case BOOLEAN:
					row.getCell(column).getBooleanCellValue();
					break;
				default:
					break;
							
				}
			}
		}
		}
	
	public static int getColumnNumber(String ColumnName) {
		Iterator<Cell> coliterator=sheet.getRow(0).cellIterator();
		while(coliterator.hasNext()) {
			Cell cell=coliterator.next();
			while(cell.getStringCellValue().equalsIgnoreCase(ColumnName)) {
				return cell.getColumnIndex();
			}
		}
		
		return 0;
	}
	
	public static double getNumericCellData(String TestCaseName,String ColumnName) {
		
		double value=0.0;
		Iterator<Row> rowiterator=sheet.rowIterator();
		while(rowiterator.hasNext()){
			Row row=rowiterator.next();
			int column=getColumnNumber(ColumnName);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(TestCaseName)) {
				System.out.println("CellType" +row.getCell(column).getCellType());
				switch(row.getCell(column).getCellType()) {
				case NUMERIC:
					return value=row.getCell(column).getNumericCellValue();
				case BLANK:
					break;
				default:
					break;
			}
		}		
	}
		return value;
	}


}
