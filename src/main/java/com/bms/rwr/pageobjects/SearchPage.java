package com.bms.rwr.pageobjects;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.utilities.ObjectRead;
import com.bms.rwr.utilities.CucumberDriver;

public class SearchPage {

	public SearchPage() throws DriverScriptException {
		new ObjectRead(CucumberDriver.testDriver, this.getClass().getSimpleName());
	}
/*
	public void openTutorial(String row) throws NumberFormatException, ObjectNameNotFoundException {
		CucumberDriver
				.waitForElementToLoad(ByAngular.repeater(ObjectRead.getObject(CucumberDriver.testDriver, "CourseList"))
						.row(Integer.parseInt(row)).className("card--left-col"));
		CucumberDriver.driver
				.findElement(ByAngular.repeater(ObjectRead.getObject(CucumberDriver.testDriver, "CourseList"))
						.row(Integer.parseInt(row)).className("card--left-col"))
				.click();
		
		CucumberDriver.driver
		.findElement(ByAngular.cssContainingText(".ng-binding", "Carl")) ;
		repeater(ObjectRead.getObject(CucumberDriver.testDriver, "CourseList")).
				.row(Integer.parseInt(row)).className("card--left-col"))
		.click();
		
		
	}

	public void VerifySearch(String searchString, String row)
			throws CucumberDriverException {
		try{
		CucumberDriver
				.waitForElementToLoad(ByAngular.repeater(ObjectRead.getObject(CucumberDriver.testDriver, "CourseList"))
						.row(Integer.parseInt(row)).className("card__title"));
		org.hamcrest.MatcherAssert.assertThat(
				CucumberDriver.driver
						.findElement(ByAngular.repeater(ObjectRead.getObject(CucumberDriver.testDriver, "CourseList"))
								.row(Integer.parseInt(row)).className("card__title"))
						.getText(),
				org.hamcrest.CoreMatchers.containsString(searchString));
		}
	catch(Exception cex){
		DriverScript.logMessage(CucumberDriver.testDriver, "testStepFail", cex.toString());
		throw new CucumberDriverException(new Throwable(cex.getCause().toString()));
		
		
	}
		
		}*/

}
