package com.systemware.selenium.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TemplateDesigner {

	public static void addNewInput(WebDriver driver, String arg) {
		// arg = the string to put the input after
		
		driver.findElement(By.xpath("//a[text()='Show Row Menu' and preceding::*[text()='" + arg + "']]")).click();
		driver.findElement(By.xpath("//a[text()='Add Input']")).click();
	}
	
	public static void changeInputName(WebDriver driver, String inputName) {
		driver.findElement(By.name("form_propformcontainer:propform:propform:inputname")).sendKeys(inputName);
	}
	
	public static void setJavaScriptOnChange(WebDriver driver) {
		driver.findElement(By.name("form_propformcontainer:propform:propform:onchangedelay:selectform:textfield")).click();
		driver.findElement(By.xpath("//a[text()='JavaScript OnChange']")).click();
	}
	
	public static void setOnChangeScript(WebDriver driver, String script) {
		driver.findElement(By.name("form_propformcontainer:propform:propform:onchanged")).sendKeys(script);
	}
	
	public static void save(WebDriver driver) throws InterruptedException {
        // JavaScriptExecutor is required to scroll button into Selenium view
		WebDriverWait wait = (new WebDriverWait(driver, 10));
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;										
		jse.executeScript("scroll(250, 0)");						
																									
		driver.findElement(By.xpath("//input[@name='hastemplatecontainer:savebutton']")).click();	
		
		WebElement element = driver.findElement(By.xpath("//a[@title='Close this message.']"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public static void deleteInput(WebDriver driver, String input) {
		
		WebElement element = driver.findElement(By.xpath("//a[text()='Delete Input' and preceding::*[text()='" + input +"']]"));
		
		// JavaScript required to mouseover element and change type=hidden
		String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor)driver).executeScript(javaScript, element);
		
		element.click();
	}
	
	public static void clickFormConfigTab(WebDriver driver) {
		driver.findElement(By.xpath("//a[text()='Form Configuration']")).click();
	}
	
	public static void addNewFormAction(WebDriver driver) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//a[@title='Click to add an action.' and preceding::*[text()='Form Actions']]"));
		
		String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor)driver).executeScript(javaScript, element);
		
		element.click();
		Thread.sleep(1000);
	}
	
	public static void setActionType(WebDriver driver, String type) throws InterruptedException {
		driver.findElement(By.xpath("//span[@class='drop-down-btn' and preceding::*[text()='Action Type:']]")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//a[text()='" + type + "']")).click();
	}
	
	public static void setActionName(WebDriver driver, String name) throws InterruptedException {
		driver.findElement(By.xpath("//span[@class='drop-down-btn' and preceding::*[text()='Name:']]")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//a[text()='" + name + "']")).click();
	}
	
	public static void deleteAction(WebDriver driver, String input) {
		WebElement element = driver.findElement(By.xpath("//a[text()='Delete Action' and preceding::*[text()='" + input +"']]"));
		
		// JavaScript required to mouseover element and change type=hidden
		String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor)driver).executeScript(javaScript, element);
		
		element.click();
	}
}
