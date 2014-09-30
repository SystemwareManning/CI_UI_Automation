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

public class RM2140 {
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
		
		NavBar.navigate(driver, "Administration", "Security", "Sessions");
		
		driver.findElement(By.xpath("//a[@title='Click here to toggle this group.' and text()='Sessions']"));
		
		NavBar.navigate(driver, "Administration", "Security", "Users");

		driver.findElement(By.xpath("(//a[@title='Show Menu' and following::*[text()='" + "admin" + "']])[last()]")).click();
		driver.findElement(By.xpath("//a[text()='View Sessions']")).click();
		
		driver.findElement(By.xpath("//a[@title='Click here to toggle this group.' and text()='Sessions']"));
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "swbaseuser");
		Authorization.logout(driver);
		driver.quit();
	}
}
