package com.systemware.selenium.redmine._4_14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.ContentServers;

public class RM2158 {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "Content Servers");
		
		ContentServers.delete(driver, "LOCALCS");
		
		ContentServers.create(driver, "LOCALCS", "127.0.0.1", "23432", "ADMIN", "admin", "admin", "ADMIN", "/SYSTEM/ADMIN");
	
		driver.findElement(By.xpath("//td[text()='LOCALCS']"));
	}
	
	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}
}
