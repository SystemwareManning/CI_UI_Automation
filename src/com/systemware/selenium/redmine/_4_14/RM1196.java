package com.systemware.selenium.redmine._4_14;

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
import com.systemware.selenium.ui.TemplateDesigner;
import com.systemware.selenium.ui.UITemplates;

public class RM1196 {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "User Interface", "UI Templates");

		UITemplates.editUITemplate(driver, "import.license");

		TemplateDesigner.addNewFormAction(driver);
		TemplateDesigner.setActionType(driver, "method");
		TemplateDesigner.setActionName(driver, "ClosePage");
		TemplateDesigner.save(driver);
		
		NavBar.navigate(driver, "Administration", "Import & Export", "Import License");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@value='New Action']")).click();
		
		WebElement element = driver.findElement(By.xpath("//h1[text()='UI Template Designer']"));
		assertEquals(element.getText(), "UI Template Designer");
		
		TemplateDesigner.setActionName(driver, "CloseWindow");
		TemplateDesigner.save(driver);
		
		NavBar.navigate(driver, "Administration", "Import & Export", "Import License");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@value='New Action']")).click();
		
		driver.findElement(By.xpath("//body[@onload='closeWindow()']"));
		
		driver = Browser.relaunch(driver);
		Authorization.adminLogin(driver);
		
		NavBar.navigate(driver, "Administration", "User Interface", "UI Templates");
		UITemplates.editUITemplate(driver, "import.license");
		Thread.sleep(1000);
		
		TemplateDesigner.deleteAction(driver, "Form Actions");
		TemplateDesigner.save(driver);
	}
	
	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}
}
