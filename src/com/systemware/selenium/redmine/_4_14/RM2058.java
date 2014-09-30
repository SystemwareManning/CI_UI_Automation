package com.systemware.selenium.redmine._4_14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMs;

public class RM2058 {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");

		CAMs.installSWCS(driver);
		
		NavBar.navigate(driver, "Administration", "Import & Export", "Content Extraction Templates", "Import");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.name("form_templategroup:1:groupchild:templateinput:1:input"));
		
		js.executeScript("arguments[0].setAttribute('class', '')",element);
		driver.findElement(By.name("form_templategroup:1:groupchild:templateinput:1:input")).sendKeys("C:\\Users\\Jeff\\Documents\\CAMs\\cam.xml");
		
		driver.findElement(By.xpath("//input[@value='Import']")).click();
		
		driver.findElement(By.xpath("//h3[text()='Import Failed']"));
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstallSWCS(driver);
		Authorization.logout(driver);
		driver.quit();
	}
}