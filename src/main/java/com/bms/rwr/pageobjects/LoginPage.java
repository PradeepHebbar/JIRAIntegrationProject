package com.bms.rwr.pageobjects;

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

public class LoginPage {

	TestDriver testDriver;

	public LoginPage() throws DriverScriptException {
		testDriver = CucumberDriver.testDriver;
		new ObjectRead(CucumberDriver.testDriver, this.getClass().getSimpleName());
	}

	public void signIntoSpotfire() throws ObjectNameNotFoundException, WebAdaptorException {
		String URL = testDriver.getGlobalParamMap().get("url");
		try {
			System.out.println("URL to login:  " + URL);
			Web.webAdaptor(testDriver, WebAction.navigateToURL, URL);
			DriverScript.logMessage(testDriver, "testStepDone", "Navigate to " + URL + " is sucessfull");
		} catch (WebAdaptorException e) {
			DriverScript.logMessage(testDriver, "testStepFail", "Navigate to " + URL + " is unsucessfull");
			throw new WebAdaptorException(new Throwable("Navigate to " + URL + " is unsucessfull", e.getCause()));

		}
	}

	public void enterUserName() throws ObjectNameNotFoundException, WebAdaptorException {
		String userName = testDriver.getGlobalParamMap().get("username");
		try {
			Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "UserNameTextField", userName);
			Web.webAdaptor(testDriver, WebAction.setText, "UserNameTextField", userName);
			DriverScript.logMessage(testDriver, "testStepDone", "Username " + userName + " Entered sucessfully");
		} catch (WebAdaptorException e) {
			DriverScript.logMessage(testDriver, "testStepFail", "Failed to enter Username" + userName);
			throw new WebAdaptorException(new Throwable("Failed to enter Username" + userName, e.getCause()));

		}
	}

	public void enterPassword() throws ObjectNameNotFoundException, WebAdaptorException {
		String password = testDriver.getGlobalParamMap().get("password");
		try {
			Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "PasswordTextField", password);
			Web.webAdaptor(testDriver, WebAction.setText, "PasswordTextField", password);
			DriverScript.logMessage(testDriver, "testStepDone", "password " + password + " Entered sucessfully");
		} catch (WebAdaptorException e) {
			DriverScript.logMessage(testDriver, "testStepFail", "Failed to enter password" + password);
			throw new WebAdaptorException(new Throwable("Failed to enter password" + password, e.getCause()));

		}
	}

	public void clickOnSignin() throws ObjectNameNotFoundException, WebAdaptorException {
		try {
			Web.webAdaptor(testDriver, WebAction.waitForObjectToLoad, "loginButton");
			Web.webAdaptor(testDriver, WebAction.click, "loginButton");
			DriverScript.logMessage(testDriver, "testStepDone", "click on login button is sucessfull");
		} catch (WebAdaptorException e) {
			DriverScript.logMessage(testDriver, "testStepFail", "click on login button is unsucessfull");
			throw new WebAdaptorException(new Throwable("click on login button is unsucessfull", e.getCause()));

		}
	}

}
