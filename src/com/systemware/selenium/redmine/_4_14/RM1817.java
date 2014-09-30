package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.ui.UITemplates;

public class RM1817 {
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
		
		driver.findElement(By.xpath("//a[text()='Form Configuration']")).click();
		
		WebElement element = driver.findElement(By.xpath("//*[@id='formconfigprops']/fieldset[2]/div/div[2]/label"));
		
		assertEquals(element.getText(), "Show Save Button:");
	
	}
	
	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}
	
}
