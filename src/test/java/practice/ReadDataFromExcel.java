package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;





public class ReadDataFromExcel {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
	
	//step 1: open the document in java readable format
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
	
	//step 2: create a workbook
	Workbook wb = WorkbookFactory.create(fis);
	
	//step 3: get control of sheet
	Sheet sh =  wb.getSheet("Organizations");
	
	//step 4: get the control of row
      Row rw = sh.getRow(7);
	
	//step 5: get the control of respective cell
      Cell ce = rw.getCell(4);
	
	//step 6: read the data inside the cell
	String value = ce.getStringCellValue();
	System.out.println(value);
	
}
}
