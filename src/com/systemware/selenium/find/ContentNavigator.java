package com.systemware.selenium.find;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ContentNavigator {

	public static void expandMenu(WebDriver driver, String nodeName) throws InterruptedException {
		
		Thread.sleep(500);
		// driver.findElement(By.xpath("//a[text()='" + nodeName + "']")).click();
		driver.findElement(By.xpath("//a[text()='" + nodeName + "']")).sendKeys(Keys.ENTER);
		Thread.sleep(500);
	}
	
	public static void findDSID(WebDriver driver, String dsid) throws InterruptedException {
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@title='" + dsid + "']")).click();
	}
	
	public static void addReportToFav(WebDriver driver, String report) throws InterruptedException {
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//a[@class='menu-button' and preceding::*[@title='" + report + "']]")).click();
		
		driver.findElement(By.xpath("//a[text()='Add Report to Favorites']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[text()='Close']")).click();
	}
	
	public static void deleteFav(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Delete Favorite']")).click();
		Thread.sleep(200);
		
		driver.findElement(By.xpath("//a[text()='Ok']")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//button[text()='Close']")).click();
		Thread.sleep(200);
	}
	
	public static void moveZoomSlider(WebDriver driver, int xOffset, int yOffset) {
		WebElement element = driver.findElement(By.xpath("//*[@id='zoomslider']"));
		
		Actions move = new Actions(driver);
		Action action = move.dragAndDropBy(element, xOffset, yOffset).build();
		action.perform();
	}
	
	public static void closeDocument(WebDriver driver) { 
		driver.findElement(By.xpath("//input[@value='Close']")).click();
	}
	
	public static void addNoteToDocument(WebDriver driver) {
		driver.findElement(By.xpath("//a[@title='Add Note to Document']")).click();
	}
	
	public static void setNoteText(WebDriver driver, String text) {
		driver.findElement(By.xpath("//textarea[@class='new-note-text']")).sendKeys(text);
	}
	
	public static void saveNote(WebDriver driver) {
		driver.findElement(By.xpath("//a[@title='Save Note to Image']")).click();
	}
	
	public static void deleteNote(WebDriver driver) { 
		driver.findElement(By.xpath("//a[text()='Delete this note.']")).click();
		driver.findElement(By.xpath("//a[text()='Ok']")).click();
	}
	
	public static void navigateActionsMenu(WebDriver driver, String... args) throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Actions Menu']")).click();
		
		Actions actions = new Actions(driver);
		
		for(String arg : args) {
			Thread.sleep(300);
			WebElement menuHoverLink = driver.findElement(By.xpath("//a[text()='" + arg + "']"));
			actions.moveToElement(menuHoverLink);
		}
		
		actions.click();
		actions.perform();
	}
}
