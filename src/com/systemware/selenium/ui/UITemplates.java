package com.systemware.selenium.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class UITemplates {

	public static void editUITemplate(WebDriver driver, String ID) throws InterruptedException {
		driver.findElement(By.xpath("(//a[@title='Show Menu' and following::*[text()='" + ID + "']])[last()]")).click();
		driver.findElement(By.xpath("//a[text()='Edit UI Template']")).click();
		Thread.sleep(1000);
	}
	
	public static void filterTemplates(WebDriver driver, String filter) throws InterruptedException {
		driver.findElement(By.xpath("//input[@type='text' and @name='filter']")).sendKeys(filter);
		Thread.sleep(1000);
	}
	
	public static void addActiongroup(WebDriver driver, String addToWhere, String actionGroup) throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Add Action Group' and preceding::*[text()='" + addToWhere +"']]")).click();
		driver.findElement(By.name("form_actiongroup:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[text()='" + actionGroup + "']")).click();
		driver.findElement(By.name("form_:submit")).click();
		Thread.sleep(300);
	}
	
	public static void saveTemplate(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("scroll(250,0);");
		
		driver.findElement(By.name("hastemplatecontainer:savebutton")).click();
		
		driver.findElement(By.xpath("//a[text()='X']")).click();
	}
}
