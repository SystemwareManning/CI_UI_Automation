package com.systemware.selenium.redmine._4_14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.find.ContentNavigator;
import com.systemware.selenium.system.AdvancedConfiguration;
import com.systemware.selenium.system.CAMs;

public class RM2167 {
	private static WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "CAMs");
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
		
		driver.findElement(By.xpath("//a[@title='Show Menu' and following::*[text()='SAMPLE AUDIT FILE']]")).click();
		driver.findElement(By.xpath("//a[text()='View in New Window']")).click();
		
		Thread.sleep(5000);
		int count = 0;
		
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			
			if (count == 1) {
				driver.findElement(By.xpath("//input[@value='Close']")).click();
			}
			
			count++;
		}
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstallSWCS(driver);
		Authorization.logout(driver);
		driver.quit();
	}
	
}
