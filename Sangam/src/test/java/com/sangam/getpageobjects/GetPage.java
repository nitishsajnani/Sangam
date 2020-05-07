package com.sangam.getpageobjects;

import static com.sangam.getpageobjects.ObjectFileReader.getELementFromFile;
import static com.sangam.utilities.ConfigPropertyReader.getProperty;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sangam.utilities.SeleniumWait;

public class GetPage extends BaseUi {

	protected WebDriver webdriver;
	String pageName;
	static String product;

	protected SeleniumWait wait;
	int x = 10;

	public static void setProduct(String productName) {
		product = productName;
	}

	public GetPage(WebDriver driver, String pageName) {
		super(driver, pageName);
		this.webdriver = driver;
		this.pageName = pageName;
		this.wait = new SeleniumWait(driver, Integer.parseInt(getProperty("Config.properties", "timeout")));
	}

	

	protected WebElement element(String elementToken) {
		return element(elementToken, "");
	}

	protected WebElement element(String elementToken, String replacement) {
		WebElement elem = null;
		try {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, replacement)));
		} catch (NoSuchElementException excp) {
			fail("FAILED: Element '" + elementToken + "' not found on the " + this.pageName + " !!!");
		}
		return elem;
	}

	
	protected List<WebElement> elements(String elementToken, String replacement) {
		return wait.waitForElementsToBeVisible(webdriver.findElements(getLocator(elementToken, replacement)));
	}

	

	protected List<WebElement> elements(String elementToken) {
		return elements(elementToken, "");
	}

	protected boolean isElementDisplayed(String elementName, String elementTextReplace) {
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
		boolean result = element(elementName, elementTextReplace).isDisplayed();
		logMessage("TEST PASSED: element " + elementName + " with text " + elementTextReplace + " is displayed.");
		return result;
	}

	

	protected boolean isElementDisplayed(String elementName) {
		wait.waitForPageToLoadCompletely();
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = element(elementName).isDisplayed();
		assertTrue(result, "TEST FAILED: element '" + elementName + "' is not displayed.");
		logMessage("Element " + elementName + " is displayed.");
		return result;
	}


	protected By getLocator(String elementToken) {
		return getLocator(elementToken, "");
	}

	protected By getLocator(String elementToken, String replacement) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	protected By getLocator(String elementToken, String replacement1, String replacement2) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement1);
		locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement2);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	public void clickOnFirstLinkBasedOnProvidedText(String elementToken, String linkText) {
		element(elementToken, linkText).click();
	}

	public boolean matchGivenPatternWithProvidedText(String pattern, String text) {
		Matcher matcher = Pattern.compile(pattern).matcher(text);
		return matcher.matches();
	}

	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);

		case xpath:
			return By.xpath(locatorValue);
		case css:
			return By.cssSelector(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		case linktext:
			return By.linkText(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}

	
	


	
}
