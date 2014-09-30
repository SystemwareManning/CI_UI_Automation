package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMs;
import com.systemware.selenium.ui.UITemplates;

public class RM2195 {
	private static WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.install(driver, CAMLocations.swbaseuser);
		
		NavBar.navigate(driver, "Administration", "User Interface", "UI Templates");
		
		UITemplates.filterTemplates(driver, "S0017");
		UITemplates.editUITemplate(driver, "S0017");
		
		driver.findElement(By.xpath("//div[text()='Role:']")).click();
		driver.findElement(By.xpath("//a[text()='Dynamic Searches']")).click();
		addDynamicUpdate();

		assertTrue(areThereDefaultPicklistFilters());
		
		
		driver.findElement(By.xpath("//input[@title='Click to delete this dynamic update.']")).click();
		
		((JavascriptExecutor) driver).executeScript("scroll(250,0);");
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//span[@class='header-text']/a[text()='User Name']")).click();
		addDynamicUpdate();
		assertFalse(areThereDefaultPicklistFilters());
		
		( ( JavascriptExecutor ) driver ).executeScript( "window.onbeforeunload = function(e){};" );
	}
	
	@After
	public void tearDown() throws Exception {

		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "swbaseuser");		
		Authorization.logout(driver);
		driver.quit();
	}
	
	public boolean areThereDefaultPicklistFilters() throws InterruptedException {
		Thread.sleep(1000);
		try { 
			driver.findElement(By.xpath("//label[text()='Default Picklist Filters:']")).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void addDynamicUpdate() {
		try {
			driver.findElement(By.xpath("//input[@value='Add Dynamic Update']")).click();
		}
		catch (Exception e) {
			addDynamicUpdate();
		}
	}
}
