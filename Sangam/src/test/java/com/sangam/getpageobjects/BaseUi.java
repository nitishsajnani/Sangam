package com.sangam.getpageobjects;

import static com.sangam.utilities.DataPropertFileUtil.getProperty;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.sangam.utilities.SeleniumWait;

public class BaseUi {

	WebDriver driver, driverToUploadImage;
	protected SeleniumWait wait;


	protected String browser;

	protected BaseUi(WebDriver driver, String pageName) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		int timeout;
		timeout = Integer.parseInt(getProperty("Config.properties", "timeout"));
		this.wait = new SeleniumWait(driver, timeout);
	}
	
	protected void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public void logMessage(String message) {
		Reporter.setEscapeHtml(true);
		Reporter.log(message, true);
	}
	
	public void clickOnButton(WebElement element,String text) {
			wait.waitForElementToBeVisible(element);
			element.click();
			logMessage("User clicks on the button: "+ text);	
		}
	public void enterText(WebElement element, String text) {
		try {
			wait.waitForElementToBeVisible(element);
			element.clear();
			element.sendKeys(text);
			logMessage("User enters the "+text);
			
		} catch (StaleElementReferenceException ex1) {
			element.clear();
			element.sendKeys(text);
			logMessage("Entered Text '" + text + "' in Element " + element + " after catching Stale Element Exception");
		} catch (UnhandledAlertException u) {
			element.clear();
			element.sendKeys(text);
		}
	}

}