package com.bms.rwr.pageobjects;

import com.automation.framework.actionInterpreter.Web;
import com.automation.framework.actionInterpreter.Web.WebAction;
import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;
import com.bms.rwr.utilities.CucumberDriver;


public class BaseReportPage {
	
	TestDriver testDriver;
	public BaseReportPage() throws DriverScriptException {
		testDriver = CucumberDriver.testDriver;
		new ObjectRead(CucumberDriver.testDriver,this.getClass().getSimpleName());
	}	


	public void verifyTheReportHeaders() throws ObjectNameNotFoundException, WebAdaptorException {
	try{
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed,"ProjectOverViewLink");
		DriverScript.logMessage(testDriver,"testStepDone","Project OverView Link Validation is sucessfull");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed,"ProjectSummariesLink");
		DriverScript.logMessage(testDriver,"testStepDone","Project Details Summaries Validation is sucessfull");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed,"ProjectDetailsLink");
		DriverScript.logMessage(testDriver,"testStepDone","Project Details Link Validation is sucessfull");
		DriverScript.logMessage(testDriver,"testStepPass","Validation of report header is sucessfull");
	}
	catch(WebAdaptorException e){
		DriverScript.logMessage(testDriver,"testStepFail","click on browser tab is unsucessfull");
		throw new WebAdaptorException(new Throwable("click on browser tab is unsucessfull",e.getCause()));
		
	}
	}

	
	
}
