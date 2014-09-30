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

public class RM1582 {
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
		
		driver.findElement(By.name("form_templategroup:1:groupchild:2:templateinput:input")).click();
		WebElement select2 = driver.findElement(By.className("ui-datepicker-year"));
		Select dropDown2 = new Select(select2);
		dropDown2.selectByValue("2000");
		
		driver.findElement(By.xpath("//a[@class='ui-state-default' and text()='1']")).click();
		Thread.sleep(500);
		
		driver.findElement(By.name("form_buttonbar:searchbutton")).click();
		
		driver.findElement(By.xpath("//a[@title='Show Menu']")).click();
		driver.findElement(By.xpath("//a[text()='View']")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@title='Actions Menu']")).click();
		
		driver.findElement(By.xpath("//a[text()='Content Query']"));
		driver.findElement(By.xpath("//a[text()='Text to PDF']"));
		driver.findElement(By.xpath("//a[text()='Reset']"));
		driver.findElement(By.xpath("//a[text()='Email']"));
		driver.findElement(By.xpath("//a[text()='Bundle - Email Document Text']"));
		driver.findElement(By.xpath("//a[text()='Bundled/combined FTP']"));
		driver.findElement(By.xpath("//a[text()='Bundled/combined JES Driver']"));
		driver.findElement(By.xpath("//a[text()='Bundle - Email Document Link']"));
		driver.findElement(By.xpath("//a[text()='Bundle - Tiff file Print']"));
		driver.findElement(By.xpath("//a[text()='Dxter Driver']"));
		driver.findElement(By.xpath("//a[text()='FTP Driver']"));
		driver.findElement(By.xpath("//a[text()='JES Driver']"));
		driver.findElement(By.xpath("//a[text()='Email Document Link']"));
		driver.findElement(By.xpath("//a[text()='PostScript Print']"));
		driver.findElement(By.xpath("//a[text()='Email Document Text']"));
		driver.findElement(By.xpath("//a[text()='Tiff file Print']"));
		driver.findElement(By.xpath("//a[text()='Download']"));
		driver.findElement(By.xpath("//a[text()='Download Range']"));
		driver.findElement(By.xpath("//a[text()='Analyze']"));
		driver.findElement(By.xpath("//a[text()='Extract']"));
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "swei");
		CAMs.uninstallSWCS(driver);
		
		Authorization.logout(driver);
		driver.quit();
	}
}

