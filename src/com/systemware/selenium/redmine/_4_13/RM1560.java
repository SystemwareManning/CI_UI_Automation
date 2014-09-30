package com.systemware.selenium.redmine._4_13;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

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

public class RM1560 {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		
		CAMs.install(driver, CAMLocations.swei);
		
		NavBar.navigate(driver, "Administration", "System", "Enterprise Index Mapping Rules");
		
		driver.findElement(By.xpath("//input[@value='New Enterprise Index Mapping Rule']")).click();
		
		driver.findElement(By.name("form_templategroup:1:groupchild:5:templateinput:2:rangecontainer:rangeinput")).click();
		
		String lastYear = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']/option[last()]")).getText();
		
		assertEquals(Integer.parseInt(lastYear), currentYear);
		

		  
	}

	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "swei");
		Authorization.logout(driver);
		driver.quit();
	}
}