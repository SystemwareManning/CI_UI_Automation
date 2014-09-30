package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.find.ContentNavigator;
import com.systemware.selenium.system.AdvancedConfiguration;
import com.systemware.selenium.system.CAMs;

public class RM2206 {
	private static WebDriver driver;
	
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
		ContentNavigator.expandMenu(driver, "XML");
		
		ContentNavigator.findDSID(driver, "41444D494E2020202014270F012076AC0001");
		
		ContentNavigator.addNoteToDocument(driver);

		WebElement element = driver.findElement(By.xpath("//div[@class='in-title']"));
		
		Actions builder = new Actions(driver);
		builder.moveToElement(element);
		builder.clickAndHold();
		builder.moveToElement(element,100,10);
		builder.release();
		builder.build().perform();
		
		//left: 8.4px; top: 17px; display: block;
		
		String noteStyle = driver.findElement(By.id("newnote")).getAttribute("style");
		assertFalse(noteStyle.equalsIgnoreCase("left: 8.4px; top: 17px; display: block;"));
		assertEquals(noteStyle, "left: 16.790625px; top: 17px; display: block;");
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs", "CAM Management");
		CAMs.uninstallSWCS(driver);
		
		Authorization.logout(driver);
		driver.quit();
	}

}

