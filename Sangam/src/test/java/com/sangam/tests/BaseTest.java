package com.sangam.tests;

import static com.sangam.utilities.YamlReader.getYamlValue;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import com.sangam.webfactory.SangamTestInitiator;

@Listeners()
public class BaseTest {
	protected boolean isTestRunCreated = false;
	protected SangamTestInitiator Sangam;
	protected String product;
	protected int counterForTests;
	protected int failCount;
	protected String baseUrl;

	public BaseTest(String baseUrl) {
		this.baseUrl = baseUrl;
		System.out.println("baseURL::" + baseUrl);
	}

	@BeforeClass
	public void beforeMethod() {
		Sangam = new SangamTestInitiator(this.getClass().getSimpleName());
		Sangam.launchApplication(getYamlValue(baseUrl));
	}

	@AfterClass(alwaysRun = true)
	public void close_Test_Session() throws IOException {
	//	Sangam.closeBrowserSession();
	}

}
