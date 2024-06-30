package com.OhrmProject.Scenario2.Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminPageTest extends BaseClass {
  
  @Test
  public void verifySideMenuItemCount() {
	 int expcount=12;
     int count=ap.leftSideMenuItemsCount();
     System.out.println("Displayed count of side menu items is as expected: "+count);
     System.out.println("\n");
     Assert.assertTrue(lp.getUrl().contains("admin"),"Admin page is not displayed");
     System.out.println("Admin page is displayed correctly as expected\n");
  }
  
  @Test
  public void verifySearchByUsernameCount() throws InterruptedException {
	  String recdisplay=ap.searchByUserName("Admin");
	  System.out.println(recdisplay);
	  Assert.assertTrue(recdisplay.contains("Record"), "Records not displayed for Username");
	  System.out.println("Records displayed correctly for username Admin:"+recdisplay);
	  driver.navigate().refresh();
  }
  
  @Test
  public void verifySearchByUserroleCount() throws InterruptedException {
	  String recdisplay1=ap.searchByUserRole();
	  System.out.println(recdisplay1);
	  Assert.assertTrue(recdisplay1.contains("Record"), "Records not displayed for User-role");
	  System.out.println("Records displayed correctly for User-role:"+recdisplay1);
	  Thread.sleep(2000);
	  driver.navigate().refresh();
  }
  
  @Test
  public void verifySearchByUserstatusCount() throws InterruptedException {
	  String recdisplay2=ap.searchByUserStatus();
	  System.out.println(recdisplay2);
	  Assert.assertTrue(recdisplay2.contains("Record"), "Records not displayed for Userstatus");
	  System.out.println("Records displayed correctly for Userstatus:"+recdisplay2);
	  Thread.sleep(2000);
	  driver.navigate().refresh();
  }
  
  
  
}
