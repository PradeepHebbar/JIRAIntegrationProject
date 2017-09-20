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


public class AmazonLoginPage {
	
	TestDriver testDriver;
	public AmazonLoginPage() throws DriverScriptException {
		testDriver = CucumberDriver.testDriver;
		new ObjectRead(CucumberDriver.testDriver,this.getClass().getSimpleName());
	}	


	public void navigateAmazonLoginPage() throws ObjectNameNotFoundException, WebAdaptorException, InterruptedException {
		try {
			Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "SignInMenu");
			Thread.sleep(5000);
			Web.webAdaptor(testDriver, WebAction.mouseHover, "SignInMenu");
			DriverScript.logMessage(testDriver, "testStepDone", "MouseHover on Sign in menu is sucessfull");
			Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "SignInBtn");
			Web.webAdaptor(testDriver, WebAction.clickUsingJavascript, "SignInBtn");
			DriverScript.logMessage(testDriver, "testStepDone", "Click on Sign in Button is sucessfull");
		} catch (WebAdaptorException e) {
			DriverScript.logMessage(testDriver, "testStepFail", "Navigate to Amazon Login Page is unsucessfull");
			throw new WebAdaptorException(new Throwable("Navigate to Amazon Login Page is unsucessfull", e.getCause()));

		}
	}

	public void enterUserName() throws ObjectNameNotFoundException, WebAdaptorException {
		String userName = testDriver.getGlobalParamMap().get("username");
		try {
			Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "UserNameTxtBox", userName);
			Web.webAdaptor(testDriver, WebAction.setText, "UserNameTxtBox", userName);
			DriverScript.logMessage(testDriver, "testStepDone", "Username " + userName + " Entered sucessfully");
		} catch (WebAdaptorException e) {
			DriverScript.logMessage(testDriver, "testStepFail", "Failed to enter Username" + userName);
			throw new WebAdaptorException(new Throwable("Failed to enter Username" + userName, e.getCause()));

		}
	}
	
	public void enterPassword() throws ObjectNameNotFoundException, WebAdaptorException {
		String password = testDriver.getGlobalParamMap().get("password");
		try {
			Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "PasswordTxtBox", password);
			Web.webAdaptor(testDriver, WebAction.setText, "PasswordTxtBox", password);
			DriverScript.logMessage(testDriver, "testStepDone", "password " + password + " Entered sucessfully");
		} catch (WebAdaptorException e) {
			DriverScript.logMessage(testDriver, "testStepFail", "Failed to enter password" + password);
			throw new WebAdaptorException(new Throwable("Failed to enter password" + password, e.getCause()));

		}
	}

	public void clickOnLogin() throws ObjectNameNotFoundException, WebAdaptorException {
		try {
			Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "LoginBtn");
			Web.webAdaptor(testDriver, WebAction.click, "LoginBtn");
			DriverScript.logMessage(testDriver, "testStepPass", "click on login button is sucessfull");
		} catch (WebAdaptorException e) {
			DriverScript.logMessage(testDriver, "testStepFail", "click on login button is unsucessfull");
			throw new WebAdaptorException(new Throwable("click on login button is unsucessfull", e.getCause()));

		}
	}
	
	public void verifyErrorMsg() throws ObjectNameNotFoundException, WebAdaptorException, InterruptedException {
		try {
			Thread.sleep(3000);
			Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "AlertMSg");
			DriverScript.logMessage(testDriver, "testStepPass", "InValid login alert message validate sucessfully");
		} catch (WebAdaptorException e) {
			DriverScript.logMessage(testDriver, "testStepFail", "Failed to validdate InValid login alert message");
			throw new WebAdaptorException(new Throwable("Failed to validate InValid login alert message", e.getCause()));

		}
	}

	public void enterUserName1() throws ObjectNameNotFoundException, WebAdaptorException {
		String userName = testDriver.getGlobalParamMap().get("username");
		try {
			Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "UserNameTxtBox1", userName);
			Web.webAdaptor(testDriver, WebAction.setText, "UserNameTxtBox1", userName);
			DriverScript.logMessage(testDriver, "testStepDone", "Username " + userName + " Entered sucessfully");
		} catch (WebAdaptorException e) {
			DriverScript.logMessage(testDriver, "testStepFail", "Failed to enter Username" + userName);
			throw new WebAdaptorException(new Throwable("Failed to enter Username" + userName, e.getCause()));

		}
	}
	
}
