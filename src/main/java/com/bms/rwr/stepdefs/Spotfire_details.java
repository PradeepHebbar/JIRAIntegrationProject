package com.bms.rwr.stepdefs;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.bms.rwr.pageobjects.HomePage;
import com.bms.rwr.pageobjects.LoginPage;
import com.bms.rwr.pageobjects.ProjectDetailsPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;


public class Spotfire_details {
	
	HomePage homepage;
	LoginPage loginpage;
	ProjectDetailsPage projectDetailsPage;
	boolean result;
	

	public Spotfire_details() throws DriverScriptException{		
		homepage = new HomePage();
		loginpage = new LoginPage();
		projectDetailsPage = new ProjectDetailsPage();
	}
	
	
	@And("^select - project details$")
	public void verifyProjectOverView() throws ObjectNameNotFoundException, WebAdaptorException {
		projectDetailsPage.selectProjectDetailTab();
		projectDetailsPage.clearFilter();
	
	}
	
	@Then("^verify the project details section$")
	public void projectOverviewDetails() throws ObjectNameNotFoundException, WebAdaptorException {
		projectDetailsPage.verifyProjectDetailData();
	}
	
	@Then("^verify the project details section- project name \"([^\"]*)\"$")
	public void verify_project_detail1(String arg1) throws ObjectNameNotFoundException, WebAdaptorException  {
		projectDetailsPage.verifyProjDetailDaysProgress(arg1);
	}
}
