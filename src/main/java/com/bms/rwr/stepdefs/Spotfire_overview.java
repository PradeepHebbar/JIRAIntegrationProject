package com.bms.rwr.stepdefs;

import java.io.FileNotFoundException;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.bms.rwr.pageobjects.HomePage;
import com.bms.rwr.pageobjects.LoginPage;
import com.bms.rwr.pageobjects.ProjectOverviewPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Spotfire_overview {
	
	HomePage homepage;
	LoginPage loginpage;
	ProjectOverviewPage reportPage;
	boolean result;
	

	public Spotfire_overview() throws DriverScriptException{		
		homepage = new HomePage();
		loginpage = new LoginPage();
		reportPage = new ProjectOverviewPage();
	}
	
	
	@When("^Search for the report$")
	public void searchForReport() throws ObjectNameNotFoundException, WebAdaptorException {
		homepage.selectBrowse();
		homepage.searchForResult("Activity_S2_  Reports-021617-Modified-V2-Feb16");
		homepage.selectRecentResult();
	}
	
	@When("^Search for the report1 \"([^\"]*)\"$")
	public void searchForReport_with_parameter(String report) throws ObjectNameNotFoundException, WebAdaptorException {
		homepage.selectBrowse();
		homepage.searchForResult(report);
		homepage.selectSearchedReport(report);
	}
	
	@And("^select - project overview$")
	public void verifyProjectOverView() throws ObjectNameNotFoundException, WebAdaptorException, InterruptedException {
		reportPage.selectProjectOverviewTab();
		reportPage.clearFilter();
		//reportPage.confirmProjectOverviewTab();
	}
	
	@Then("^verify project overview details$")
	public void projectOverviewDetails() throws ObjectNameNotFoundException, WebAdaptorException {
		reportPage.verifyProjectOverviewData();
	}
	

	@Then("^verify project overview- total projects with activity \"([^\"]*)\" and \"([^\"]*)\"$")
	public void verify_project_overview_total_projects_with_activity_and(String arg1, String arg2) throws ObjectNameNotFoundException, WebAdaptorException  {
		reportPage.verifyProjectActivityData(arg1, arg2);
	}
	
	@When("^change the dates for the selected region$")
	public void selectFromToDate() throws ObjectNameNotFoundException, WebAdaptorException, InterruptedException{
		reportPage.selectFilterDates();
	}
	
	 
	@Then("^Values of Active project data should match the analytical data$")
	public void verifyActiveProjectData() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException{
		reportPage.verifyActiveProjectCount();
	}
	
	@Then("^Values of Active projects completed data should match the analytical data$")
	public void verifyActiveProjectsCompletedData() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException{
		reportPage.verifyActiveProjectsCompleted();
	}
	
	@Then("^Values of In-Progress project data should match the analytical data$")
	public void verifyInProgressProjectData() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException{
		reportPage.verifyInProgressProjectCount();
	}
	
	@Then("^Values of Mandatory Metadata Completed should match the analytical data$")
	public void verifyMandatoryMetadataCompleted() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException{
		reportPage.verifyMandatoryMetadataCompleted();
	}
	
	@Then("^Percentage of Has Project Status should match the analytical data$")
	public void verifyHasProjectStatus() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException{
		reportPage.verifyHasProjectStatus();
	}
	
	@Then("^Percentage of Has Project Start Date should match the analytical data$")
	public void verifyHasProjectStartDate() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException{
		reportPage.verifyHasProjectStartDate();
	}
	
	@Then("^percentage Project Target Date should match the analytical data$")
	public void verifyHasProjectTargetDate() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException{
		reportPage.verifyHasProjectTargetDate();
	}
	
	@Then("^verify the project overview page for left navigaion$")
	public void verifyLeftNavigation() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException{
		reportPage.verifyLeftNavigation();
		//reportPage.verifyProjectOverviewData();
	}
	
	@Then("^verify the project overview page for right navigaion$")
	public void verifyRightNavigation() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException{
		reportPage.verifyRightNavigation();
	}
	
}
