package com.systemware.selenium.security;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Roles {

	public static void create(WebDriver driver, String roleName) {
		driver.findElement(By.xpath("//a[text()='Add Role']")).click();
		
		driver.findElement(By.name("form_detailssection:rolename")).sendKeys(roleName);
	}
	
	public static void expandPermission(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//a[@class='toggle-group open' and text()='Permissions']")).click();
		Thread.sleep(500);
	}
	public static void addPermission(WebDriver driver, String permission) throws InterruptedException {

		Actions actions = new Actions(driver);
		
		driver.findElement(By.name("form_permissioneditpanel:availfilter")).clear();
		driver.findElement(By.name("form_permissioneditpanel:availfilter")).sendKeys(permission);

		WebElement menuHoverLink = driver.findElement(By.xpath("//a[@class='rnd-corner-2 permission' and text()='" + permission + "']"));
		
		actions.moveToElement(menuHoverLink).click().perform();
	}
	
	public static void save(WebDriver driver) { 
		driver.findElement(By.name("form_submitlink")).click();
	}
	
	public static void deleteGroup(WebDriver driver, String roleName) {
		driver.findElement(By.xpath("(//a[@title='Show Menu' and following::*[text()='" + roleName + "']])[last()]")).click();
		driver.findElement(By.linkText("Delete Role")).click();
		driver.findElement(By.xpath("//a[@class='MessageBox_button' and text()='Ok']")).click();		
	}
}
