package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.find.ContentNavigator;
import com.systemware.selenium.system.AdvancedConfiguration;
import com.systemware.selenium.system.CAMs;

public class RM2074 {
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
		ContentNavigator.expandMenu(driver, "CIMANUAL");
		ContentNavigator.findDSID(driver, "41444D494E2020202014245F016B80470001");
		Thread.sleep(3000);
		
		WebElement element = driver.findElement(By.name("form_pagenumcontainer:pagenum"));
		element.clear();
		element.sendKeys("1100");
		element.sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		
		element = driver.findElement(By.name("form_pagenumcontainer:pagenum"));
		String pageNum = element.getAttribute("value");

		assertEquals(pageNum, "1,100");
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstallSWCS(driver);
		
		Authorization.logout(driver);
		driver.quit();
	}

}
