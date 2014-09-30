package com.systemware.selenium.redmine._4_13;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.CISettings;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMs;

public class RM1518 {
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
		CAMs.install(driver, CAMLocations.redmine992);
		
		
		driver.get(CISettings.baseUrl + "mobile");
			
		driver.findElement(By.xpath("//a[@class='ui-btn-noborder ui-nodisc-icon ui-link ui-btn-left ui-btn ui-icon-bars ui-btn-icon-notext ui-shadow']")).click();	
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//a[@class='ui-collapsible-heading-toggle ui-btn ui-icon-carat-d ui-btn-icon-right ui-btn-inherit'][1]")).click();		
		driver.findElement(By.xpath("//label[text()='REDMINE #992 TEST']")).click();		
		Thread.sleep(500); 
		
		driver.findElement(By.xpath("//input[@value='Select']")).click();
		driver.findElement(By.xpath("//a[text()='Administrators']")).click();
		
		Thread.sleep(500); 
		driver.findElement(By.xpath("//input[@value='Select']")).click();
		driver.findElement(By.xpath("//a[text()='System Administrator']")).click();
		
		Thread.sleep(500); 
		driver.findElement(By.xpath("//button[text()='CLEAR FORM']")).click();
		
		driver.findElement(By.id("userid")).clear();
		driver.findElement(By.id("userid")).sendKeys("3");
		driver.findElement(By.xpath("//label[text()='User ID']")).click();
		
		Thread.sleep(500); 
		assertTrue(verifySelectsAreHidden());
		

	}
	
	@After
	public void tearDown() throws Exception {
		driver.get(CISettings.baseUrl);
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "swbaseuser");
		CAMs.uninstall(driver, "redmine992");
		Authorization.logout(driver);
		driver.quit();
	}
	
	public static boolean verifySelectsAreHidden() {
		try {
			driver.findElement(By.xpath("//input[@value='Select']")).click();
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}