package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMs;
import com.systemware.selenium.ui.TemplateDesigner;
import com.systemware.selenium.ui.UITemplates;

public class RM2043 {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.install(driver, CAMLocations.swcs);
		
		NavBar.navigate(driver, "Administration", "User Interface", "UI Templates");
		
		UITemplates.editUITemplate(driver, "deliver.email");
		
		TemplateDesigner.clickFormConfigTab(driver);
		
		assertTrue(clickMetadataRetrievalTemplate_verifySeperationTabs(driver));
		

	}

	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "swcs");
		Authorization.logout(driver);
		driver.quit();
	}
	
	public static boolean clickMetadataRetrievalTemplate_verifySeperationTabs(WebDriver driver) {
		
		try {
			driver.findElement(By.name("form_propformcontainer:propform:propform:metadataretrievaltemplate:selectform:textfield")).click();
			driver.findElement(By.xpath("//span[text()='Retrieval Templates']"));
			driver.findElement(By.xpath("//span[text()='View Templates']"));
		} catch (ElementNotFoundException e) {
			return false;
		}
		
		return true;
	}
}