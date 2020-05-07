package com.sangam.actions;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.sangam.getpageobjects.GetPage;

public class HomePageAction extends GetPage {

	WebDriver driver;

	public HomePageAction(WebDriver driver) {
		super(driver, "HomePage");
		this.driver = driver;
	}

	public void clickOnLetsBegin() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(674,403)");
		Thread.sleep(3000);
		clickOnButton(element("bth_letsBegin"), "lets begin");

	}

	public void verifyErrorMessage(String element, String message) throws InterruptedException {
		Thread.sleep(3000);
		String messageFromElement = element(element).getText();
		Assert.assertEquals(messageFromElement, message);
		Reporter.log("Verified the error message: " + messageFromElement, true);

	}

	public void selectProfileCreator(String creator) {
		element("dd_profileCreator").click();
		element("op_profileCreator", creator).click();
		logMessage("User selects the profile creator: " + creator);
	}

	public void selectGender(String gender) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(812,331)");
		Thread.sleep(3000);
		element("radio_genderMale").click();
		logMessage("User selects the profile gender: " + gender);
	}

	public void enterDateOfBirth(String dateOfBirth) {
		List<WebElement> date = elements("inp_dob");

		for (int i = 0; i < date.size(); i++) {
			date.get(i).sendKeys(dateOfBirth.substring(i, i + 1));
		}
		logMessage("User enters the date of birth the profile gender: " + dateOfBirth);

	}

	public void selectMotherTongue(String motherTongue) throws InterruptedException {
		element("search_motherTongue").click();
		Thread.sleep(2000);
		;
		element("select_motherTongue", motherTongue).click();
		logMessage("User selects mother tongue from search list: " + motherTongue);

	}

	public void selectCommunity(String community) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Thread.sleep(2000);
		element("search_community").click();
		Thread.sleep(2000);
		element("select_community", community).click();
		logMessage("User selects community from search list: " + community);
	}

	public void verifyCreateAccountHeader(String header) {
		String createAccountHeader = element("header_creatAccount").getText();
		Assert.assertEquals(createAccountHeader, header);
		logMessage("verify user is on email view: " + header);

	}

	public void verifyWhereSendProfileEmail(String emailMessage)  {
		String sendEmailMessage = element("msg_sendEmail").getText();
		Assert.assertEquals(sendEmailMessage, emailMessage);
		logMessage("verify user is on email view: " + emailMessage);

	}
}