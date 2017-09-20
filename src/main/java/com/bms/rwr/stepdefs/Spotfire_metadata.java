package com.bms.rwr.stepdefs;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.bms.rwr.pageobjects.HomePage;
import com.bms.rwr.pageobjects.LoginPage;
import com.bms.rwr.pageobjects.ProjectMetadataPage;

import cucumber.api.java.en.Then;


public class Spotfire_metadata {
	
	HomePage homepage;
	LoginPage loginpage;
	ProjectMetadataPage reportPage;
	boolean result;
	

	public Spotfire_metadata() throws DriverScriptException{		
		homepage = new HomePage();
		loginpage = new LoginPage();
		reportPage = new ProjectMetadataPage();
	}
	
	

	@Then("^select - project metadata$")
	public void selectMetadataTab() throws ObjectNameNotFoundException, WebAdaptorException {
		reportPage.selectProjectMetadataTab();
		//reportPage.confirmProjectOverviewTab();
	}
	
	@Then("^verify project metadata details$")
	public void verifyMetadataDetails() throws ObjectNameNotFoundException, WebAdaptorException {
		reportPage.verifyProjectMetadata();
	}
	
	@Then("^verify project metadata details and data \"([^\"]*)\"$")
	public void verify_project_detail1(String arg1) throws ObjectNameNotFoundException, WebAdaptorException  {
		reportPage.verifyProjDetailDaysProgress(arg1);
	}
	
}
