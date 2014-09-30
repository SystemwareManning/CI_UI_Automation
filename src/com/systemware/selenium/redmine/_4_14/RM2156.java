package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMCreator;
import com.systemware.selenium.system.CAMs;

public class RM2156 {
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
		CAMs.install(driver, CAMLocations.swrm);
		CAMs.install(driver, CAMLocations.swiauth);
		CAMs.install(driver, CAMLocations.swstore);
		CAMs.install(driver, CAMLocations.swmonitor);
		
		assertTrue(verifyThereAreNoWarnings());
	}
	
	@After
	public void tearDown() throws Exception {
		CAMs.uninstall(driver, "swmonitor");
		CAMs.uninstall(driver, "swstore");
		CAMs.uninstall(driver, "swiauth");
		CAMs.uninstall(driver, "swbaserm");
		CAMs.uninstall(driver, "swbaseuser");
		
		Authorization.logout(driver);
		driver.quit();
	}
	
	public static boolean verifyThereAreNoWarnings() {
		try { 
			driver.findElement(By.xpath("//div[@class='general-warning']"));
			return false;
		} catch (Exception e) { 
			return true;
		}
	}
}
