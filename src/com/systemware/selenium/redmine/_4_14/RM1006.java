package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertEquals;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.core.Utility;
import com.systemware.selenium.system.AdvancedConfiguration;
import com.systemware.selenium.system.CAMs;

public class RM1006 {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");

		CAMs.install(driver, CAMLocations.redmine1625);
		CAMs.startCAM(driver, "redmine1625");
		
		NavBar.navigate(driver, "Administration", "System", "Advanced Configuration");
		AdvancedConfiguration.toggleCollapsed(driver, "CAMs", "redmine1625", "config", "app");
		
		AdvancedConfiguration.openFile(driver, "gensrch_create.redmine1625.xml");

		String currentXML = AdvancedConfiguration.getXMLFromTextContainer(driver);
		
		Document document = DocumentHelper.parseText(currentXML);
		Element e = (Element) document.selectSingleNode("//search");
		e.addAttribute("transaction", "createtopic");
		
		AdvancedConfiguration.saveXMLToTextContainer(driver, document);
		AdvancedConfiguration.saveAndActivateConfig(driver);
		
		AdvancedConfiguration.openFile(driver, "gensrch_search.redmine1625.xml");
		Thread.sleep(300);
		currentXML = AdvancedConfiguration.getXMLFromTextContainer(driver);
		document = DocumentHelper.parseText(currentXML);
		
		e = (Element) document.selectSingleNode("//actions/action[@id='create.redmine1625']");
		e.addAttribute("type","method");
		
		e = (Element) document.selectSingleNode("//actions/action[@id='create.redmine1625']/name");
		e.setText("editdialog");

		e =  (Element) document.selectSingleNode("//actions/action[@id='create.redmine1625']/params/param[text()='createtopic']");
		e.detach();
		
		AdvancedConfiguration.saveXMLToTextContainer(driver, document);
		AdvancedConfiguration.saveAndActivateConfig(driver);
		
		NavBar.navigate(driver, "Find", "Search Redmine #1625");
		
		Utility.refreshTemplate(driver);
		
		driver.findElement(By.xpath("//a[text()='Create Redmine #1625']")).click();
		testCreateOpensInNewMsgBox(driver);
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "redmine1625");
		Authorization.logout(driver);
		driver.quit();
	}
	
	public static void testCreateOpensInNewMsgBox(WebDriver driver) throws InterruptedException {
		String msgBoxTitle = driver.findElement(By.className("MessageBox_titlebar")).getText();
		assertEquals(msgBoxTitle,"Create Redmine #1625");
		driver.findElement(By.xpath("//input[@value='Cancel']")).click();
		Thread.sleep(300);
	}
}
