package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;

public class RM1693 {
	private static WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		
		for (int i = 0; i < 5; i++) {
			driver.findElement(By.xpath("//a[text()='Next']")).click();
			Thread.sleep(300);
		}
		
		NavBar.navigate(driver, "Administration", "Import & Export", "Import License");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "Import & Export", "System Configuration");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "Security", "Users");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "Security", "Groups");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "Security", "Roles");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "Security", "Permissions");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "Security", "Filters");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "Security", "Password Policy");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "System", "Advanced Configuration");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "System", "Auditing");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "System", "CAM Creator");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "System", "Content Servers");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "System", "Retention Schedules");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "User Interface", "UI Templates");
		assertTrue(verifyHelpTextDoesNotExist());
		
		NavBar.navigate(driver, "Administration", "Utilities", "Password Encryption");
		assertTrue(verifyHelpTextDoesNotExist());
	}
	
	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}
	
	public static boolean verifyHelpTextDoesNotExist() {
		try {
			driver.findElement(By.xpath("//a[text()='Help']")).click();
			driver.findElement(By.xpath("//a[text()='Show Tips and Hints']")).click();
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}
