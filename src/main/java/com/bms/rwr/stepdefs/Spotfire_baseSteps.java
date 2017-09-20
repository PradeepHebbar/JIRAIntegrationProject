package com.bms.rwr.stepdefs;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.bms.rwr.pageobjects.BaseReportPage;
import com.bms.rwr.pageobjects.HomePage;
import com.bms.rwr.pageobjects.LoginPage;
import com.bms.rwr.pageobjects.ProjectOverviewPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;


public class Spotfire_baseSteps {
	
	HomePage homepage;
	LoginPage loginpage;
	ProjectOverviewPage reportPage;
	BaseReportPage baseReportPage;
	boolean result;
	

	public Spotfire_baseSteps() throws DriverScriptException{		
		homepage = new HomePage();
		loginpage = new LoginPage();
		reportPage = new ProjectOverviewPage();
		baseReportPage = new BaseReportPage();
	}
	
	
	
	@And("^select - project overview tab$")
	public void verifyProjectOverView() throws ObjectNameNotFoundException, WebAdaptorException, InterruptedException {
		reportPage.selectProjectOverviewTab();
		reportPage.clearFilter();
		//reportPage.confirmProjectOverviewTab();
	}
	
	@Then("^Shows the report page with all the options$")
	public void baseReport() throws ObjectNameNotFoundException, WebAdaptorException {
		baseReportPage.verifyTheReportHeaders();
	}
	
	
	

}
