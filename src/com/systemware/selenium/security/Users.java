package com.systemware.selenium.security;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Users {

	public static void copyUsers(WebDriver driver, String fromUser,
			String toUser, String copiedUserPwd) {
		
		driver.findElement(By.xpath("(//a[@title='Show Menu' and following::*[text()='" + fromUser + "']])[last()]")).click();
		
		driver.findElement(By.linkText("Copy User")).click();
		
		driver.findElement(By.name("form_detailssection:username")).sendKeys(toUser);
		driver.findElement(By.name("form_copylink")).click();
		
		driver.findElement(By.name("form_detailssection:pwdcontainer:pwd")).sendKeys(copiedUserPwd);
		driver.findElement(By.name("form_detailssection:pwdcontainer:confirmpwd")).sendKeys(copiedUserPwd);
		
		driver.findElement(By.name("form_savecloselink")).click();
	}

	public static void deleteUser(WebDriver driver, String username) {
		driver.findElement(By.xpath("(//a[@title='Show Menu' and following::*[text()='" + username + "']])[last()]")).click();
		driver.findElement(By.linkText("Delete User")).click();
		driver.findElement(By.xpath("//a[@class='MessageBox_button' and text()='Ok']")).click();		
	}

	public static void create(WebDriver driver, String username, String password) {
		driver.findElement(By.xpath("//a[text()='Add User']")).click();
		
		driver.findElement(By.name("form_detailssection:username")).sendKeys(username);
		
		driver.findElement(By.name("form_detailssection:pwdcontainer:pwd")).sendKeys(password);
		driver.findElement(By.name("form_detailssection:pwdcontainer:confirmpwd")).sendKeys(password);
		
		driver.findElement(By.name("form_detailssection:fullname")).sendKeys(username);
	}
	
	public static void expandGroup(WebDriver driver) throws InterruptedException { 
		driver.findElement(By.xpath("//a[@class='toggle-group open' and text()='Groups']")).click();
		Thread.sleep(500);
	}
	
	public static void assignGroupsToUser(WebDriver driver, String group) throws InterruptedException {

		Actions actions = new Actions(driver);
		
		WebElement menuHoverLink = driver.findElement(By.xpath("//a[@class='rnd-corner-2 group' and text()='" + group +"']"));
		
		actions.moveToElement(menuHoverLink).click().perform();
	}
	
	public static void saveAndClose(WebDriver driver) { 
		driver.findElement(By.name("form_savecloselink")).click();
	}
}
