package com.systemware.selenium.redmine._4_13;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.systemware.selenium.core.Authorization;
import com.systemware.selenium.core.CISettings;
import com.systemware.selenium.core.NavBar;
import com.systemware.selenium.security.Groups;
import com.systemware.selenium.security.Roles;
import com.systemware.selenium.security.Users;

public class RM1780 {
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", CISettings.driverLocation);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(CISettings.baseUrl);
		
		Authorization.adminLogin(driver);
	}

	@Test
	public void test() throws Exception {
		NavBar.navigate(driver, "Administration", "Security", "Roles");
		
		createRoles(1, "security.read-group", "security.read-user");

		createRoles(2, "security.read-group", "security.read-user", "security.update-group", "security.update-user");

		
		createRoles(3, "security.read-group", "security.read-user", "security.delete-group", "security.delete-user");

		
		createRoles(4, "security.read-group", "security.read-user", "security.add-group", "security.add-user");

		
		createRoles(5, "security.read-group", "security.read-user", "security.update-group", "security.update-user", 
				"security.delete-group", "security.delete-user");
		
		createRoles(6, "security.read-group", "security.read-user", "security.delete-group", "security.delete-user", 
				"security.add-group", "security.add-user");

		createRoles(7, "security.read-group", "security.read-user", "security.update-group", "security.update-user", 
				"security.delete-group", "security.delete-user", "security.add-group", "security.add-user");
		
		NavBar.navigate(driver, "Administration", "Security", "Groups");
		
		for(int i = 1; i < 8; i++) {
			createGroup(i);
		}

		NavBar.navigate(driver, "Administration", "Security", "Users");

		for(int i = 1; i < 8; i++) {
			createUser(i);
		}
		
		testEverything(1, "");
		testEverything(2, "AD");
		testEverything(3, "CF");
		testEverything(4, "BE");
		testEverything(5, "ACDF");
		testEverything(6, "BCEF");
		testEverything(7, "ABCDEF");
		
		
		
		Authorization.logout(driver);
		Authorization.adminLogin(driver);

		NavBar.navigate(driver, "Administration", "Security", "Users");
		for(int i = 1; i < 8; i++) {
			String username = "1780TestUser" + i;
			Users.deleteUser(driver, username);
			Thread.sleep(1000);
		}
		
		NavBar.navigate(driver, "Administration", "Security", "Groups");
		for(int i = 1; i < 8; i++) {
			String groupName = "1780TestGroup" + i;
			Groups.deleteGroup(driver, groupName);
			Thread.sleep(1000);
		}
		
		NavBar.navigate(driver, "Administration", "Security", "Roles");
		for(int i = 1; i < 8; i++) {
			String roleName = "1780TestRole" + i;
			Roles.deleteGroup(driver, roleName);
			Thread.sleep(1000);
		}
	}
	
	@After
	public void tearDown() throws Exception {
		Authorization.logout(driver);
		driver.quit();
	}
	
	public void createRoles(int num, String... args) throws InterruptedException {
		String roleName = "1780TestRole" + num;
		
		Roles.create(driver, roleName);
		Roles.expandPermission(driver);
		
		for(String arg : args) {
			Roles.addPermission(driver, arg);
		}

		Roles.save(driver);
		Thread.sleep(1000);
	}
	
	public void createGroup(int num) throws InterruptedException {
		String groupName = "1780TestGroup" + num;
		String roleName = "1780TestRole" + num;
		
		Groups.createGroup(driver, groupName);
		Groups.expandRole(driver);
		
		Groups.assignRoleToGroup(driver, roleName);
		Groups.saveAndCloseGroup(driver);
		Thread.sleep(1000);
	}
	
	public void createUser(int num) throws InterruptedException {
		String username = "1780TestUser" + num;
		String groupName = "1780TestGroup" + num;
		
		Users.create(driver,  username, "admin");
		Users.expandGroup(driver);
		Users.assignGroupsToUser(driver, groupName);
		Users.saveAndClose(driver);
		Thread.sleep(1000);
	}
	
	public void testEverything(int num, String expectedResult) {
		String username = "1780TestUser" + num;
		
		Authorization.logout(driver);
		Authorization.userLogin(driver, username, "admin");
		
		StringBuilder stringBuilder = new StringBuilder();
		
		try {
			NavBar.navigate(driver, "Administration", "Security", "Groups");
			driver.findElement(By.xpath("//a[text()='Menu']")).click();
				try { 
					driver.findElement(By.xpath("//a[text()='Edit Group']"));
					stringBuilder.append("A");
				} catch (Exception e) { }
				try { 
					driver.findElement(By.xpath("//a[text()='Copy Group']"));
					stringBuilder.append("B");
				} catch (Exception e) { }
				try { 
					driver.findElement(By.xpath("//a[text()='Delete Group']"));
					stringBuilder.append("C");
				} catch (Exception e) { }
		} catch(Exception e) { }
		
		try {
			NavBar.navigate(driver, "Administration", "Security", "Users");
			driver.findElement(By.xpath("//a[text()='Menu']")).click();
				try { 
					driver.findElement(By.xpath("//a[text()='Edit User']"));
					stringBuilder.append("D");
				} catch (Exception e) { }
				try { 
					driver.findElement(By.xpath("//a[text()='Copy User']"));
					stringBuilder.append("E");
				} catch (Exception e) { }
				try { 
					driver.findElement(By.xpath("//a[text()='Delete User']"));
					stringBuilder.append("F");
				} catch (Exception e) { }
		} catch(Exception e) { }
		
		
		assertEquals(expectedResult, stringBuilder.toString());
	}
}
