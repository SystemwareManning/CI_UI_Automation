package com.systemware.selenium.redmine._4_14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.CAMs;
import com.systemware.selenium.system.EIXMappingRules;

public class RM1482 {
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
		CAMs.install(driver, CAMLocations.swei);
	
		NavBar.navigate(driver, "Administration", "System", "Enterprise Index Mapping Rules");
		
		
		if (doesEIXRuleExist() == true) {
			NavBar.navigate(driver, "Find", "Test EIX Group");
		}
		
		else {
			
			EIXMappingRules.create(driver);
			EIXMappingRules.selectFolder(driver, "/SELENIUM");
			EIXMappingRules.selectReport(driver, "INDEXED (V:1)");
			EIXMappingRules.setEIXGroup(driver, "Test EIX Group");
			
			driver.findElement(By.name("form_templategroup:1:groupchild:5:templateinput:2:input")).click();
			WebElement select = driver.findElement(By.className("ui-datepicker-year"));
			Select dropDown = new Select(select);
			dropDown.selectByValue("2000");
			
			driver.findElement(By.xpath("//a[@class='ui-state-default' and text()='1']")).click();

			Thread.sleep(500);
			
			EIXMappingRules.selectIndex(driver, "FIRST NAME (FIRSTN$)");
			EIXMappingRules.appendIndex(driver);
			EIXMappingRules.saveIndex(driver);
			NavBar.navigate(driver, "Find", "Test EIX Group");
		}
	
		
		driver.findElement(By.name("form_templategroup:1:groupchild:2:templateinput:input")).click();
		WebElement select2 = driver.findElement(By.className("ui-datepicker-year"));
		Select dropDown2 = new Select(select2);
		dropDown2.selectByValue("2000");
		
		driver.findElement(By.xpath("//a[@class='ui-state-default' and text()='1']")).click();
		Thread.sleep(500);
		
		driver.findElement(By.name("form_buttonbar:searchbutton")).click();
		
		driver.findElement(By.xpath("//div[text()='/SELENIUM']"));
		driver.findElement(By.xpath("//div[text()='INDEXED']"));
		
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "swei");
		CAMs.uninstallSWCS(driver);
		
		Authorization.logout(driver);
		driver.quit();
	}
	
	public boolean doesEIXRuleExist() {
		
		try {
			driver.findElement(By.xpath("//div[text()='Test EIX Group']"));
			return true;
		}
		
		catch (Exception e) { 
			return false;
		}
	}
}

