package com.systemware.selenium.system;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EIXMappingRules {

	public static void create(WebDriver driver) { 
		driver.findElement(By.xpath("//input[@value='New Enterprise Index Mapping Rule']")).click();
	}
	
	public static void selectFolder(WebDriver driver, String folder) throws InterruptedException {
		driver.findElement(By.name("form_templategroup:1:groupchild:2:templateinput:2:input:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[text()='" + folder + "']")).click();
		Thread.sleep(1000);
	}
	
	public static void selectReport(WebDriver driver, String report) throws InterruptedException { 
		driver.findElement(By.name("form_templategroup:1:groupchild:3:templateinput:input:selectform:textfield")).click();;
		driver.findElement(By.xpath("//a[text()='" + report + "']")).click();
		Thread.sleep(1000);
	}
	
	public static void setEIXGroup(WebDriver driver, String eixGroup) {
		driver.findElement(By.name("form_templategroup:1:groupchild:4:templateinput:input")).clear();
		driver.findElement(By.name("form_templategroup:1:groupchild:4:templateinput:input")).sendKeys(eixGroup);
	}
	
	public static void selectIndex(WebDriver driver, String index) {
		driver.findElement(By.name("form_templategroup:2:groupchild:1:templateinput:input:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[text()='" + index + "']")).click();
	}
	
	public static void appendIndex(WebDriver driver) {
		driver.findElement(By.xpath("//input[@value='Append Index']")).click();
	}
	
	public static void saveIndex(WebDriver driver) { 
		driver.findElement(By.name("form_buttonbar:savebutton")).click();
	}
}
