package com.OhrmProject.Scenario2.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.OhrmProject.Scenario2.Testcases.BaseClass;

public class LoginPageohrm extends BaseClass {
	
	private WebDriver driver;
	
	//constructor
	public LoginPageohrm(WebDriver driver) {
		this.driver=driver;
	}
	
	//locators
	By name=By.name("username");
	By passkey=By.name("password");
	By loginbtn=By.xpath("//button[@type='submit']");
		
	//methods
	public String getUrl() {
		return driver.getCurrentUrl();
	}
		
	public void doLogin(String un,String pw) {
    	try {
		driver.findElement(name).sendKeys(un);
    	} catch (NoSuchElementException ex) {
    	}
		//Password
    	try {
		driver.findElement(passkey).sendKeys(pw);
    	} catch (NoSuchElementException ex) {
    	}
    	//Login button
    	try {
		driver.findElement(loginbtn).click();
    	} catch (NoSuchElementException ex) {
    	}
    
	}


}
