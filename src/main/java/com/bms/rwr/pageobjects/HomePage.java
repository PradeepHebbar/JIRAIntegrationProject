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


public class HomePage {
	
	TestDriver testDriver;
	public HomePage() throws DriverScriptException {
		testDriver = CucumberDriver.testDriver;
		new ObjectRead(CucumberDriver.testDriver,this.getClass().getSimpleName());
	}	


	public void verifyHomePage() throws ObjectNameNotFoundException, WebAdaptorException {
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "TibcSpotfireLogo");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "TibcSpotfireLogo");
	}

	public void selectBrowse() throws ObjectNameNotFoundException, WebAdaptorException {
		try{
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "BrowseTab");
		Web.webAdaptor(testDriver, WebAction.click, "BrowseTab");	
		DriverScript.logMessage(testDriver,"testStepDone","click on browser tab is sucessfull");
	}
	catch(WebAdaptorException e){
		DriverScript.logMessage(testDriver,"testStepFail","click on browser tab is unsucessfull");
		throw new WebAdaptorException(new Throwable("click on browser tab is unsucessfull",e.getCause()));
		
	}
	}

	public void searchForResult(String report) throws ObjectNameNotFoundException, WebAdaptorException {
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "SearchTextBox");
		Web.webAdaptor(testDriver, WebAction.setText, "SearchTextBox", report );
		//Web.webAdaptor(testDriver, WebAction.clickUsingJavascript,"searchIcon");		
		//Web.webAdaptor(testDriver, WebAction.setValueToXpathAndClick, "SearchResult",report);
		//Web.webAdaptor(testDriver, WebAction.sendKeyboardActions,"SearchTextBox", "RETURN");
		Web.webAdaptor(testDriver, WebAction.sendKeyboardActions,"SearchTextBox" ,"SPACE");
		Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
		Web.webAdaptor(testDriver, WebAction.sendKeyboardActions,"SearchTextBox" ,"ENTER");
		Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
	}
	
	public void selectRecentResult() throws ObjectNameNotFoundException, WebAdaptorException {
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable,"SearchResult");
		Web.webAdaptor(testDriver, WebAction.clickUsingJavascript,"SearchResult");
	}
	
	public void selectSearchedReport(String report) throws ObjectNameNotFoundException, WebAdaptorException {
		//Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable,"SearchResult");
		Web.webAdaptor(testDriver, WebAction.clickUsingJavascript,"SearchResult");
		//Web.webAdaptor(testDriver, WebAction.setValueToXpathAndClick,"SearchResultParm",report);
	}
	
	
	public void selectLatestReport() throws WebAdaptorException, ObjectNameNotFoundException{
		//Library ›  Organization ›  Enterprise Services ›  EIM ›  Real World ›  Shared ›  DEV ›  OBI-V1.0 ›  DXPs › DXPs Backup
		try{
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "Library");
		Web.webAdaptor(testDriver, WebAction.IsElementExist, "Library");
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "Organization");
		Web.webAdaptor(testDriver, WebAction.click, "Organization");
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "EnterpriseServices");
		Web.webAdaptor(testDriver, WebAction.click, "EnterpriseServices");
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "EIM");
		Web.webAdaptor(testDriver, WebAction.click, "EIM");
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "RealWorld");
		Web.webAdaptor(testDriver, WebAction.click, "RealWorld");
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "Production");
		Web.webAdaptor(testDriver, WebAction.click, "Production");
		/*Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "DEV");
		Web.webAdaptor(testDriver, WebAction.click, "DEV");*/
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "OBIV1");
		Web.webAdaptor(testDriver, WebAction.click, "OBIV1");
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "DXPs");
		Web.webAdaptor(testDriver, WebAction.click, "DXPs");
/*		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "DXPsBackup");
		Web.webAdaptor(testDriver, WebAction.click, "DXPsBackup");*/
		//latest at the top
		//Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "DXPsBackup");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "Report_OBI");
		Web.webAdaptor(testDriver, WebAction.click, "Report_OBI");
		DriverScript.logMessage(testDriver,"testStepPass","Selecting latest report is sucessfull");
		Web.webAdaptor(testDriver, WebAction.wait, "wait.medium");
	}
	catch(WebAdaptorException e){
		DriverScript.logMessage(testDriver,"testStepFail","Selecting latest report is Unsucessfull");
		throw new WebAdaptorException(new Throwable("Selecting latest report is Unsucessfull",e.getCause()));
		
	}
		
	}
	
	public void selectLatestReportFromUAT() throws WebAdaptorException, ObjectNameNotFoundException{
		//Library ›  Organization ›  Enterprise Services ›  EIM ›  Real World ›  Shared ›  DEV ›  OBI-V1.0 ›  DXPs › Report_OBI
		try{
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "Library");
		Web.webAdaptor(testDriver, WebAction.IsElementExist, "Library");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "Organization");
		Web.webAdaptor(testDriver, WebAction.click, "Organization");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "EnterpriseServices");
		Web.webAdaptor(testDriver, WebAction.click, "EnterpriseServices");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "EIM");
		Web.webAdaptor(testDriver, WebAction.click, "EIM");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "RealWorld");
		Web.webAdaptor(testDriver, WebAction.click, "RealWorld");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "Shared");
		Web.webAdaptor(testDriver, WebAction.click, "Shared");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "TST");
		Web.webAdaptor(testDriver, WebAction.click, "TST");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "UAT");
		Web.webAdaptor(testDriver, WebAction.click, "UAT");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "OBIV1");
		Web.webAdaptor(testDriver, WebAction.click, "OBIV1");		
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "DXPs");
		Web.webAdaptor(testDriver, WebAction.click, "DXPs");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "Report_OBI");
		Web.webAdaptor(testDriver, WebAction.click, "Report_OBI");
		DriverScript.logMessage(testDriver,"testStepPass","Selecting latest report is sucessfull");
	}
	catch(WebAdaptorException e){
		DriverScript.logMessage(testDriver,"testStepFail","Selecting latest report is Unsucessfull");
		throw new WebAdaptorException(new Throwable("Selecting latest report is Unsucessfull",e.getCause()));
		
	}
	}
	
	public void runSpotfireReport() throws WebAdaptorException, ObjectNameNotFoundException, InterruptedException{
		Web.webAdaptor(testDriver, WebAction.wait, "wait.high");
		Web.webAdaptor(testDriver, WebAction.wait, "wait.high");
		Web.webAdaptor(testDriver, WebAction.IsElementExist, "DateSelection");
		Web.webAdaptor(testDriver, WebAction.clickUsingJavascript, "DateSelection");
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "latestDate");
		Web.webAdaptor(testDriver, WebAction.click, "latestDate");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "EditButton");
		Web.webAdaptor(testDriver, WebAction.click, "EditButton");
		try {
			Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
			Web.webAdaptor(testDriver, WebAction.IsElementExist, "EditPopupOkButton");
			Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "EditPopupOkButton");
			Web.webAdaptor(testDriver, WebAction.clickUsingJavascript, "EditPopupOkButton");
		} catch (Exception e) {
		}
		Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
		//Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "SaveButton");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "SaveButton");
		Web.webAdaptor(testDriver, WebAction.clickUsingJavascript, "SaveButton");
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "SubmenuSaveButton");
		Web.webAdaptor(testDriver, WebAction.clickUsingJavascript, "SubmenuSaveButton");
		Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
		try {
			Web.webAdaptor(testDriver, WebAction.click, "SavePopUpOkButton");
		} catch (Exception e) {
		}
		Thread.sleep(100000);
		
	}
	
	
}
