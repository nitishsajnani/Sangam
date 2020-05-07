package com.sangam.utilities;

import org.openqa.selenium.WebDriver;

public class CustomFunctions{
	static WebDriver driver;
	static String productName;

	public CustomFunctions(WebDriver driver) {
		CustomFunctions.driver = driver;
	}	
	public static void setProduct(String product) {
		productName = product;
	}	
}