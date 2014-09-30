package com.systemware.selenium.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CIService {
	
	static String APP_SERVICE_NAME = "Systemware CI";

	
	
	
	public static void start() {
		String[] startScript = {"cmd.exe", "/c", "sc", "start", APP_SERVICE_NAME};//to start service
		
		try { 
			Process process = new ProcessBuilder(startScript).start();
			InputStream inputStream = process.getInputStream();
			InputStreamReader inputStreamReader = new 
					InputStreamReader(inputStream);
			BufferedReader bufferedReader = new
					BufferedReader(inputStreamReader);
			String line;
			
		} catch (Exception e) { System.out.println(e); }
	}
	
	public static void stop() {
		String[] stopScript = {"cmd.exe", "/c", "sc", "stop", APP_SERVICE_NAME};//to stop service
		
		try { 
			Process process = new ProcessBuilder(stopScript).start();
			InputStream inputStream = process.getInputStream();
			InputStreamReader inputStreamReader = new 
					InputStreamReader(inputStream);
			BufferedReader bufferedReader = new
					BufferedReader(inputStreamReader);
			String line;
			
		} catch (Exception e) { System.out.println(e); }
	}
}
