package com.systemware.selenium.redmine._4_13;

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

public class RM1766 {
	
	private WebDriver driver;

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
		CAMs.install(driver, CAMLocations.swstore);
		
		NavBar.navigate(driver, "Find", "Redmine #992 Test");
		
		driver.findElement(By.className("record-script")).click();
		driver.findElement(By.className("recording-info"));
		driver.findElement(By.xpath("//a[@title='Stop Script Recording']")).click();
		driver.findElement(By.name("form_submitlink"));
	}

	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "redmine992");
		CAMs.uninstall(driver, "swstore");
		CAMs.uninstall(driver, "swbaseuser");
		Authorization.logout(driver);
		driver.quit();
	}
	

}