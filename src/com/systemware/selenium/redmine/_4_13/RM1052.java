package com.systemware.selenium.redmine._4_13;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.AdvancedConfiguration;

public class RM1052 {
	private WebDriver driver;
	private WebElement element;

	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "Advanced Configuration");
		AdvancedConfiguration.newConfig(driver, "AppConfig", "txnconfig.xml");
		
		element = driver.findElement(By.className("error-msg"));
		fileNamesMustBeUnique(element);
		
		driver.findElement(By.xpath("//input[@type='button' and @value='Cancel' and @class='nice-button input inline']")).click();
	}

	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}

	private void fileNamesMustBeUnique(WebElement element) {
		assertEquals("File name must be unique.", element.getText());
	}

}