package com.systemware.selenium.redmine._4_14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMCreator;

public class RM2007 {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAM Creator");
		
		CAMCreator.relationalDatabase(driver, "No");
		CAMCreator.nextPage(driver);
		CAMCreator.back(driver);
		
		/* Verify that the current page is Topic Storage */
		driver.findElement(By.xpath("//h1[text()='Topic Storage']"));
	}
	
	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}

}
