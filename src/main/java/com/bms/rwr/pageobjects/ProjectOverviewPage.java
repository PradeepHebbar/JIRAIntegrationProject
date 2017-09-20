package com.bms.rwr.pageobjects;
import java.io.FileNotFoundException;

import com.automation.framework.actionInterpreter.Web;
import com.automation.framework.actionInterpreter.Web.WebAction;
import com.automation.framework.core.DriverScript;
//import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.ObjectNameNotFoundException;
import com.automation.framework.exceptions.WebAdaptorException;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;
import com.bms.rwr.utilities.CucumberDriver;
import com.bms.testng.tests.XLS_Data;

public class ProjectOverviewPage {	

	TestDriver testDriver;
	int ActualVal;
	int ExpectedVal;
	String ActualStringVal;
	String ExpectedStringVal;
	
	public ProjectOverviewPage() throws DriverScriptException {
		testDriver = CucumberDriver.testDriver;
		new ObjectRead(CucumberDriver.testDriver,this.getClass().getSimpleName());
	}		
	
	public void selectProjectOverviewTab() throws ObjectNameNotFoundException, WebAdaptorException, InterruptedException {
		try{
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad,"ProjectOverViewLink");
		this.waitUntillElementPresent("Processing");
		Web.webAdaptor(testDriver, WebAction.click, "ProjectOverViewLink");	
		DriverScript.logMessage(testDriver,"testStepDone","click on Project OverView Link is sucessfull");
		}
		catch(WebAdaptorException e){
			DriverScript.logMessage(testDriver,"testStepFail","click on Project OverView Link is unsucessfull");
			throw new WebAdaptorException(new Throwable("click on Project OverView Link is unsucessfull",e.getCause()));
			
		}
	}
	
	public void confirmProjectOverviewTab() throws ObjectNameNotFoundException, WebAdaptorException {
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "ProjectOverViewLink");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "ProjectOverViewLink");	
	}
	
	public void clearFilter() throws ObjectNameNotFoundException, WebAdaptorException {
		try{
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "clearFilter");
		Web.webAdaptor(testDriver, WebAction.click, "clearFilter");	
		DriverScript.logMessage(testDriver,"testStepDone","click on clear filters is sucessfull");
	}
	catch(WebAdaptorException e){
		DriverScript.logMessage(testDriver,"testStepFail","click on clear filters  is unsucessfull");
		throw new WebAdaptorException(new Throwable("click on clear filters is unsucessfull",e.getCause()));
		
	}
	}

	public void verifyProjectOverviewData() throws ObjectNameNotFoundException, WebAdaptorException {	
		Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projActivityField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projActivityValue1");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projActivityValue2");
		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projCompletedField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projCompletedValue1");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projCompletedValue2");
		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projDeliverdOntimeField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projDeliverdLateField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projInProgressField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projOnTargetField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projComingDueField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projOverdueField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "metadataMandateMetadataCompleteField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "metadataHasProjStatusField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "metadataHasProjStDateField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "metadataHasProjTrgDateField");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "researchUtilTrendsField");
		
		
	}

	public void verifyProjectActivityData(String arg1, String arg2) throws WebAdaptorException, ObjectNameNotFoundException {
		
		//Verifying value 1 of "Total Projects with Activity"
		Web.webAdaptor(testDriver, WebAction.getText, "projCompletedVal1");
        String str = testDriver.getMapValues().get("projCompletedVal1");
        int ActualVal = Integer.parseInt(str);
        int ExpectedVal = Integer.parseInt(arg1);
        //System.out.println("project Completed Value1 from report= "+ str);
        if(ActualVal != ExpectedVal){
        	DriverScript.logMessage(testDriver, "testStepFail","extracted value from report ="+ str);
        	DriverScript.logMessage(testDriver, "testStepFail","expected value from test data ="+ arg1);
        	 throw new WebAdaptorException("Project completed value1 expected is "+ arg1 +" actual is "  +  str);
        }else{
        	
        	DriverScript.logMessage(testDriver, "testStepDone","extracted value from report ="+ str);
        	DriverScript.logMessage(testDriver, "testStepDone","expected value from test data ="+ arg1);
        	DriverScript.logMessage(testDriver, "testStepDone","Project completed value validated sucessfully");
        }
        
      //Verifying value 2 of "Total Projects with Activity"
		Web.webAdaptor(testDriver, WebAction.getText, "projCompletedVal2");
        str = testDriver.getMapValues().get("projCompletedVal2");
        ActualVal = Integer.parseInt(str);
        ExpectedVal = Integer.parseInt(arg2);

        if(ActualVal != ExpectedVal){
        	DriverScript.logMessage(testDriver, "testStepFail","extracted value from report ="+ str);
        	DriverScript.logMessage(testDriver, "testStepFail","expected value from test data ="+ arg2);
        	 throw new WebAdaptorException("Total Projects with activity completed value2 expected is "+ arg2 +" actual is "  +  str);
        }else{
        	DriverScript.logMessage(testDriver, "testStepDone","extracted value from report ="+ str);
        	DriverScript.logMessage(testDriver, "testStepDone","expected value from test data ="+ arg2);
        	DriverScript.logMessage(testDriver, "testStepPass","Total Projects with activity completed value validated sucessfully");
        }
	}
	

	public void selectFilterDates() throws ObjectNameNotFoundException, WebAdaptorException, InterruptedException {	
		this.waitUntillElementPresent("Processing");
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "fromDateCal");
		Web.webAdaptor(testDriver, WebAction.click, "fromDateCal");	
		this.waitUntillElementPresent("Processing");
		Web.webAdaptor(testDriver, WebAction.click, "selectFromDate");	
		this.waitUntillElementPresent("Processing");
		Web.webAdaptor(testDriver, WebAction.waitUntilElementClickable, "toDateCal");
		Web.webAdaptor(testDriver, WebAction.click, "toDateCal");	
		Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "selectToDate");
		Web.webAdaptor(testDriver, WebAction.click, "selectToDate");
		this.waitUntillElementPresent("Processing");
	}
	
	public void verifyActiveProjectsCompleted() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException {
		//verify the Total Projects with Activity- total and completed

		XLS_Data  readData = new XLS_Data();
		String toatalActiveProjectXL = readData.getExcelStringData(1, 1, "KPIs");
		
		//Verifying value 1 of "Total Projects with Activity"
		Web.webAdaptor(testDriver, WebAction.getText, "totalProjectActivityVal1");
        String toatalActiveProjectUI = testDriver.getMapValues().get("totalProjectActivityVal1");
        ActualVal = Integer.parseInt(toatalActiveProjectUI);
        ExpectedVal = Integer.parseInt(toatalActiveProjectXL);
        //System.out.println("project Completed Value1 from report= "+ str);
        if(ActualVal != ExpectedVal){
        	DriverScript.logMessage(testDriver, "testStepFail","Value from XLS report ="+ ExpectedVal);
        	DriverScript.logMessage(testDriver, "testStepFail","Value from spotfire report ="+ ActualVal);
        	 throw new WebAdaptorException("Total Projects with activity from XLS report= "+ ExpectedVal +", Value from spotfire report= "  +  ActualVal);
        }else{
        	DriverScript.logMessage(testDriver, "testStepDone","Total Projects with activity - PASS");
        	DriverScript.logMessage(testDriver, "testStepDone","Value from XLS report ="+ ExpectedVal);
        	DriverScript.logMessage(testDriver, "testStepPass","Value from spotfire report="+ ActualVal);
        }		
   
	}
	
	public void verifyActiveProjectCount() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException {
		//verify the Total Projects with Activity- total and completed

		XLS_Data  readData = new XLS_Data();
		String toatalActiveProjectXL = readData.getExcelStringData(1, 1, "KPIs");
		
		//Verifying value 1 of "Total Projects with Activity"
		Web.webAdaptor(testDriver, WebAction.getText, "totalProjectActivityVal1");
        String toatalActiveProjectUI = testDriver.getMapValues().get("totalProjectActivityVal1");
        ActualVal = Integer.parseInt(toatalActiveProjectUI);
        ExpectedVal = Integer.parseInt(toatalActiveProjectXL);
        //System.out.println("project Completed Value1 from report= "+ str);
        if(ActualVal != ExpectedVal){
        	DriverScript.logMessage(testDriver, "testStepFail","Value from XLS report ="+ ExpectedVal);
        	DriverScript.logMessage(testDriver, "testStepFail","Value from spotfire report ="+ ActualVal);
        	 throw new WebAdaptorException("Total Projects with activity from XLS report= "+ ExpectedVal +", Value from spotfire report= "  +  ActualVal);
        }else{
        	DriverScript.logMessage(testDriver, "testStepDone","Total Projects with activity - PASS");
        	DriverScript.logMessage(testDriver, "testStepDone","Value from XLS report ="+ ExpectedVal);
        	DriverScript.logMessage(testDriver, "testStepDone","Value from spotfire report="+ ActualVal);
        	DriverScript.logMessage(testDriver, "testStepPass", "Total Project value validated sucessfully");
        }
		
        //Verifying value 1 of "Total Projects completed"
        String projectsCompletedXL = readData.getExcelStringData(2, 1, "KPIs");		
		Web.webAdaptor(testDriver, WebAction.getText, "projectsCompletedVal1");
        String projectsCompletedUI = testDriver.getMapValues().get("projectsCompletedVal1");
        ActualVal = Integer.parseInt(projectsCompletedUI);
        ExpectedVal = Integer.parseInt(projectsCompletedXL);
        if(ActualVal != ExpectedVal){
        	 throw new WebAdaptorException("Projects completed Value from XLS report= "+ ExpectedVal +", Value from spotfire report= "  +  ActualVal);
        }else{
        	System.out.println("Projects completed - PASS");
        }
			
        //Verifying value 1 of "Projects Delivered On-Time"
        String projectsDeliveredOnTimeXL = readData.getExcelStringData(3, 1, "KPIs");		
		Web.webAdaptor(testDriver, WebAction.getText, "deliveredOnTimeVal1");
        String projectsDeliveredOnTimeUI = testDriver.getMapValues().get("deliveredOnTimeVal1");
        ActualVal = Integer.parseInt(projectsDeliveredOnTimeUI);
        ExpectedVal = Integer.parseInt(projectsDeliveredOnTimeXL);
        if(ActualVal != ExpectedVal){
        	 throw new WebAdaptorException("Projects Delivered On-Time Value from XLS report= "+ ExpectedVal +", Value from spotfire report= "  +  ActualVal);
        }else{
        	DriverScript.logMessage(testDriver, "testStepPass","Projects Delivered On-Time - PASS");
        }
        
        //Verifying value 1 of "Projects Delivered Late"
        String projectsDeliveredLateXL = readData.getExcelStringData(4, 1, "KPIs");		
		Web.webAdaptor(testDriver, WebAction.getText, "deliveredLateVal1");
        String projectsDeliveredLateUI = testDriver.getMapValues().get("deliveredLateVal1");
        ActualVal = Integer.parseInt(projectsDeliveredLateUI);
        ExpectedVal = Integer.parseInt(projectsDeliveredLateXL);
        if(ActualVal != ExpectedVal){
        	 throw new WebAdaptorException("Projects Delivered Late Value from XLS report= "+ ExpectedVal +", Value from spotfire report= "  +  ActualVal);
        }else{
        	DriverScript.logMessage(testDriver, "testStepPass","Projects Delivered Late - PASS");
        }
        
	}

	public void verifyInProgressProjectCount() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException {
		//verify the Total Projects with Activity- In progress

		XLS_Data  readData = new XLS_Data();
		String projectsInProgressValXL = readData.getExcelStringData(5, 1, "KPIs");		
		//Verifying value In Progress
		Web.webAdaptor(testDriver, WebAction.getText, "projectsInProgressVal");
        String projectsInProgressValUI = testDriver.getMapValues().get("projectsInProgressVal");
        ActualVal = Integer.parseInt(projectsInProgressValUI);
        ExpectedVal = Integer.parseInt(projectsInProgressValXL);
        if(ActualVal != ExpectedVal){
        	 throw new WebAdaptorException("Projects-In progress from XLS report= "+ ExpectedVal +", Value from spotfire report= "  +  ActualVal);
        }else{
        	DriverScript.logMessage(testDriver, "testStepPass","Projects-In progress - PASS");
        }
		
        //Verifying value  of "On Target"
        String projectsOnTargetXL = readData.getExcelStringData(6, 1, "KPIs");		
		Web.webAdaptor(testDriver, WebAction.getText, "projectsOnTargetVal");
        String projectsOnTargetUI = testDriver.getMapValues().get("projectsOnTargetVal");
        ActualVal = Integer.parseInt(projectsOnTargetUI);
        ExpectedVal = Integer.parseInt(projectsOnTargetXL);
        if(ActualVal != ExpectedVal){
        	 throw new WebAdaptorException("On Target Val Value from XLS report= "+ ExpectedVal +", Value from spotfire report= "  +  ActualVal);
        }else{
        	DriverScript.logMessage(testDriver, "testStepPass","On Target - PASS");
        }
			
        //Verifying value 1 of "Coming Due"
        String projectsComingDueXL = readData.getExcelStringData(7, 1, "KPIs");		
		Web.webAdaptor(testDriver, WebAction.getText, "projectsComingDueVal");
        String projectsComingDueUI = testDriver.getMapValues().get("projectsComingDueVal");
        ActualVal = Integer.parseInt(projectsComingDueUI);
        ExpectedVal = Integer.parseInt(projectsComingDueXL);
        if(ActualVal != ExpectedVal){
        	 throw new WebAdaptorException("Projects coming due Value from XLS report= "+ ExpectedVal +", Value from spotfire report= "  +  ActualVal);
        }else{
        	DriverScript.logMessage(testDriver, "testStepPass","Projects coming due - PASS");
        }
        
        //Verifying value 1 of "Projects Overdue"
        String projectsOverdueXL = readData.getExcelStringData(8, 1, "KPIs");		
		Web.webAdaptor(testDriver, WebAction.getText, "projectsOverdueVal");
        String projectsOverdueUI = testDriver.getMapValues().get("projectsOverdueVal");
        ActualVal = Integer.parseInt(projectsOverdueUI);
        ExpectedVal = Integer.parseInt(projectsOverdueXL);
        if(ActualVal != ExpectedVal){
        	 throw new WebAdaptorException("Projects Overdue Value from XLS report= "+ ExpectedVal +", Value from spotfire report= "  +  ActualVal);
        }else{
        	DriverScript.logMessage(testDriver, "testStepPass","Projects Delivered Late - PASS");
        }
        
	}

	public void verifyMandatoryMetadataCompleted() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException {
		//verify the Mandatory Metadata Completed 
		XLS_Data  readData = new XLS_Data();
		String valueFromXL = readData.getExcelStringData(11, 3, "KPIs");
		Web.webAdaptor(testDriver, WebAction.getText, "mandatoryMetadataCompletedVal");
        String valueFromUI = testDriver.getMapValues().get("mandatoryMetadataCompletedVal");
        ActualStringVal = valueFromUI;
        ExpectedStringVal = valueFromXL;
    	
        if(ActualStringVal.equals(ExpectedStringVal)){
        	DriverScript.logMessage(testDriver, "testStepDone","Value from XLS report ="+ ExpectedStringVal);
        	DriverScript.logMessage(testDriver, "testStepDone","Value from spotfire report ="+ ActualStringVal);          	 
        	DriverScript.logMessage(testDriver, "testStepPass","Total Mandatory Metadata Completed - PASS");
        }else{
        	DriverScript.logMessage(testDriver, "testStepFail","Value from XLS report ="+ ExpectedStringVal);
        	DriverScript.logMessage(testDriver, "testStepFail","Value from spotfire report ="+ ActualStringVal);
       	 throw new WebAdaptorException("Total Mandatory Metadata Completed from XLS report= "+ ExpectedStringVal +", Value from spotfire report= "  +  ActualStringVal);

        }		
   
	}
	
	public void verifyHasProjectStatus() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException {
		//verify the % Has Project Status 
		XLS_Data  readData = new XLS_Data();
		String valueFromXL = readData.getExcelStringData(13, 3, "KPIs");
		Web.webAdaptor(testDriver, WebAction.getText, "hasProjectStatusVal");
        String valueFromUI = testDriver.getMapValues().get("hasProjectStatusVal");
        ActualStringVal = valueFromUI;
        ExpectedStringVal = valueFromXL;
    	
        if(ActualStringVal.equals(ExpectedStringVal)){
        	DriverScript.logMessage(testDriver, "testStepDone","Value from XLS report ="+ ExpectedStringVal);
        	DriverScript.logMessage(testDriver, "testStepDone","Value from spotfire report ="+ ActualStringVal);          	 
        	DriverScript.logMessage(testDriver, "testStepPass","% Has Project Status - PASS");
        }else{
        	DriverScript.logMessage(testDriver, "testStepFail","Value from XLS report ="+ ExpectedStringVal);
        	DriverScript.logMessage(testDriver, "testStepFail","Value from spotfire report ="+ ActualStringVal);
       	 throw new WebAdaptorException("% Has Project Status from XLS report= "+ ExpectedStringVal +", Value from spotfire report= "  +  ActualStringVal);

        }		
   
	}
	
	public void verifyHasProjectStartDate() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException {
		//verify the % Has Project Start Date
		XLS_Data  readData = new XLS_Data();
		String valueFromXL = readData.getExcelStringData(15, 3, "KPIs");
		Web.webAdaptor(testDriver, WebAction.getText, "hasProjectStartDateVal");
        String valueFromUI = testDriver.getMapValues().get("hasProjectStartDateVal");
        ActualStringVal = valueFromUI.replaceAll(" ", "");
        ExpectedStringVal = valueFromXL;
    	
        if(ActualStringVal.equals(ExpectedStringVal)){
        	DriverScript.logMessage(testDriver, "testStepDone","Value from XLS report ="+ ExpectedStringVal);
        	DriverScript.logMessage(testDriver, "testStepDone","Value from spotfire report ="+ ActualStringVal);          	 
        	DriverScript.logMessage(testDriver, "testStepPass","Has Project Start Date - PASS");
        }else{
        	DriverScript.logMessage(testDriver, "testStepFail","Value from XLS report ="+ ExpectedStringVal);
        	DriverScript.logMessage(testDriver, "testStepFail","Value from spotfire report ="+ ActualStringVal);
       	 throw new WebAdaptorException("Has Project Start Date from XLS report= "+ ExpectedStringVal +", Value from spotfire report= "  +  ActualStringVal);

        }		
   
	}
	
	public void verifyHasProjectTargetDate() throws ObjectNameNotFoundException, WebAdaptorException, FileNotFoundException, DriverScriptException {
		//verify the % Has Project Target Date
		XLS_Data  readData = new XLS_Data();
		String valueFromXL = readData.getExcelStringData(17, 3, "KPIs");
		Web.webAdaptor(testDriver, WebAction.getText, "hasProjectTargetDateVal");
        String valueFromUI = testDriver.getMapValues().get("hasProjectTargetDateVal");
        ActualStringVal = valueFromUI;
        ExpectedStringVal = valueFromXL;
    	
        if(ActualStringVal.equals(ExpectedStringVal)){
        	DriverScript.logMessage(testDriver, "testStepDone","Value from XLS report ="+ ExpectedStringVal);
        	DriverScript.logMessage(testDriver, "testStepDone","Value from spotfire report ="+ ActualStringVal);          	 
        	DriverScript.logMessage(testDriver, "testStepPass","Has Project target Date - PASS");
        }else{
        	DriverScript.logMessage(testDriver, "testStepFail","Value from XLS report ="+ ExpectedStringVal);
        	DriverScript.logMessage(testDriver, "testStepFail","Value from spotfire report ="+ ActualStringVal);
       	 throw new WebAdaptorException("Has Project Target Date from XLS report= "+ ExpectedStringVal +", Value from spotfire report= "  +  ActualStringVal);

        }		
   
	}
	
	public void verifyLeftNavigation() throws ObjectNameNotFoundException, WebAdaptorException {	
		Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projectsActiveWithin");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "requestorDept");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "cordsGroup");		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "cordsRole");
		Web.webAdaptor(testDriver, WebAction.scrollToBottom, "verticalScrollLeft");
		Web.webAdaptor(testDriver, WebAction.IsElementExist, "market");
		Web.webAdaptor(testDriver, WebAction.IsElementExist, "therapeuticArea");		
		Web.webAdaptor(testDriver, WebAction.IsElementExist, "drugAsset");	
		
		
	}
	public void verifyRightNavigation() throws ObjectNameNotFoundException, WebAdaptorException {	
		Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "researcherUtilTrend");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "projectsPerResearcher");
		//Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "forDateHeading");		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "comparedTo");
	
	}
	
	public void waitUntillElementPresent(String LocName) throws InterruptedException, WebAdaptorException, ObjectNameNotFoundException{
		int count = 0;
		Thread.sleep(8000);
		while(count<200){
			try{
			Web.webAdaptor(testDriver, WebAction.isElementNotExists, LocName);
			break;
		}catch(Exception e){
			Thread.sleep(2000);
			count++;
		}
		}
	}
	
	
}
