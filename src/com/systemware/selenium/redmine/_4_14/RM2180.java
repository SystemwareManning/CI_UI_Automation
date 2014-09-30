package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

public class RM2180 {
	private static WebDriver driver;
	
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
		
		ContentNavigator.findDSID(driver, "41444D494E2020202014203F016BEB6F0001");
		
		ContentNavigator.addNoteToDocument(driver);
		ContentNavigator.setNoteText(driver, "Test");
		ContentNavigator.saveNote(driver);
		
		ContentNavigator.navigateActionsMenu(driver, "Query", "Content Query");
		
		driver.findElement(By.name("form_tqitemlist:1:indexval:selectform:textfield")).sendKeys("SYSTEM");
		driver.findElement(By.name("form_:submit")).click();
		
		assertTrue(isNoteHidden());

		driver.findElement(By.xpath("//input[@class='nice-button small' and @value='Clear']")).click();
		
		assertFalse(isNoteHidden());
		
		ContentNavigator.navigateActionsMenu(driver, "Transform", "Text to PDF");
		
		driver.findElement(By.xpath("//input[@value='Transform']")).click();
		assertTrue(isNoteHidden());
		driver.findElement(By.xpath("//input[@value='Reset']")).click();
		assertFalse(isNoteHidden());
		
		ContentNavigator.deleteNote(driver);
		
		// PNG
		
	NavBar.navigate(driver, "Find", "Content Navigator"); 
		
		ContentNavigator.expandMenu(driver, "LOCALCS");
		ContentNavigator.expandMenu(driver, "SELENIUM");
		ContentNavigator.expandMenu(driver, "PNG");
		
		ContentNavigator.findDSID(driver, "41444D494E2020202014232F01503A4E0001");
		
		ContentNavigator.addNoteToDocument(driver);
		ContentNavigator.setNoteText(driver, "Test");
		ContentNavigator.saveNote(driver);
		
		ContentNavigator.navigateActionsMenu(driver, "Transform", "Image to PDF");
		assertTrue(isNoteHidden());
		
		ContentNavigator.navigateActionsMenu(driver, "Transform", "Reset");
		assertFalse(isNoteHidden());
		
		ContentNavigator.deleteNote(driver);
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstallSWCS(driver);
		
		Authorization.logout(driver);
		driver.quit();
	}
	
	public static boolean isNoteHidden() {
		
		try {
			Thread.sleep(500);
			driver.findElement(By.xpath("//div[text()='Test']"));
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}

