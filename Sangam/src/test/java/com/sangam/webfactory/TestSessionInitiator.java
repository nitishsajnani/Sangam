/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sangam.webfactory;

import static com.sangam.utilities.DataPropertFileUtil.getProperty;
import static com.sangam.utilities.YamlReader.getYamlValue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;


public class TestSessionInitiator {

	protected WebDriver driver, originalDriver;
	private WebDriverFactory wdfactory;
	Map<String, Object> chromeOptions = null;
	protected static String product;
	
	
	
	public TestSessionInitiator() {
		wdfactory = new WebDriverFactory();
	}

	protected void configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		driver.manage().window().maximize();
		int timeout;
			timeout = Integer.parseInt(_getSessionConfig().get("timeout"));
		driver.manage().timeouts().implicitlyWait(timeout,TimeUnit.SECONDS);
		originalDriver = driver;
	}
	
	private static Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "timeout",
				"operatingSystem", "wDriverpathIE", "wDriverpathChrome","wDriverpathFirefox","driverpathFirefox", "driverpathChrome", "otherFilesPath","screenshot-path"};
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			
			
			config.put(string, getProperty("./Config.properties", string));
		}
		return config;
	}

	public static String getEnv() {
		String tier = System.getProperty("env");
		if (tier == null)
			tier = _getSessionConfig().get("tier");
		return tier;
	}
	

	public static String getBrowser() {
		String browser = System.getProperty("browser");
		if (browser == null)
			browser = _getSessionConfig().get("browser");
		return browser;
	}

	public String getTakeScreenshot() {
		return _getSessionConfig().get("takeScreenshot");
	}
	
	public String getAutoITScriptPath() {
		return _getSessionConfig().get("autoITPath");
	}

	public static String getProduct() {
		if (System.getProperty("product") != null)
			product = System.getProperty("product");
		return product;
	}

	public void launchApplication() {
		launchApplication(getYamlValue("app_url"));
	}

	public void launchApplication(String applicationpath) {
		Reporter.log("The application url is :- " + applicationpath, true);
		Reporter.log("The test browser is :- " + getBrowser(), true);
		driver.get(applicationpath);
	}
	public void closeBrowserSession() {
		driver.quit();
	}
	public void closeWindow() {
		driver.close();
	}

}
