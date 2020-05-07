package com.sangam.webfactory;

import static com.sangam.utilities.YamlReader.setYamlFilePath;

import com.sangam.actions.HomePageAction;
import com.sangam.getpageobjects.GetPage;
import com.sangam.utilities.CustomFunctions;

public class SangamTestInitiator extends TestSessionInitiator{

	public CustomFunctions customFunctions;
	public HomePageAction homePage;
	

	
		private void _initPage() {
			homePage = new HomePageAction(driver);	
			
		}

	public SangamTestInitiator(String testname) {
		super();
		setProduct();
		setYamlFilePath();
		configureBrowser();
		_initPage();
	}

	public void setProduct(){
		product = "Sangam";
		CustomFunctions.setProduct(product);

		GetPage.setProduct(product);
	}
	
	
}

