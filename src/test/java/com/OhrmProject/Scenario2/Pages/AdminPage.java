package com.OhrmProject.Scenario2.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage {
	
	private WebDriver driver;
	
	//constructor
	public AdminPage(WebDriver driver) {
		this.driver=driver;
	}

	//locators
	By adminsidemenu = By.xpath("//span[text()='Admin']");
	By optionsleftmenu = By.xpath("//ul[contains(@class,'oxd-main-menu')]");
	By listtag = By.tagName("li");
	By username = By.xpath("(//input[@class=\"oxd-input oxd-input--active\"])[2]");
	By userroledrpmenu = By.xpath("(//i[contains(@class,'oxd-select-text--arrow')])[1]");
	By admindrpmenuoption = By.xpath("(//div[contains(@class,'oxd-select-option')])[2]");
	By userstatus = By.xpath("(//i[@class=\"oxd-icon bi-caret-down-fill oxd-select-text--arrow\"])[2]");
	By search = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
	By record = By.xpath("//div[contains(@class,'orangehrm-horizontal-padding')]//span[contains(@class,'oxd-text')]");
	By enabled = By.xpath("//span[text()='Enabled']");
	
	//methods
	public int leftSideMenuItemsCount() {

		int ct=0;
		WebElement sidemenu=driver.findElement(optionsleftmenu);
		List<WebElement> smenulist=sidemenu.findElements(listtag);
		
		for(WebElement m:smenulist)
		{
			System.out.println(ct + ". "+m.getText());
			ct++;
		}
		System.out.println("menuct:"+ct);
		return ct;
		
	}
	
	public String searchByUserName(String un) throws InterruptedException {
	
		//click on admin option in side menu
		driver.findElement(adminsidemenu).click();
		//Username textbox
		driver.findElement(username).sendKeys(un);
		//search
		driver.findElement(search).click();
		Thread.sleep(3000);
		//display records count
		String recno=driver.findElement(record).getText();
		//click on admin option in side menu
		driver.findElement(adminsidemenu).click();
		
		return recno;
		
	}
	
	public String searchByUserRole() throws InterruptedException {

		//click on admin option in side menu
		driver.findElement(adminsidemenu).click();
		//Select Dropdownmenu Arrow in Userrole
		driver.findElement(userroledrpmenu).click();
		//Select Admin from menu
		driver.findElement(admindrpmenuoption).click();
		//search 
		driver.findElement(search).click();
		Thread.sleep(2000);
		//display records count
		String recno=driver.findElement(record).getText();
		return recno;
		
	}

	public String searchByUserStatus() throws InterruptedException {

		//click on admin option in side menu
		driver.findElement(adminsidemenu).click();
		//Select Dropdownmenu Arrow in Userstatus
		driver.findElement(userstatus).click();
		//Select Enabled from menu
		driver.findElement(enabled).click();
		//search
		driver.findElement(search).click();
		Thread.sleep(2000);
		//display records count
		String recno=driver.findElement(record).getText();
		return recno;
		
	}
	
}
