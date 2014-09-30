package com.systemware.selenium.redmine._4_13;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.security.Users;

public class RM1979 {
	private WebDriver driver;
	private String fromUser = "admin";
	private String toUser = "TestUser1";
	private String copiedUserPwd = "password1";
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "Security", "Users");
		Users.copyUsers(driver, fromUser, toUser, copiedUserPwd);
		
		verifyUserWasCopied(toUser);
		verifyUserCanLogin();

		NavBar.navigate(driver, "Administration", "Security", "Users");
		Users.deleteUser(driver, toUser);
	}

	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}

	public boolean verifyUserWasCopied(String txtValue){
        boolean b = false;
	    try{
	        b = driver.getPageSource().contains(txtValue);
	        return b;
	    }
	    catch (Exception e){
	        System.out.println(e.getMessage());
	    }     
	     return b;
	}
	
	private void verifyUserCanLogin() throws InterruptedException {
		Authorization.logout(driver);
		Authorization.userLogin(driver, toUser, copiedUserPwd);
		Authorization.logout(driver);
		Authorization.adminLogin(driver);
		Thread.sleep(500);
	}
}