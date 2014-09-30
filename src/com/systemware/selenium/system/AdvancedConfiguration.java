package com.systemware.selenium.system;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AdvancedConfiguration {

	public static void toggleCollapsed(WebDriver driver, String... args) {
		for(String arg : args) {
			driver.findElement(By.xpath("(//a[@class='toggle collapsed' and following::*[@class='node-label' and text()='" + arg + "']])[last()]")).click();
		}
	}
	
	public static void openFile(WebDriver driver, String fileName) throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='" + fileName + "']")).click();
		Thread.sleep(300);
	}
	
	public static String getXMLFromTextContainer(WebDriver driver) {
		String xml = "";
		WebElement element = driver.findElement(By.id("textarea-container"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys(Keys.chord(Keys.CONTROL, "c"));
		actions.build().perform();
		
		try {
			xml = (String) Toolkit.getDefaultToolkit()
			        .getSystemClipboard().getData(DataFlavor.stringFlavor);
		} catch (HeadlessException | UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}
		
		return xml; 
	}
	
	public static void saveXMLToTextContainer(WebDriver driver, Document document) {
		StringSelection stringSelection = new StringSelection(document.asXML());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		WebElement element = driver.findElement(By.id("textarea-container"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		actions.build().perform();
	}
	
	public static void saveAndActivateConfig(WebDriver driver) throws InterruptedException {
		driver.findElement(By.name("form_savebutton")).click();
		driver.findElement(By.className("close-button")).click();

		Thread.sleep(400);
		
		driver.findElement(By.xpath("//input[@type='button' and @value='Activate']")).click();
		
		driver.findElement(By.className("close-button")).click();
		Thread.sleep(300);
	}
	
	public static void hideTreePanel(WebDriver driver) {
		driver.findElement(By.xpath("//button[@title='Hide Tree Panel']")).click();
	}
	
	public static void showTreePanel(WebDriver driver) {
		driver.findElement(By.xpath("//button[@title='Show Tree Panel']")).click();
	}
	
	public static void newConfig(WebDriver driver, String fileLocation, String fileName) {
		driver.findElement(By.xpath("//div/ul/li[a[text()='" + fileLocation + "']]/a[@title='Create a New Document in this Folder']")).click();
		driver.findElement(By.name("form_namefield")).sendKeys(fileName);
		driver.findElement(By.name("form_:submit")).click();
	}
	
	public static void enableSWCSCrawler(WebDriver driver) throws DocumentException, InterruptedException {
		String currentXML = getXMLFromTextContainer(driver);
		
		Document document = DocumentHelper.parseText(currentXML);
		Element e = (Element) document.selectSingleNode("//module");
		e.addAttribute("enabled", "True");
		
		saveXMLToTextContainer(driver, document);
		saveAndActivateConfig(driver);
	}
}
