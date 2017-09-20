package com.bms.rwr.pageobjects;
import com.automation.framework.actionInterpreter.Web;
import com.automation.framework.actionInterpreter.Web.WebAction;
//import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;
import com.bms.rwr.utilities.CucumberDriver;

public class ProjectDetailsPage {	

	TestDriver testDriver;
	public ProjectDetailsPage() throws DriverScriptException {
		testDriver = CucumberDriver.testDriver;
		new ObjectRead(CucumberDriver.testDriver,this.getClass().getSimpleName());
	}		
	
	public void selectProjectDetailTab() throws ObjectNameNotFoundException, WebAdaptorException {
		Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
		Web.webAdaptor(testDriver, WebAction.click, "ProjectDetailTab");		
	}
	
	public void confirmProjectDetailTab() throws ObjectNameNotFoundException, WebAdaptorException {
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "ProjectDetailTabLink");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "ProjectDetailTabLink");	
	}
	
	public void clearFilter() throws ObjectNameNotFoundException, WebAdaptorException {
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "clearFilter");
		Web.webAdaptor(testDriver, WebAction.click, "clearFilter");	
	}

	public void verifyProjectDetailData() throws ObjectNameNotFoundException, WebAdaptorException {		
		Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "ProjectStatus");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "ProjectStatusValue");
		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "StartDate");		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "StartDateValue");
		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "DaysinProgress");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "DaysinProgressValue");
		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "TargetcompletionDate");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "TargetcompletionDateValue");
		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "DaystoTarget");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "DaystoTargetValue");
		
		
	}

	public void verifyProjDetailDaysProgress(String arg1) throws WebAdaptorException, ObjectNameNotFoundException {
		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "DaysinProgress");
		
		Web.webAdaptor(testDriver, WebAction.getText, "DaysinProgressValue");
        String str = testDriver.getMapValues().get("DaysinProgressValue");
        int ActualVal = Integer.parseInt(str);
        int ExpectedVal = Integer.parseInt(arg1);
        //System.out.println("projCompletedValue1 Str ----"+ str);
        if(ActualVal != ExpectedVal){
        	System.out.println("extracted value-- "+ str);
        	System.out.println("expected value--"+ arg1);
        	 throw new WebAdaptorException("Days in progress expected is "+ arg1 +" actual is "  +  str);
        }else{
        	System.out.println("extracted value "+ str);
        	System.out.println("expected value "+ arg1);
        }
	}
	

	

}
