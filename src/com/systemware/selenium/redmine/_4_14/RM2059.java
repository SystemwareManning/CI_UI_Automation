package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.find.ContentNavigator;
import com.systemware.selenium.system.AdvancedConfiguration;
import com.systemware.selenium.system.CAMs;

public class RM2059 {
	private static WebDriver driver;
	private WebElement element;

	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		
		CAMs.installSWCS(driver);
		
		NavBar.navigate(driver, "Administration", "System", "Advanced Configuration");
		
		AdvancedConfiguration.toggleCollapsed(driver, "CAMs");
		AdvancedConfiguration.toggleCollapsed(driver, "swcs");
		AdvancedConfiguration.toggleCollapsed(driver, "config");
		
		AdvancedConfiguration.openFile(driver, "cciesconfig.xml");
		
		AdvancedConfiguration.enableSWCSCrawler(driver);
		
		NavBar.navigate(driver, "Find", "Content Navigator"); 
		
		ContentNavigator.expandMenu(driver, "LOCALCS");
		ContentNavigator.expandMenu(driver, "SELENIUM");
		ContentNavigator.expandMenu(driver, "AUDIT");
			
		ContentNavigator.addReportToFav(driver, "AUDIT");
		
		ContentNavigator.expandMenu(driver, "My Favorites");
		ContentNavigator.expandMenu(driver, "General [System Administrator]");
		
		element = driver.findElement(By.xpath("//a[@title='LOCALCS - SAMPLE AUDIT FILE']"));
		
		Actions builder = new Actions(driver);
		Actions hover = builder.moveToElement(element);
		hover.build().perform();
		
		driver.findElement(By.xpath("//a[@class='menu-button' and preceding::*[@title='" + "LOCALCS - SAMPLE AUDIT FILE" + "']]")).click();
		
		assertTrue(verifyListOrder());
		
		ContentNavigator.deleteFav(driver);
		

	}

	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstallSWCS(driver);
		Authorization.logout(driver);
		driver.quit();
	}
	
	public static boolean verifyListOrder() {
		
		try { 
			driver.findElement(By.xpath("//a[text()='View' and following::*[text()='Show Report Versions']]"));
			driver.findElement(By.xpath("//a[text()='Show Report Versions' and preceding::*[text()='View']]"));
		} catch (ElementNotFoundException e) {
			return false;
		}
		
		return true;
	}
}