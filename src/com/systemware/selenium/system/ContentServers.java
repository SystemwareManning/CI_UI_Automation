package com.systemware.selenium.system;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContentServers {

	public static void delete(WebDriver driver, String csName) {
		driver.findElement(By.xpath("//a[@class='delete-btn' and following::*[text()='" + csName + "']]")).click();
		driver.findElement(By.xpath("//a[text()='Ok']")).click();
		driver.findElement(By.xpath("//a[text()='X']")).click();
	}
	
	public static void create(WebDriver driver, String name, String host, String port,
			String secid, String pwd, String confirmpwd, String account, String user) {
		
		driver.findElement(By.xpath("//a[text()='Add Content Server']")).click();
		driver.findElement(By.name("form_name")).sendKeys(name);
		driver.findElement(By.name("form_host")).sendKeys(host);
		driver.findElement(By.name("form_port")).sendKeys(port);
		driver.findElement(By.name("form_secid")).sendKeys(secid);
		driver.findElement(By.name("form_pwd")).sendKeys(pwd);
		driver.findElement(By.name("form_confirmpwd")).sendKeys(confirmpwd);
		driver.findElement(By.name("form_account")).sendKeys(account);
		driver.findElement(By.name("form_user")).sendKeys(user);
		
		driver.findElement(By.name("form_submitlink")).click();
	}
}
