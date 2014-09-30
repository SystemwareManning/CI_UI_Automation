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
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMs;

public class RM1814 {
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
		CAMs.install(driver, CAMLocations.swbaseuser);

		NavBar.navigate(driver, "Administration", "Security", "Users");
		checkForID();
		
		NavBar.navigate(driver, "Administration", "Security", "Groups");
		checkForID();
		
		NavBar.navigate(driver, "Administration", "Security", "Roles");
		checkForID();
		
		NavBar.navigate(driver, "Administration", "Security", "Permissions");
		checkForID();
		
		NavBar.navigate(driver, "Administration", "Security", "Filters");
		checkForID();
		
		NavBar.navigate(driver, "Administration", "Security", "CS Resource Rules");
		checkForID();
		
		NavBar.navigate(driver, "Administration", "Security", "CS Filter Rules");
		checkForID();


	}

	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "swbaseuser");
		Authorization.logout(driver);
		driver.quit();
	}

	private void checkForID() {
		element = driver.findElement(By.xpath("//a[text()='ID']"));
		assertEquals("ID", element.getText());
	}

}