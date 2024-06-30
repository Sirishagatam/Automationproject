package com.OhrmProject.Scenario1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class DataDrivenTestingExcelScenario1 {
	File file,rfile;
	FileOutputStream fos;
	FileInputStream fis;
	String fpath="C:\\Users\\sirim\\SeleniumExcel\\ProjectLoginExcel.xlsx";
	XSSFWorkbook wb,rwb;
	XSSFSheet sheet,rsheet;
	XSSFRow row,rrow;
	XSSFCell cell,rcell;
	int i=0;

  
  @Test(priority=1,dataProvider="getLoginData")
  public void loginDataWriteToExcel(String un,String pw,String rs) throws FileNotFoundException {

	 
	//create Row
	row=sheet.createRow(i);
	
	//create first cell
	cell=row.createCell(0);
	//Add data to first cell in Row
	cell.setCellValue(un);
	
	//create second cell
	cell=row.createCell(1);
	//Add data to second cell in Row
	cell.setCellValue(pw);
	//create third cell
	cell=row.createCell(2);
	//Add data to second cell in Row
	cell.setCellValue(rs);

	i++;
  
  }
 
  @DataProvider
  public Object[][] getLoginData() {
	  return new Object[][] {
		  new Object[] {"Username","Password","Result"},
		  new Object[] {"Admin","admin123","Not Run"},
		  new Object[] {"User1","admin123","Not Run"},
		  new Object[] {"User2","admin13","Not Run"},
		  new Object[] {"User3","admin23","Not Run"},
		  new  Object[] {"User4","admin12","Not Run"},
	  };
  }

  @Test(priority=2)
  public void closeWriteExcelFile() throws IOException {
	  wb.write(fos);
	  wb.close();
	  fos.close();
  }
  
   
  @Test(priority=3)
  public void loginDataReadFromExcel() throws IOException {
	  
	  
	  rfile=new File(fpath);
	  fis=new FileInputStream(rfile);
	  rwb=new XSSFWorkbook(fis);
	  rsheet=rwb.getSheet("Login Data");
	  int rowno=rwb.getSheet("Login Data").getPhysicalNumberOfRows();
	  int cellno=rwb.getSheet("Login Data").getRow(0).getPhysicalNumberOfCells();
	  System.out.println("Total no of Rows in sheet:"+rowno+"\n"+"Total no of cells in row:"+cellno);
	  //Object data[][] = new Object[rowno-1][cellno];
	  for(int i=0;i<rowno-1;i++)
	  {
		  
		  rrow=rsheet.getRow(i+1);
		  System.out.println("ROW:"+rrow);
		    
		  for(int j=0;j<cellno;j++)
		  {
			 /* if(rrow==null)
			  {
				  data[i][j]="";
			  }
			  else
			  {*/
				  
				  //Read cell
				  rcell=rrow.getCell(j);
				 /* if(rcell==null)
				  {
					  data[i][j]="";
				  }
				  else
				  {*/
					  System.out.println("CELL:"+rcell);
					  //Read cell value
					  System.out.println(rcell.getStringCellValue());
		//			  data[i][j]=rcell.getStringCellValue();
				  //}
			  //}
		  }
	  }
	  
	  //System.out.println("data array is:" + data);
	  
  }
  
  @BeforeTest
  public void beforeTest() throws FileNotFoundException {
  file=new File(fpath);
  fos=new FileOutputStream(file);
  wb= new XSSFWorkbook();
  sheet=wb.createSheet("Login Data");
  
  }

  @AfterTest
  public void afterTest() throws IOException {
	  rwb.close();
	  fis.close();
  }

  
}
