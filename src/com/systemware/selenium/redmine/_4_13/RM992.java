package com.systemware.selenium.redmine._4_13;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMs;

public class RM992 {
	private static WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {	
		
		 NavBar.navigate(driver, "Administration", "System", "CAMs"); 
		 CAMs.install(driver, CAMLocations.redmine992);
		 CAMs.install(driver, CAMLocations.swbaseuser);
		 
		 driver.findElement(By.xpath("//img[@alt='Find']")).click();
		 driver.findElement(By.xpath("//span[text()='" + "Redmine #992 Test" + "']")).click();

		 driver.findElement(By.name("form_templategroup:1:groupchild:2:templateinput:input:selectform:textfield")).click();
		 assertEquals(true,checkForGroup());
		 
		 driver.findElement(By.name("form_templategroup:1:groupchild:1:templateinput:input")).clear();
		 driver.findElement(By.name("form_templategroup:1:groupchild:1:templateinput:input")).sendKeys("3");
		 assertEquals(false,checkForGroup());
		 

	}

	@After
	public void tearDown() throws Exception {
		 NavBar.navigate(driver, "Administration", "System", "CAMs");
		 CAMs.uninstall(driver, "redmine992");
		 CAMs.uninstall(driver, "swbaseuser");
		Authorization.logout(driver);
		driver.quit();
	}
	
	private static boolean checkForGroup() {
		try { 
			driver.findElement(By.xpath("//a[@title='Administrators']")).click();
		} catch (ElementNotVisibleException e) {
			return false;
		}
		return true;
	}
}