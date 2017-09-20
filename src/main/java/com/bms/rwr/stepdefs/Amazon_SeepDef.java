package com.bms.rwr.stepdefs;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.bms.rwr.pageobjects.AmazonLoginPage;
import com.bms.rwr.pageobjects.BaseReportPage;
import com.bms.rwr.pageobjects.HomePage;
import com.bms.rwr.pageobjects.LoginPage;
import com.bms.rwr.pageobjects.ProjectOverviewPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Amazon_SeepDef {
	
	AmazonLoginPage amazonLoginPage;
	

	public Amazon_SeepDef() throws DriverScriptException{		
		amazonLoginPage = new AmazonLoginPage();
	}
	
	
	 @Given("^user navigates to Amazon Login page$")
	    public void user_navigates_to_amazon_login_page() throws Throwable {
	      amazonLoginPage.navigateAmazonLoginPage();
	    }

	 @When("^enter Username and Password and click login$")
	    public void enter_username_and_password_and_click_login() throws Throwable {
	     amazonLoginPage.enterUserName();
	     amazonLoginPage.enterPassword();
	     amazonLoginPage.clickOnLogin();
	    }

	 @Then("^User should be able to see Invalid login message$")
	    public void user_should_be_able_to_see_in_valid_login_message() throws Throwable {
	       amazonLoginPage.verifyErrorMsg();
	    }
	
	 @When("^enter Username and Password and click login1$")
	    public void enter_username_and_password_and_click_login1() throws Throwable {
	     amazonLoginPage.enterUserName1();
	     amazonLoginPage.enterPassword();
	     amazonLoginPage.clickOnLogin();
	    }


}
