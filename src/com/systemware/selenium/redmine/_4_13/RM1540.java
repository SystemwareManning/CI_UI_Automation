package com.systemware.selenium.redmine._4_13;

import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
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
import com.systemware.selenium.ui.UITemplates;

public class RM1540 {
	private WebDriver driver;
	String data;

	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		
		CAMs.installSWCS(driver);
		
		NavBar.navigate(driver, "Administration", "System", "Advanced Configuration");
		
		AdvancedConfiguration.toggleCollapsed(driver, "CAMs");
		AdvancedConfiguration.toggleCollapsed(driver, "swcs");
		AdvancedConfiguration.toggleCollapsed(driver, "config");
		AdvancedConfiguration.openFile(driver, "cciesconfig.xml");
		AdvancedConfiguration.enableSWCSCrawler(driver); 
		
		AdvancedConfiguration.openFile(driver, "uiactionconfig.xml");
		

		
		String currentXML = AdvancedConfiguration.getXMLFromTextContainer(driver);
		SAXReader reader = new SAXReader();
		Document document = DocumentHelper.parseText(currentXML);

		String xml = "<action id=\"test.action.1\" type=\"panel\" manualtid=\"t\" noforminput=\"t\" results=\"t\" permission=\"system.security.admin,security.update-user\">" +
							"<name>testaction1</name>" +
							"<url>security/users/edit</url>" +
							"<label>Test Action 1</label>" +
							"<params>" +
								"<param name=\"userid\">#{1700}</param>" +
							"</params>" +
				"</action>";
		
		
		Document newNodeDocument = reader.read(new StringReader(xml));
		Element e = (Element) document.selectSingleNode("//uiactionconfig/uiactions[1]");
		
		e.add(newNodeDocument.getRootElement());
		
		xml = 
				"<action id=\"test.action.2\" type=\"panel\" manualtid=\"t\" noforminput=\"t\" results=\"t\" permission=\"system.security.admin,security.update-user\">" +
						"<name>testaction2</name>" +
						"<url>security/users/edit</url>" +
						"<label>Test Action 2</label>" +
						"<params>" +
							"<param name=\"userid\">#{1700}</param>" +
						"</params>" +
				"</action>";
		
		newNodeDocument = reader.read(new StringReader(xml));
		e = (Element) document.selectSingleNode("//uiactionconfig/uiactions[1]");
		e.add(newNodeDocument.getRootElement());

		xml = 
				"<actiongroup id=\"test.group.1\">" +
						"<actionref id=\"test.action.1\" type=\"action\" />" +
						"<actionref id=\"test.action.2\" type=\"action\" />" +
				"</actiongroup>";
		
		newNodeDocument = reader.read(new StringReader(xml));
		
		e = (Element) document.selectSingleNode("//uiactiongroups");
		e.add(newNodeDocument.getRootElement());

		AdvancedConfiguration.saveXMLToTextContainer(driver, document);
		AdvancedConfiguration.saveAndActivateConfig(driver);

		NavBar.navigate(driver, "Administration", "User Interface", "UI Templates");
		
		UITemplates.filterTemplates(driver, "search.908.a.swcsrecentverview");
		
		UITemplates.editUITemplate(driver, "search.908.a.swcsrecentverview");

		UITemplates.addActiongroup(driver, "Results No-Selection Action Groups", " (id: test.group.1)");
		UITemplates.addActiongroup(driver, "Results Single-Select Action Groups", " (id: test.group.1)");
		UITemplates.addActiongroup(driver, "Results Multi-Select Action Groups", " (id: test.group.1)");
		
		UITemplates.saveTemplate(driver);
		
		NavBar.navigate(driver, "Find", "Content Navigator");
		ContentNavigator.expandMenu(driver, "LOCALCS");
		ContentNavigator.expandMenu(driver, "SELENIUM");

		//assertions
		driver.findElement(By.xpath("//a[text()='Test Action 1']"));
		driver.findElement(By.xpath("//a[text()='Test Action 2']"));
		
		//assertions 2
		driver.findElement(By.xpath("//a[@title='Show Menu' and following::*[text()='SAMPLE AUDIT FILE']]")).click();
		driver.findElement(By.xpath("//a[text()='Test Action 1' and @class='menu-item-link clickable']"));
		driver.findElement(By.xpath("//a[text()='Test Action 2' and @class='menu-item-link clickable']"));
		
		//assertion 3
		driver.findElement(By.xpath("//a[text()='Select' and following::*[text()='SAMPLE AUDIT FILE']]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//a[text()='1 item(s) selected']")).click();
		driver.findElement(By.xpath("//a[text()='Test Action 1' and @class='menu-item-link clickable']"));
		driver.findElement(By.xpath("//a[text()='Test Action 2' and @class='menu-item-link clickable']"));
		
		
		
	}

	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstallSWCS(driver);
		Authorization.logout(driver);
		driver.quit();
	}
}