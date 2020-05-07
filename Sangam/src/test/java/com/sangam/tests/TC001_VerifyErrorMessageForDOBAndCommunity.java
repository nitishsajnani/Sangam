package com.sangam.tests;

import static com.sangam.utilities.YamlReader.getYamlValue;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sangam.tests.BaseTest;

public class TC001_VerifyErrorMessageForDOBAndCommunity extends BaseTest {

	public TC001_VerifyErrorMessageForDOBAndCommunity(String baseUrl) {
		super("loginApp.baseUrl");
	}
	

	@Test
	public void TestStep01_UserVerifyErrorMessageForDOBAndCommunity() throws InterruptedException {
	   Sangam.homePage.clickOnLetsBegin();
	   Sangam.homePage.verifyErrorMessage("msg_createProfile","Please specify whose profile is being created");
	   Sangam.homePage.verifyErrorMessage("msg_dob","Please specify Date of Birth");
	   Sangam.homePage.verifyErrorMessage("msg_community","Please specify Caste / Community");
		Reporter.log("User is successfully login into the application",true);
	}
	
	}
