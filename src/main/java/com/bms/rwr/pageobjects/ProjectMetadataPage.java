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

public class ProjectMetadataPage {	

	TestDriver testDriver;
	public ProjectMetadataPage() throws DriverScriptException {
		testDriver = CucumberDriver.testDriver;
		new ObjectRead(CucumberDriver.testDriver,this.getClass().getSimpleName());
	}		
	
	public void selectProjectMetadataTab() throws ObjectNameNotFoundException, WebAdaptorException {
		Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
		Web.webAdaptor(testDriver, WebAction.click, "ProjectMetadataTab");		
	}
	

	public void verifyProjectMetadata() throws ObjectNameNotFoundException, WebAdaptorException {	
		Web.webAdaptor(testDriver, WebAction.wait, "wait.low");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "MetadataPage");
		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "ProjectID");
		
		Web.webAdaptor(testDriver, WebAction.getText, "ProjectID");
        String str = testDriver.getMapValues().get("ProjectID");
        System.out.println("ProjectID Str ----"+ str);
        if(str.equals(1111)){
        	//4201089
        	 throw new WebAdaptorException("ProjectID is expected is 1111 but showing " + str );
        }else{
        	System.out.println("Both are equal---");
        }
       
        
        
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "ProjectName");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "PropertyNameColumn");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "MandatoryColumn");
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "CompleteColumn");	
	
		
	}

	public void verifyProjDetailDaysProgress(String ExpectedVal) throws WebAdaptorException, ObjectNameNotFoundException {
		
		Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "MetadataPage");
		
		//Web.webAdaptor(testDriver, WebAction.isElementDisplayed, "ProjectName");
		
		Web.webAdaptor(testDriver, WebAction.getText, "ProjectName");
        String ActualVal = testDriver.getMapValues().get("ProjectName");
       
        if(ActualVal.equals(ExpectedVal)){
        	System.out.println("extracted value-- "+ ExpectedVal);
        	System.out.println("expected value--"+ ActualVal);
        	 
        }else{
        	System.out.println("extracted value "+ ExpectedVal);
        	System.out.println("expected value "+ ActualVal);
        	throw new WebAdaptorException("Project ID expected is "+ ExpectedVal +" actual is "  +  ActualVal);
        }
        
		
	}
	

	

}
