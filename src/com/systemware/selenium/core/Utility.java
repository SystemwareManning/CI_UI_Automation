package com.systemware.selenium.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Utility {

	public static void refreshTemplate(WebDriver driver) throws InterruptedException {
		driver.findElement(By.className("template-refresh-btn")).click();
		Thread.sleep(500);
	}
}
