package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.find.ContentNavigator;
import com.systemware.selenium.system.AdvancedConfiguration;
import com.systemware.selenium.system.CAMs;

public class RM2248 {
	private static WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs", "CAM Management");

		CAMs.installSWCS(driver);
		
		NavBar.navigate(driver, "Administration", "System", "Advanced Configuration");
		
		AdvancedConfiguration.toggleCollapsed(driver, "CAMs");
		AdvancedConfiguration.toggleCollapsed(driver, "swcs");
		AdvancedConfiguration.toggleCollapsed(driver, "config");
		AdvancedConfiguration.openFile(driver, "cciesconfig.xml");
		AdvancedConfiguration.enableSWCSCrawler(driver);
		
		NavBar.navigate(driver, "Find", "Content Navigator");
		
		ContentNavigator.expandMenu(driver, "LOCALCS");
		ContentNavigator.expandMenu(driver, "SELENIUM");
		ContentNavigator.expandMenu(driver, "CHINESE");
		
		ContentNavigator.findDSID(driver, "41444D494E2020202014259F016AF3620001");

		ContentNavigator.navigateActionsMenu(driver, "Query", "Content Query");
		testOperator(">=");
		testOperator(">");
		testOperator("<=");
		testOperator("<=");
		testOperator("=");
		testOperator("+");

	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs", "CAM Management");
		CAMs.uninstallSWCS(driver);
		Authorization.logout(driver);
		driver.quit();
	}
	
	public static void testOperator(String operator) throws InterruptedException {

		
		driver.findElement(By.xpath("//div[@class='sw-select smaller input suppress-list filter']/div/input[@type='text']")).clear();
		driver.findElement(By.xpath("//div[@class='sw-select smaller input suppress-list filter']/div/input[@type='text']")).sendKeys(operator);
		driver.findElement(By.xpath("//a[@title='Save Content Query']")).click();
		driver.findElement(By.name("form_formcontents:savename")).sendKeys(operator);
		driver.findElement(By.name("form_formcontents:savebutton")).click();
		
		driver.findElement(By.className("remove-tq-item")).click();
		driver.findElement(By.xpath("//a[@title='Load Saved Content Query']")).click();
		Thread.sleep(300);
		driver.findElement(By.name("form_formcontents:loadtid:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[@title='" + operator + "']")).click();
		driver.findElement(By.name("form_formcontents:loadbutton")).click();
		
		String value = driver.findElement(By.xpath("//div[@class='sw-select smaller input suppress-list filter']/div/input[@type='text']")).getAttribute("value");
		assertEquals(value, operator);
		
		driver.findElement(By.xpath("//a[@title='Load Saved Content Query']")).click();
		Thread.sleep(300);
		driver.findElement(By.name("form_formcontents:loadtid:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[@title='" + operator + "']")).click();
		driver.findElement(By.name("form_formcontents:deletebutton")).click();
		driver.findElement(By.xpath("//a[text()='Ok']")).click();
		
		Thread.sleep(300);
		driver.findElement(By.xpath("//input[@value='Cancel']")).click();
	}
}
