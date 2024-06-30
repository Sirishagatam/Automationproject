package com.OhrmProject.Scenario1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import config.Utility;

public class DataDrivenFrameworkScenario1 {
	String path="C:\\Users\\sirim\\SeleniumExcel\\ProjectLoginExcel.xlsx";
	File file;
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	WebDriver driver;
	String expurl="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index",acturl;
	int id=1;
	ExtentSparkReporter htmlReporter;
	ExtentReports report;
	ExtentTest test;
		
  @Test(dataProvider="getData")
  public void verifyloginOHRM(String un,String pw) throws InterruptedException {
	  
	  //Username
	  driver.findElement(By.name("username")).sendKeys(un);
	  //Password
	  driver.findElement(By.name("password")).sendKeys(pw);
	  Utility.capture(driver);
	  //Login button
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  //Thread.sleep(2000);
	  Utility.capture(driver);
	
	  acturl=driver.getCurrentUrl();
	  
	  System.out.println("Current URL is:"+acturl);
	  Assert.assertTrue(acturl.contains("dashboard"),"URL not as expected, verify login credentials!!!");
	  Utility.capture(driver);
	  System.out.println("Login is successful!!!");
  }
  
   
  @AfterMethod
  public void afterMethod(ITestResult result) {
	  row=sheet.getRow(id);
	  cell=row.getCell(2);
	  if(acturl.equals(expurl))
	  {
		driver.findElement(By.xpath("//i[@class=\"oxd-icon bi-caret-down-fill oxd-userdropdown-icon\"]")).click();
		driver.findElement(By.partialLinkText("Log")).click();
		cell.setCellValue("pass");
	  } 
	  else
	  {
		  driver.findElement(By.xpath("//p[@class=\"oxd-text oxd-text--p oxd-alert-content-text\"]")).getText();
		  cell.setCellValue("fail");
	  }
	  id++;
	  if(result.getStatus() == ITestResult.SUCCESS)
	  {
			test = report.createTest("Login with Valid Data");
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + ": Pass", ExtentColor.GREEN));
	  }
	  else if(result.getStatus() == ITestResult.FAILURE)
	  {
			test = report.createTest("Login with Invalid Data");
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + ": Fail", ExtentColor.RED));
			test.fail(result.getThrowable());
	  }
		  
  }
  
  @DataProvider
 	public Object[][] getData() {
 		int rowno=sheet.getPhysicalNumberOfRows();
 	    int cellno=sheet.getRow(0).getPhysicalNumberOfCells();
 		String[][] data=new String [rowno-1] [2];
 		//Traverse the row and cell and add to array
 		for(int i=0;i<rowno-1;i++)
 		{
 			System.out.println("i:"+i);
 			row=sheet.getRow(i+1);
 			for(int j=0;j<2;j++)
 			{
 				cell=row.getCell(j);
 				data[i][j]=cell.getStringCellValue();
 				System.out.println(data[i][j]+"\t");
 			}
 			//System.out.println("\n");
 		}
 		return data;
 		
 	}

  @BeforeTest
  public void beforeTest() throws IOException, InterruptedException {
	  //Create object for file,workbook,sheet;
	  file= new File(path);
	  fis=new FileInputStream(file);
	  wb=new XSSFWorkbook(fis);
	  sheet=wb.getSheet("Login Data");
	  fos=new FileOutputStream(file);
	  htmlReporter = new ExtentSparkReporter("OrangeHRMTestResult.html");
	  report = new ExtentReports();
	  report.attachReporter(htmlReporter);
		
	  report.setSystemInfo("Machine Name", "Lenovo");
	  report.setSystemInfo("OS", "Windows 11");
	  report.setSystemInfo("User", "Lekha");
	  report.setSystemInfo("Browser", "Google Chrome");
	  	  
	  // Configuration for look of report
	  htmlReporter.config().setDocumentTitle("Extent Report for OHRM Login using DDF");
	  htmlReporter.config().setReportName("OHRM Login Report");
	  htmlReporter.config().setTheme(Theme.STANDARD);
	  htmlReporter.config().setTimeStampFormat("EEEE MMMM dd yyyy, hh:mm a '('zzz')'");

	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	  System.out.println("Current URL:"+driver.getCurrentUrl());
  }
  
  @AfterTest
  public void afterTest() throws IOException {
	  wb.write(fos);
	  wb.close();
	  fis.close();
	  report.flush();
	  driver.quit();
  }
  }
