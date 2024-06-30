package com.OhrmProject.Scenario2.Testcases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.OhrmProject.Scenario2.Pages.AdminPage;
import com.OhrmProject.Scenario2.Pages.LoginPageohrm;

public class BaseClass {
	
	WebDriver driver;
	LoginPageohrm lp;
	AdminPage ap;
	
	@BeforeTest
    public void driverInitialize() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		lp=new LoginPageohrm(driver);
		ap=new AdminPage(driver);
	}
	
	@BeforeClass
	public void pageSetup() {
		lp.doLogin("Admin", "admin123");
	}
	
}