package com.systemware.selenium.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavBar {

	public static void navigate(WebDriver driver, String img, String... args) throws InterruptedException {
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//img[@alt='" + img + "']")).click();
		for(String arg : args) {
			driver.findElement(By.xpath("//span[text()='" + arg + "']")).click();
		}
		
		Thread.sleep(500);
	}
}
