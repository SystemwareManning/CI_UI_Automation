package com.systemware.selenium.redmine._4_13;

import static org.junit.Assert.assertEquals;

import java.util.List;

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

public class RM1625 {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		
		CAMs.install(driver, CAMLocations.redmine1625);
		CAMs.startCAM(driver, "redmine1625");

		NavBar.navigate(driver, "Find", "Search Redmine #1625");

		driver.findElement(By.xpath("//a[text()='Create Redmine #1625']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("(//input[@class='hidden-file-input'])[last()]"));
		js.executeScript("arguments[0].setAttribute('class', '')",element);
		element.sendKeys(CAMLocations.redmine1625);

		List<WebElement> elements = 
				driver.findElements(By.name("form_templategroup:1:groupchild:1:templateinput:input"));
		assertEquals(elements.size(),2);
	}

	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}
}