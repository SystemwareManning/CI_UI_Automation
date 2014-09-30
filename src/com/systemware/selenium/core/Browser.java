package com.systemware.selenium.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {

	public static WebDriver launch() {
		
		WebDriver driver = null;
		
		switch(CISettings.browserType) {
		
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", CISettings.driverLocation);
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(CISettings.baseUrl);
			break;
		}
		
		return driver;
	}
	
	public static WebDriver relaunch(WebDriver driver) {
		driver.quit();
		driver = Browser.launch();
		
		return driver;
	}
}
