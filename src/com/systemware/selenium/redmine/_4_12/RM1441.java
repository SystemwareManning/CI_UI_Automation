package com.systemware.selenium.redmine._4_12;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.Browser;
import com.systemware.selenium.core.CAMLocations;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.system.AdvancedConfiguration;
import com.systemware.selenium.system.CAMs;

public class RM1441 {
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
		CAMs.install(driver, CAMLocations.swappconfig);
		
		NavBar.navigate(driver, "Administration", "System", "Advanced Configuration");
		
		AdvancedConfiguration.toggleCollapsed(driver, "CAMs");
		AdvancedConfiguration.toggleCollapsed(driver, "swappconfig");
		AdvancedConfiguration.toggleCollapsed(driver, "config");
		AdvancedConfiguration.toggleCollapsed(driver, "app");
		
		AdvancedConfiguration.openFile(driver, "webAppMenuItems.xml");
		
		String currentXML = AdvancedConfiguration.getXMLFromTextContainer(driver);

		Document document = DocumentHelper.parseText(currentXML);
		Element e = (Element) document.selectSingleNode("//item/name[text()='Administration/User Interface/List Items']");
		e.setText("Administration/User Interface/List Items\\/TEST");
		
		AdvancedConfiguration.saveXMLToTextContainer(driver, document);
		AdvancedConfiguration.saveAndActivateConfig(driver);

		driver = Browser.relaunch(driver);
		Authorization.adminLogin(driver);
		
		NavBar.navigate(driver, "Administration", "User Interface", "List Items/TEST");
		

	}

	@After
	public void tearDown() throws Exception {
		NavBar.navigate(driver, "Administration", "System", "CAMs");		
		CAMs.uninstall(driver, "swappconfig");
		CAMs.uninstall(driver, "swbaseuser");
		Authorization.logout(driver);
		driver.quit();
	}
}