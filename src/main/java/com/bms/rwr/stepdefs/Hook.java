package com.bms.rwr.stepdefs;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.automation.framework.core.BaseTest;
import com.automation.framework.pojs.TestDriver;
import com.bms.rwr.jira.service.JiraAPIConsumer;
import com.bms.rwr.utilities.CucumberDriver;
import com.bms.rwr.utilities.JIRAUtil;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {
	
	JiraAPIConsumer jiraAPIConsumer = new JiraAPIConsumer();
	static String executionId = null;
	
	@Before
	public void beforeScenario(Scenario scenario) throws Exception {		
		TestDriver testDriver = new TestDriver();
		testDriver.setCucumber(true);
		if(JIRAUtil.getPlatform().equalsIgnoreCase("Infra") && scenario.getSourceTagNames().contains("@Infra")){
		}
		else{
		new BaseTest(scenario.getName(), testDriver, null);
		CucumberDriver.testDriver = testDriver;
		CucumberDriver.driver = testDriver.getWebDriver();		
		}
		executionId = JIRAUtil.InitJiraExecution(scenario.getSourceTagNames());
	}

	@After
	public void tearDown(Scenario scenario) throws Exception {
		byte[] screenshotBytes = null;
		if(JIRAUtil.getPlatform().equalsIgnoreCase("Infra")&&scenario.getSourceTagNames().contains("@Infra")){
		}else{
		if (scenario.isFailed()) {
			screenshotBytes = ((TakesScreenshot) CucumberDriver.driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshotBytes, "image/png");			
		}
			CucumberDriver.driver.quit();
		}
		JIRAUtil.updateJiraExecution(executionId,scenario,screenshotBytes);
	}
	
}
