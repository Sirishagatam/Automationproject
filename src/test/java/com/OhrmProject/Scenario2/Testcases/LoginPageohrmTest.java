package com.OhrmProject.Scenario2.Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageohrmTest extends BaseClass {
  @Test
  public void verifyLogin() {
	  lp.doLogin("Admin", "admin123");
	  Assert.assertTrue(lp.getUrl().contains("dashboard"),"Login is not successful,verify credentials");
	  System.out.println("Login is successful!!!");
  }
}
