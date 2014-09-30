package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertEquals;

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

public class RM2071 {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs", "CAM Management");

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
		ContentNavigator.findDSID(driver, "41444D494E2020202014203F016BEB6F0001");
		
		WebElement findTextField = driver.findElement(By.id("findtextfield"));
		
		findTextField.click();
		findTextField.sendKeys("LOG");
		findTextField.sendKeys(Keys.ENTER);
		
		/* Verify LOG is found */
		driver.findElement(By.xpath("//span[text()='Found LOG 2 times on this page.']"));
		
		WebElement nextButton = driver.findElement(By.className("find-text-next"));
		nextButton.click();
		nextButton.click();
		Thread.sleep(500);
		String beginning = driver.findElement(By.xpath("//span[@class='dir-txt']")).getText();
		assertEquals(beginning, "beginning");
		
		driver.findElement(By.className("find-text-prev")).click();
		Thread.sleep(500);
		String end = driver.findElement(By.xpath("//span[@class='dir-txt']")).getText();
		assertEquals(end, "end");
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs", "CAM Management");
		CAMs.uninstallSWCS(driver);
		
		Authorization.logout(driver);
		driver.quit();
	}

}
