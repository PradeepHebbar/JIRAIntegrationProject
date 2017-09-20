package com.bms.rwr.jira.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.bms.rwr.utilities.EncryptJiraCredentials;

public class JiraAPIBuilder {
	
	static String jiraUserName;
	static String jiraPassword;
	static Properties prop = new Properties();
	static InputStream input = null;
	static String scenarioPassCommand;
	static String scenarioFailCommand;
	static String stepPassCommand;
	static String stepFailCommand;
	
	private static String getJiraURI() throws Exception {
		
		try {
			input = new FileInputStream("config/GlobalParameter.properties");
			prop.load(input);			
			return prop.getProperty("jiraURI");
		} catch (Exception ex) {
			throw new Exception(
					new Throwable("Failed to read property file or globalURI value is absent in property file"));
		}
	}

	public static String getUsername() throws Exception{
		input = new FileInputStream("config/GlobalParameter.properties");
		prop.load(input);			
		jiraUserName =prop.getProperty("encryptedjiraUserName");
		return EncryptJiraCredentials.decrypt(jiraUserName);
	}
	
	public static String getScenarioPassCommand() throws Exception{
		input = new FileInputStream("config/GlobalParameter.properties");
		prop.load(input);			
		scenarioPassCommand =prop.getProperty("testCasePassComment");
		return scenarioPassCommand;
	}
	
	public static String getScenarioFailCommand() throws Exception{
		input = new FileInputStream("config/GlobalParameter.properties");
		prop.load(input);			
		scenarioFailCommand =prop.getProperty("testCaseFailCommenr");
		return scenarioFailCommand;
	}
	
	public static String getStepPassCommand() throws Exception{
		input = new FileInputStream("config/GlobalParameter.properties");
		prop.load(input);			
		stepPassCommand =prop.getProperty("stepPassComment");
		return stepPassCommand;
	}
	public static String getStepFailCommand() throws Exception{
		input = new FileInputStream("config/GlobalParameter.properties");
		prop.load(input);			
		stepFailCommand =prop.getProperty("stepFailComment");
		return stepFailCommand;
	}
	public static String getPassword() throws Exception{
		input = new FileInputStream("config/GlobalParameter.properties");
		prop.load(input);			
		jiraPassword =prop.getProperty("encryptedjiraPassword");
		return EncryptJiraCredentials.decrypt(jiraPassword);
	}
	
	
	public static String formatURIforJiraProjectDetails(String jiraProjectKey) throws Exception{
		return JiraAPIBuilder.getJiraURI() + "/rest/api/latest/project/" + jiraProjectKey;
	}
	
	public static String formatURIforJiraIssueDetails(String jiraIssueKey) throws Exception{
		return JiraAPIBuilder.getJiraURI() + "/rest/api/latest/issue/" + jiraIssueKey;
	}
	
	public static String formatURIforJiraCycleDetails(String jiraProjectId, String jiraVersionId) throws Exception{
		return JiraAPIBuilder.getJiraURI() + "/rest/zapi/latest/cycle?projectId=" + jiraProjectId + "&versionId=" + jiraVersionId;
	}
	
	public static String formatURIforCreatingJiraExecution() throws Exception{
		return JiraAPIBuilder.getJiraURI() + "/rest/zapi/latest/execution";
	}

	public static String formatURIforUpdatingJiraExecutionStatus(String jiraExecutionId) throws Exception{
		return JiraAPIBuilder.getJiraURI() + "/rest/zapi/latest/execution/" + jiraExecutionId + "/execute";
	}
	
	public static String formatURIforAttachingJiraExecution(String jiraExecutionId) throws Exception{
		return JiraAPIBuilder.getJiraURI() + "/rest/zapi/latest/attachment?entityId=" + jiraExecutionId + "&entityType=execution";
	}
	
	public static String formatURIforStatusEquivalentCode() throws Exception{
		return JiraAPIBuilder.getJiraURI() + "/rest/zapi/latest/execution";
	}
	
	public static String formatURIforStepResultByExecutionID(String jiraExecutionID) throws Exception{
		return JiraAPIBuilder.getJiraURI() + "/rest/zapi/latest/stepResult?executionId="+jiraExecutionID;
	}
	
	public static String formatURIforUpdatingStepByExecutionID(String jiraExecutionStepID) throws Exception{
		return JiraAPIBuilder.getJiraURI() + "/rest/zapi/latest/stepResult/"+jiraExecutionStepID;
	}
		
}
