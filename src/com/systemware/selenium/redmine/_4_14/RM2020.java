package com.systemware.selenium.redmine._4_14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMCreator;

public class RM2020 {
	private static WebDriver driver;
	
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
		
		CAMCreator.contentTopic(driver, "No");
		CAMCreator.notification(driver, "No");
		CAMCreator.nextPage(driver);
		
		CAMCreator.uiCreate(driver, "Yes");
		CAMCreator.uiUpdate(driver, "Yes");
		CAMCreator.uiDelete(driver, "Yes");
		CAMCreator.nextPage(driver);
		
		CAMCreator.setCAMID(driver, "Thisisatest");
		
		driver.findElement(By.name("form_propformcontainer:propform:camid")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//h1[text()='CAM Information']"));			
	}
	
	
	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}
}
