package com.systemware.selenium.system;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CAMCreator {

	public static void nextPage(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//input[@title='Go to next page']")).click();
		Thread.sleep(1000);
	}
	
	public static void back(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//input[@title='Go to previous page']")).click();
		Thread.sleep(500);
	}
	
	public static void setCAMID(WebDriver driver, String camID) {
		driver.findElement(By.name("form_propformcontainer:propform:camid")).sendKeys(camID);
	}
	
	public static void relationalDatabase(WebDriver driver, String input) {
		
		if (input.equalsIgnoreCase("yes")) {
			driver.findElement(By.xpath("//label[text()=' Yes']/input[@name='form_propformcontainer:propform:reldb']")).click();
		}
		
		else if (input.equalsIgnoreCase("no")) {
			driver.findElement(By.xpath("//label[text()=' No']/input[@name='form_propformcontainer:propform:reldb']")).click();
		}
	}
	
	public static void contentTopic(WebDriver driver, String input) {
		
		if (input.equalsIgnoreCase("yes")) {
			driver.findElement(By.xpath("//label[text()=' Yes']/input[@name='form_propformcontainer:propform:content']")).click();
		}
		
		if (input.equalsIgnoreCase("no")) {
			driver.findElement(By.xpath("//label[text()=' No']/input[@name='form_propformcontainer:propform:content']")).click();
		}
	}
	
	public static void fullTextContent(WebDriver driver, String input) {
		if (input.equalsIgnoreCase("yes")) {
			driver.findElement(By.xpath("//label[text()=' Yes']/input[@name='form_propformcontainer:propform:yescontent:fulltextcontent']")).click();
		}
		
		if (input.equalsIgnoreCase("no")) {
			driver.findElement(By.xpath("//label[text()=' No']/input[@name='form_propformcontainer:propform:yescontent:fulltextcontent']")).click();
		}
	}
	
	public static void captureService(WebDriver driver, String input) {
		if (input.equalsIgnoreCase("yes")) {
			driver.findElement(By.xpath("//label[text()=' Yes']/input[@name='form_propformcontainer:propform:yescontent:captureservice']")).click();
		}
		
		if (input.equalsIgnoreCase("no")) {
			driver.findElement(By.xpath("//label[text()=' No']/input[@name='form_propformcontainer:propform:yescontent:captureservice']")).click();
		}
	}
	
	public static void notification(WebDriver driver, String input) {
		
		if (input.equalsIgnoreCase("yes")) {
			driver.findElement(By.xpath("//label[text()=' Yes']/input[@name='form_propformcontainer:propform:notificationcontainer:notification']")).click();
		}
		
		if (input.equalsIgnoreCase("no")) {
			driver.findElement(By.xpath("//label[text()=' No']/input[@name='form_propformcontainer:propform:notificationcontainer:notification']")).click();
		}
	}
	
	public static void uiCreate(WebDriver driver, String input) {
		
		if (input.equalsIgnoreCase("yes")) {
			driver.findElement(By.xpath("//label[text()=' Yes']/input[@name='form_propformcontainer:propform:nocontent:uicreate']")).click();
		}
		
		if (input.equalsIgnoreCase("no")) {
			driver.findElement(By.xpath("//label[text()=' No']/input[@name='form_propformcontainer:propform:nocontent:uicreate']")).click();
		}
	}
	
	public static void uiUpload(WebDriver driver, String input) {
		
		if (input.equalsIgnoreCase("yes")) {
			driver.findElement(By.xpath("//label[text()=' Yes']/input[@name='form_propformcontainer:propform:content:uploadcontent']")).click();
		}
		
		if (input.equalsIgnoreCase("no")) {
			driver.findElement(By.xpath("//label[text()=' No']/input[@name='form_propformcontainer:propform:content:uploadcontent']")).click();
		}
	}
	
	public static void uiUpdate(WebDriver driver, String input) {
		
		if (input.equalsIgnoreCase("yes")) {
			driver.findElement(By.xpath("//label[text()=' Yes']/input[@name='form_propformcontainer:propform:uiupdate']")).click();
		}
		
		if (input.equalsIgnoreCase("no")) {
			driver.findElement(By.xpath("//label[text()=' No']/input[@name='form_propformcontainer:propform:uiupdate']")).click();
		}
	}
	
	public static void uiDelete(WebDriver driver, String input) {
		
		if (input.equalsIgnoreCase("yes")) {
			driver.findElement(By.xpath("//label[text()=' Yes']/input[@name='form_propformcontainer:propform:uidelete']")).click();
		}
		
		if (input.equalsIgnoreCase("no")) {
			driver.findElement(By.xpath("//label[text()=' No']/input[@name='form_propformcontainer:propform:uidelete']")).click();
		}
	}
	
	public static void setCAMName(WebDriver driver, String input) {
		driver.findElement(By.name("form_propformcontainer:propform:camname")).sendKeys(input);
	}

	public static void setCAMOTID(WebDriver driver, String input) {
		driver.findElement(By.name("form_propformcontainer:propform:otid")).sendKeys(input);
	}
	
	public static void setCAMTopicName(WebDriver driver, String input) { 
		driver.findElement(By.name("form_propformcontainer:propform:topicname")).sendKeys(input);
	}
}
