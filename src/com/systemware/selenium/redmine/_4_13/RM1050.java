package com.systemware.selenium.redmine._4_13;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

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

public class RM1050 {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "Advanced Configuration");

		AdvancedConfiguration.hideTreePanel(driver);
		isTreePanelHidden();
	
		AdvancedConfiguration.showTreePanel(driver);
		isTreePanelShown();
	}

	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}

	private void isTreePanelHidden() {
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='page-content-block adv-config hide-tree-panel']"));
		assertFalse(list.isEmpty());
	}

	private void isTreePanelShown() {

		List<WebElement> list = driver.findElements(By.xpath("//div[@class='page-content-block adv-config hide-tree-panel']"));
		assertTrue(list.isEmpty());
	}
}