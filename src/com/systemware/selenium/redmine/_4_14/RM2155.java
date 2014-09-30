package com.systemware.selenium.redmine._4_14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
//23432
public class RM2155 {
	private WebDriver driver;
	private WebElement port;
	private String originalPort;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "Content Servers");
		driver.findElement(By.xpath("//td[text()='LOCALCS']")).click();

		port = driver.findElement(By.name("form_port"));
		
		originalPort = port.getAttribute("value");
		port.clear();
		port.sendKeys("11111");
		driver.findElement(By.name("form_submitlink")).click();
		
		NavBar.navigate(driver, "Administration", "System", "Event Manager", "Event Registry");

		driver.findElement(By.xpath("//input[@value='Register Event']")).click();
		
		driver.findElement(By.name("form_templategroup:1:groupchild:1:templateinput:input")).sendKeys("1234");
		driver.findElement(By.name("form_templategroup:1:groupchild:2:templateinput:input:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[@title='Add Report Version']")).click();
		Thread.sleep(1500);
		
		driver.findElement(By.name("form_templategroup:1:groupchild:3:templateinput:input:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[@title='LOCALCS']")).click();
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		
		NavBar.navigate(driver, "Administration", "System", "Content Servers");
		driver.findElement(By.xpath("//td[text()='LOCALCS']")).click();
		
		port = driver.findElement(By.name("form_port"));
		port.clear();
		port.sendKeys(originalPort);
		driver.findElement(By.name("form_submitlink")).click();
		
		Authorization.logout(driver);
		driver.quit();
	}
}
