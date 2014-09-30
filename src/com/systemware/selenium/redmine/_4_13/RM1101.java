package com.systemware.selenium.redmine._4_13;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.ui.TemplateDesigner;
import com.systemware.selenium.ui.UITemplates;

public class RM1101 {
	
	private static WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "User Interface", "UI Templates");	
		UITemplates.editUITemplate(driver, "import.license");
	
		TemplateDesigner.addNewInput(driver, "License File:");
		TemplateDesigner.changeInputName(driver, "TEST");	
		TemplateDesigner.setJavaScriptOnChange(driver);
		TemplateDesigner.setOnChangeScript(driver, "arg.form.TEST.value = 'TEST JavaScript'");	
		TemplateDesigner.save(driver);

		NavBar.navigate(driver, "Administration", "Import & Export", "Import License");
		verifyJavaScriptChanges();
				
		NavBar.navigate(driver, "Administration", "User Interface", "UI Templates");
		UITemplates.editUITemplate(driver, "import.license");
	
		TemplateDesigner.deleteInput(driver, "New Input:");
		TemplateDesigner.save(driver);
		
		NavBar.navigate(driver, "Administration", "User Interface", "UI Templates"); //Navigate to different page to prevent IE "are you sure you want
																	// to close dialog. Should actually redirect to homepage. 
	}
	
	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}
	
	public static void verifyJavaScriptChanges() {
		driver.findElement(By.xpath("//input[@value='TEST JavaScript']"));
	}
}
