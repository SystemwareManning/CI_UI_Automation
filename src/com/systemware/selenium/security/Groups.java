package com.systemware.selenium.security;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Groups {

	public static void createGroup(WebDriver driver, String groupName) { 
		driver.findElement(By.xpath("//a[text()='Add Group']")).click();
		
		driver.findElement(By.name("form_detailssection:groupname")).sendKeys(groupName);
	}
	
	public static void expandRole(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//a[@class='toggle-group open' and text()='Roles']")).click();
		Thread.sleep(500);
	}
	
	public static void assignRoleToGroup(WebDriver driver, String role) throws InterruptedException {
		Actions actions = new Actions(driver);
		driver.findElement(By.name("form_roleeditpanel:availfilter")).clear();
		driver.findElement(By.name("form_roleeditpanel:availfilter")).sendKeys(role);
		
		WebElement menuHoverLink = driver.findElement(By.xpath("//a[@class='rnd-corner-2 role' and text()='" + role + "']"));
		
		actions.moveToElement(menuHoverLink).click().perform();
	}
	
	public static void saveAndCloseGroup(WebDriver driver) { 
		driver.findElement(By.name("form_savecloselink")).click();
	}
	
	public static void deleteGroup(WebDriver driver, String groupName) {
		driver.findElement(By.xpath("(//a[@title='Show Menu' and following::*[text()='" + groupName + "']])[last()]")).click();
		driver.findElement(By.linkText("Delete Group")).click();
		driver.findElement(By.xpath("//a[@class='MessageBox_button' and text()='Ok']")).click();		
	}
}
