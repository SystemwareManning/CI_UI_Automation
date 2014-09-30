package com.systemware.selenium.redmine._4_13;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.find.ContentNavigator;
import com.systemware.selenium.system.AdvancedConfiguration;
import com.systemware.selenium.system.CAMs;

public class RM1986 {
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
		
		ContentNavigator.findDSID(driver, "41444D494E2020202014203F016BEB6F0001");

		assertTrue(checkElementName("Bundle - Email Document Text"));
		assertTrue(checkElementName("Bundled/combined FTP"));
		assertTrue(checkElementName("Bundled/combined JES Driver"));
		assertTrue(checkElementName("Bundle - Email Document Link"));
		assertTrue(checkElementName("Bundle - PostScript Print"));
		assertTrue(checkElementName("Bundle - Tiff file Print"));
		assertTrue(checkElementName("Dxter Driver"));
		assertTrue(checkElementName("FTP Driver"));
		assertTrue(checkElementName("JES Driver"));
		assertTrue(checkElementName("Email Document Link"));
		assertTrue(checkElementName("PostScript Print"));
		assertTrue(checkElementName("Email Document Text"));
		assertTrue(checkElementName("Tiff file Print"));
		

	}

	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstallSWCS(driver);
		Authorization.logout(driver);
		driver.quit();
	}
	
	public boolean checkElementName(String arg) throws InterruptedException {
		Thread.sleep(200);
		driver.findElement(By.xpath("//a[@title='Actions Menu']")).click();
		Thread.sleep(500);
		WebElement we = driver.findElement(By.xpath("//a[text()='Deliver']"));
		WebElement we2 = driver.findElement(By.xpath("//a[text()='CS Drivers']"));

		String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";

		((JavascriptExecutor)driver).executeScript(javaScript, we);
		((JavascriptExecutor)driver).executeScript(javaScript, we2);
		
		driver.findElement(By.xpath("//a[text()='" + arg + "']")).click();

		try {
			driver.findElement(By.xpath("//h1[@class='panel-title' and text()='" + arg + "']"));
		} catch (Exception e) { return false;}
		
		Thread.sleep(300);
		return true;
	}
}