package com.systemware.selenium.redmine._4_14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMs;
import com.systemware.selenium.ui.UITemplates;

public class RM2151 {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.install(driver, CAMLocations.swbaseuser);
		
		NavBar.navigate(driver, "Administration", "User Interface", "UI Templates");
		
		UITemplates.editUITemplate(driver, "S0015");
		
		driver.findElement(By.xpath("//div[text()='Test Data' and following::*[text()='User Name']]")).click();
	
		driver.findElement(By.name("form_propformcontainer:propform:propform:horalign:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[text()='center']")).click();
		driver.findElement(By.name("form_propformcontainer:propform:propform:submitbutton")).click();
		driver.findElement(By.xpath("//div[@class='test-data-row align-center' and text()='Test Data' and following::*[text()='User Name']]"));
		
		driver.findElement(By.name("form_propformcontainer:propform:propform:horalign:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[text()='right']")).click();
		driver.findElement(By.name("form_propformcontainer:propform:propform:submitbutton")).click();
		driver.findElement(By.xpath("//div[@class='test-data-row align-right' and text()='Test Data' and following::*[text()='User Name']]"));
		
		driver.findElement(By.name("form_propformcontainer:propform:propform:horalign:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[text()='left']")).click();
		driver.findElement(By.name("form_propformcontainer:propform:propform:submitbutton")).click();
		driver.findElement(By.xpath("//div[@class='test-data-row align-left' and text()='Test Data' and following::*[text()='User Name']]"));
		
		UITemplates.saveTemplate(driver);
	}
	
	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}
}
