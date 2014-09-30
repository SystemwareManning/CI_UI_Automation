package com.systemware.selenium.redmine._4_14;

import static org.junit.Assert.assertEquals;

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
import com.systemware.selenium.system.AdvancedConfiguration;
import com.systemware.selenium.system.CAMs;

public class RM1859 {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = Browser.launch();
		Authorization.adminLogin(driver);
	}
	
	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs", "CAM Management");
		CAMs.install(driver, CAMLocations.swem);
		
		NavBar.navigate(driver, "Administration", "System", "Advanced Configuration");
		
		AdvancedConfiguration.toggleCollapsed(driver, "CAMs");
		AdvancedConfiguration.toggleCollapsed(driver, "swem");
		AdvancedConfiguration.toggleCollapsed(driver, "config");
		
		AdvancedConfiguration.openFile(driver, "cxsconfig.xml");
		
		String xml = 
						"<parameters>" +
						 	"<parm name='raise.event.create.topic'>true</parm>" +
						  	"<parm name='raise.event.update.topic'>true</parm>" +
						 	"<parm name='raise.event.delete.topic'>true</parm>" +
						 "</parameters>";
		
		String currentXML = AdvancedConfiguration.getXMLFromTextContainer(driver);
		
		SAXReader reader = new SAXReader();
		Document document = DocumentHelper.parseText(currentXML);
		Document newNodeDocument = reader.read(new StringReader(xml));
		
		Element e = (Element) document.selectSingleNode("//topic[@name='SWEM Event Registry']");
		
		e.add(newNodeDocument.getRootElement());;
		StringSelection stringSelection = new StringSelection(document.asXML());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		AdvancedConfiguration.saveXMLToTextContainer(driver, document);
		AdvancedConfiguration.saveAndActivateConfig(driver);
		
		
		
		NavBar.navigate(driver, "Administration", "System", "Event Manager", "Event Registry");
		driver.findElement(By.xpath("//input[@value='Register Event']")).click();
		Thread.sleep(300);
		
		driver.findElement(By.name("form_templategroup:1:groupchild:1:templateinput:input")).sendKeys("Test");
		driver.findElement(By.name("form_templategroup:1:groupchild:2:templateinput:input:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[@title='Add Report Version']")).click();
		Thread.sleep(500);
		driver.findElement(By.name("form_templategroup:1:groupchild:3:templateinput:input:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[@title='CCS']")).click();
		
		driver.findElement(By.name("form_templategroup:1:groupchild:6:templateinput:input:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[@title='Invoke Rule Script']")).click();
		
		driver.findElement(By.name("form_templategroup:1:groupchild:8:templateinput:input:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[text()='indexcam~cap.ccsmon.drl']")).click();
		
		driver.findElement(By.name("form_buttonbar:savebutton")).click();
		
		
		

		driver.findElement(By.xpath("//a[text()='Menu']")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//a[text()='Update Registry']")).click();
		driver.findElement(By.xpath("//input[@value='Test']")).sendKeys("2");
		driver.findElement(By.name("form_buttonbar:savebutton")).click();
		String name = driver.findElement(By.xpath("//div[text()='Test2']")).getText();
		assertEquals(name, "Test2");
		
	
		driver.findElement(By.xpath("//a[text()='Menu']")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//a[text()='Delete Registry']")).click();
		driver.findElement(By.xpath("//a[text()='Ok']")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//button[text()='Close']")).click();
		
		
	}
	
	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");
		CAMs.uninstall(driver, "swem");
		
		Authorization.logout(driver);
		driver.quit();
	}

}
