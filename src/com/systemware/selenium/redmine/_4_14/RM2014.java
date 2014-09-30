package com.systemware.selenium.redmine._4_14;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.core.Utility;
import com.systemware.selenium.system.AdvancedConfiguration;
import com.systemware.selenium.system.CAMs;

public class RM2014 {
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
		CAMs.install(driver, CAMLocations.swbaseuser);
		
		NavBar.navigate(driver, "Administration", "System", "Advanced Configuration");
		
		AdvancedConfiguration.toggleCollapsed(driver, "CAMs");
		AdvancedConfiguration.toggleCollapsed(driver, "swbaseuser");
		AdvancedConfiguration.toggleCollapsed(driver, "config");
		AdvancedConfiguration.toggleCollapsed(driver, "app");
		AdvancedConfiguration.openFile(driver, "gensrch_S0017.xml");
		
		String currentXML = AdvancedConfiguration.getXMLFromTextContainer(driver);
		
		SAXReader reader = new SAXReader();
		Document document = DocumentHelper.parseText(currentXML);
		
		String xml = "<input name=\"testdisplayerror\" visible=\"1\">" +
					 	"<type>display</type>" +
						"<label>Test Display Error</label>" +
						"<value>value</value>" +
						"<size>200</size>" +
						"<onvalidate>" +
							"arg.message = 'invalid!!!';" +
						"</onvalidate>" +
					  "</input>";
		
		Document newNodeDocument = reader.read(new StringReader(xml));
		
		Element e = (Element) document.selectSingleNode("//search/inputgroup/row[1]");
		e.add(newNodeDocument.getRootElement());
		
		StringSelection stringSelection = new StringSelection(document.asXML());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		AdvancedConfiguration.saveXMLToTextContainer(driver, document);
		AdvancedConfiguration.saveAndActivateConfig(driver);
		 
		NavBar.navigate(driver, "Administration", "Security", "Users");
		  
		Utility.refreshTemplate(driver);

  
		driver.findElement(By.xpath("//label[text()='Test Display Error']"));
		driver.findElement(By.xpath("//span[text()='value']"));
		  

	}

	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "swbaseuser");
		Authorization.logout(driver);
		driver.quit();
	}
}