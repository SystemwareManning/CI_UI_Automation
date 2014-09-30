package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertEquals;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.StringReader;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.find.ContentNavigator;
import com.systemware.selenium.system.AdvancedConfiguration;
import com.systemware.selenium.system.CAMs;

public class RM1773 {
	private WebDriver driver;
	
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
		
		AdvancedConfiguration.openFile(driver, "uiactionconfig.xml");
		
		String xml = 
				"<action id='swcs.viewer.download.range' type='method' noforminput='1' results='1'>" +
					"<name>DownloadRange</name>" +
						"<label>Download Range</label>" +
							"<params>" +
								"<param name='allowedmaxlines'>1000</param>" +
								"<param name='defaultmaxlines'>100</param>" +
								"<param name='allowedmaxpages'>50</param>" +
								"<param name='defaultmaxpages'>5</param>" +
								"<param name='rtrv_tplid'>qa</param>" +
							"</params>" +
				"</action>";
		String currentXML = AdvancedConfiguration.getXMLFromTextContainer(driver);
		
		SAXReader reader = new SAXReader();
		Document document = DocumentHelper.parseText(currentXML);
		Document newNodeDocument = reader.read(new StringReader(xml));
		
		Element e = (Element) document.selectSingleNode("//uiactions");
		
		e.add(newNodeDocument.getRootElement());;
		StringSelection stringSelection = new StringSelection(document.asXML());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		AdvancedConfiguration.saveXMLToTextContainer(driver, document);

		xml = "<actionref id='swcs.viewer.download.range'/>";
		
		currentXML = AdvancedConfiguration.getXMLFromTextContainer(driver);
		document = DocumentHelper.parseText(currentXML);
		
		newNodeDocument = reader.read(new StringReader(xml));
		

		Element f = (Element) document.selectSingleNode("//actiongroup[@id='swcs.viewer.actiongroup']");
		f.add(newNodeDocument.getRootElement());;
		
		
		stringSelection = new StringSelection(document.asXML());
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		AdvancedConfiguration.saveXMLToTextContainer(driver, document);
		AdvancedConfiguration.saveAndActivateConfig(driver);

		//// xml is configured at this point
		
		NavBar.navigate(driver, "Find", "Content Navigator"); 

		ContentNavigator.expandMenu(driver, "LOCALCS");
		ContentNavigator.expandMenu(driver, "SELENIUM");
		ContentNavigator.expandMenu(driver, "CHINESE");
		ContentNavigator.findDSID(driver, "41444D494E2020202014259F016AF3620001");
		
		driver.findElement(By.xpath("//a[@title='Actions Menu']")).click();
		driver.findElement(By.xpath("//a[text()='Download Range']")).click();
		
		WebElement element = driver.findElement(By.name("form_startpage"));
		element.clear();
		element.sendKeys("10");
		
		element = driver.findElement(By.name("form_maxpages"));
		element.clear();
		element.sendKeys("10");
		
		element = driver.findElement(By.name("form_maxlines"));
		element.clear();
		element.sendKeys("500");
		
		driver.findElement(By.name("form_:submit")).click();
		List<WebElement> elements = driver.findElements(By.className("error-msg"));
		
		assertEquals(elements.size(), 3);
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs", "CAM Management");
		CAMs.uninstallSWCS(driver);
		
		Authorization.logout(driver);
		driver.quit();
	}

}
