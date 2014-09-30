package com.systemware.selenium.redmine._4_13;

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

public class RM1690 {
	private WebDriver driver;
	
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
		
		ContentNavigator.expandMenu(driver, "My Favorites");
		
		driver.findElement(By.xpath("//div[@title='41444D494E2020202014203F016BEB6F0001']")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='findtextfield']"));

		}

	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstallSWCS(driver);
		Authorization.logout(driver);
		driver.quit();
	}
	

}