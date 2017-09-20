package com.bms.rwr.stepdefs;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.bms.rwr.pageobjects.HomePage;
import com.bms.rwr.pageobjects.LoginPage;
import com.bms.rwr.pageobjects.ProjectOverviewPage;

import cucumber.api.java.en.When;


public class Spotfire_Home {
	
	HomePage homepage;
	LoginPage loginpage;
	ProjectOverviewPage reportPage;
	boolean result;
	

	public Spotfire_Home() throws DriverScriptException{		
		homepage = new HomePage();
		loginpage = new LoginPage();
		reportPage = new ProjectOverviewPage();
	}
	

	@When("^Navigate and select the latest report from DXPs$")
	public void enterUserCredentials() throws  ObjectNameNotFoundException, WebAdaptorException{
		homepage.selectBrowse();
		homepage.selectLatestReport();
	}
	
	@When("^Navigate and select the latest report from UAT$")
	public void selectLatestReportFromUAT() throws  ObjectNameNotFoundException, WebAdaptorException{
		homepage.selectBrowse();
		homepage.selectLatestReportFromUAT();
	}
	
	@When("^Rrunning the report locally and saving in library$")
	public void runSpotfireReport() throws  ObjectNameNotFoundException, WebAdaptorException, InterruptedException{
		homepage.runSpotfireReport();

	}
	
	
}
