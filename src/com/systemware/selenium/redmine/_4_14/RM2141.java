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
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMs;

public class RM2141 {
	private WebDriver driver;
	private WebElement element;

	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {
		
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.install(driver, CAMLocations.redmine2141);
		
		NavBar.navigate(driver, "Find", "Search Redmine #2141");
		driver.findElement(By.xpath("//a[text()='Create Redmine #2141']")).click();
		
		Thread.sleep(1000);
		
		element = driver.findElement(By.name("form_templategroup:1:groupchild:1:templateinput:input"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('class', '')",element);
		
		element.sendKeys(CAMLocations.redmine2141);
		
		driver.findElement(By.name("form_buttonbar:savebutton")).click();
		
		driver.findElement(By.xpath("//h3[text()='Save Another Failed']"));
		
		driver.findElement(By.xpath("//input[@value='Cancel']")).click();
		
		driver.findElement(By.xpath("//div[text()='redmine2141.zip']"));

	}

	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "redmine2141");
		Authorization.logout(driver);
		driver.quit();
	}
	

}