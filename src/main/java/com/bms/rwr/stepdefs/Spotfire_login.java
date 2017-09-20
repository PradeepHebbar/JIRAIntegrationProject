package com.bms.rwr.stepdefs;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.bms.rwr.pageobjects.HomePage;
import com.bms.rwr.pageobjects.LoginPage;
import com.bms.rwr.pageobjects.ProjectOverviewPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Spotfire_login {
	
	HomePage homepage;
	LoginPage loginpage;
	ProjectOverviewPage reportPage;
	boolean result;
	

	public Spotfire_login() throws DriverScriptException{		
		homepage = new HomePage();
		loginpage = new LoginPage();
		reportPage = new ProjectOverviewPage();
	}
	
	@Given("^user navigates to BMS spotfire Login page$")
	public void loginToSpotfire() throws ObjectNameNotFoundException, WebAdaptorException{
		loginpage.signIntoSpotfire();
	}

	@When("^enter Username as and Password$")
	public void enterUserCredentials() throws  ObjectNameNotFoundException, WebAdaptorException{
		loginpage.enterUserName();
		loginpage.enterPassword();
	}
	
	@Then("^login should be successful$")
	public void verifyHomeScreen() throws  ObjectNameNotFoundException, WebAdaptorException{
		loginpage.clickOnSignin();
		//homepage.verifyHomePage();
	}
	
	
}
