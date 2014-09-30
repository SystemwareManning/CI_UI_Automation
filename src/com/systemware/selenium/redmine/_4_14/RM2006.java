package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMCreator;

public class RM2006 {
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
		
		CAMCreator.contentTopic(driver, "Yes");
		CAMCreator.fullTextContent(driver, "Yes");
		CAMCreator.captureService(driver, "No");
		CAMCreator.notification(driver, "No");
		CAMCreator.nextPage(driver);
		
		CAMCreator.uiUpload(driver, "Yes");
		CAMCreator.uiUpdate(driver, "Yes");
		CAMCreator.uiDelete(driver, "Yes");
		CAMCreator.nextPage(driver);
		
		CAMCreator.setCAMID(driver, "Thisisatest");
		CAMCreator.setCAMName(driver, "Test");
		CAMCreator.nextPage(driver);
		
		CAMCreator.setCAMOTID(driver, "12345");
		CAMCreator.setCAMTopicName(driver, "TestCAM");
		CAMCreator.nextPage(driver);
		
		String TopicProp = driver.findElement(By.name("form_propformcontainer:propform:propertylist:1:name")).getAttribute("value");
	
		assertEquals(TopicProp, "FTXCONTENT");
	}
	
	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}

}
