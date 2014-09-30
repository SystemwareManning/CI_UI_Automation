package com.systemware.selenium.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Authorization {

	public static void adminLogin(WebDriver driver) {
		driver.findElement(By.name("form_userid")).sendKeys("admin");
		driver.findElement(By.name("form_password")).sendKeys("admin");
		driver.findElement(By.name("form_password")).submit();
	}
	
	public static void logout(WebDriver driver) {
		driver.get(CISettings.baseUrl + "ui/logout");
		driver.findElement(By.linkText("here")).click();
	}

	public static void userLogin(WebDriver driver, String username,
			String password) {
		driver.findElement(By.name("form_userid")).sendKeys(username);
		driver.findElement(By.name("form_password")).sendKeys(password);
		
		driver.findElement(By.name("form_password")).submit();
	}
}
