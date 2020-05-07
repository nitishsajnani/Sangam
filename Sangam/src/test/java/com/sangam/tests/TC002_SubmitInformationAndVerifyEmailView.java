package com.sangam.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TC002_SubmitInformationAndVerifyEmailView extends BaseTest {
	String profileCreator = "Self";
	String gender="Male";
	String dateOfBirth = "15121990";
	String motherTongue="Hindi";
	String  community="Hindu - Agarwal";

	public TC002_SubmitInformationAndVerifyEmailView(String baseUrl) {
		super("loginApp.baseUrl");
	}

	@Test
	public void StepTC001_submitRegistrationDetails() throws InterruptedException {
		Sangam.homePage.selectProfileCreator(profileCreator);
		Sangam.homePage.selectGender("gender");
		Sangam.homePage.enterDateOfBirth(dateOfBirth);
		Sangam.homePage.selectMotherTongue(motherTongue);
		Sangam.homePage.selectCommunity(community);
		Sangam.homePage.clickOnLetsBegin();
		Reporter.log("User is successfully submitted the registration details",true);

	}
	@Test
	public void StepTC002_verifyUserIsOnEmailAccountView()
	{
		Sangam.homePage.verifyCreateAccountHeader("Create your account");
		Sangam.homePage.verifyWhereSendProfileEmail("Where should we send you Profiles?");
		Reporter.log("User is successfully login into the application",true);

	}
}