package com.systemware.selenium.system;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.systemware.selenium.core.CAMLocations;

public class CAMs {
	
	public static void install(WebDriver driver, String cam) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Install CAM']")));
		driver.findElement(By.xpath("//a[text()='Install CAM']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		element = driver.findElement(By.name("form_file"));
		
		js.executeScript("arguments[0].setAttribute('class', '')",element);
		driver.findElement(By.name("form_file")).sendKeys(cam);
		
		driver.findElement(By.xpath("//input[@value='Install']")).click();
		driver.findElement(By.xpath("//a[text()='X']")).click();
		
		Thread.sleep(500);
	}
	
	public static void uninstall(WebDriver driver, String cam) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		driver.findElement(By.xpath("(//a[@title='Show Menu' and following::*[text()='" + cam + "']])[last()]")).click();
		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Uninstall']")));
		element.click();
		
		driver.findElement(By.xpath("//a[text()='Ok']")).click();
		
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='X']")));
		element.click();
		
		driver.navigate().refresh();
		Thread.sleep(1000);
	}
	
	public static void startCAM(WebDriver driver, String cam) {
		
		driver.findElement(By.xpath("//a[@title='Show Menu' and following::*[text()='" + cam + "']]")).click();
		driver.findElement(By.xpath("//a[text()='Start']")).click();
		
		driver.findElement(By.xpath("//a[text()='Ok']")).click();
		driver.findElement(By.className("close-button")).click();
	}
	
	public static void installSWCS(WebDriver driver) throws InterruptedException {
		install(driver, CAMLocations.swbaseuser);
		install(driver, CAMLocations.swcache);
		install(driver, CAMLocations.swcs);
		install(driver, CAMLocations.swfavorite);
		install(driver, CAMLocations.swscheduler);
		install(driver, CAMLocations.swstore);
		install(driver, CAMLocations.swbasenote);
		install(driver, CAMLocations.swappconfig);
	}
	
	public static void uninstallSWCS(WebDriver driver) throws InterruptedException {
		uninstall(driver, "swappconfig");
		uninstall(driver, "swbasenote");
		uninstall(driver, "swbaseuser");
		uninstall(driver, "swcache");
		uninstall(driver, "swcs");
		uninstall(driver, "swfavorite");
		uninstall(driver, "swscheduler");
		uninstall(driver, "swstore");
	}
}
