package com.bms.rwr.utilities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.bms.rwr.jira.service.JiraAPIConsumer;

import cucumber.api.Scenario;

public class JIRAUtil {
	static String projectKey;
	static String cycleName;
	static String versionName;
	static String issueID;
	static JiraAPIConsumer jiraAPIConsumer = new JiraAPIConsumer();	
	static InputStream input = null;
	static Properties prop = new Properties();
	static String platform;
	static File snapshotFolder = new File("snapshotBackup");
	
	public static String InitJiraExecution(Collection<String> tagCollection) throws Exception{	
		if(CucumberDriver.testDriver!=null && CucumberDriver.testDriver.getGlobalParamMap().get("isJiraExecution").equals("true")){
			try {
				return createJiraExecution(tagCollection);				
			} catch (Exception e) {				
				throw e;
			}
		}else if(CucumberDriver.testDriver==null &&getjiraExecution().equals("true")){
			try {
				return createJiraExecution(tagCollection);				
			} catch (Exception e) {				
				throw e;
			}
		}
			return null;
	}

	public static String createJiraExecution(Collection<String> tagCollection) throws Exception {
		String executionId;	
		parseTags(tagCollection.iterator());
		executionId = jiraAPIConsumer.createNewExecution(projectKey, versionName, cycleName, issueID);
		if (executionId != null) {
			return executionId;
		} else {
			throw new Exception("Cannot create the execution with projectKey: " + projectKey + " cycleName: "
					+ cycleName + " versionName: " + versionName + " issueID: " + issueID);
		}
	}

	public static void updateJiraExecution(String executionId2, Scenario scenario, byte[] screenshotBytes) throws Exception{
		if (!snapshotFolder.exists()) {
			snapshotFolder.mkdir();
		}
		if(CucumberDriver.testDriver!=null && CucumberDriver.testDriver.getGlobalParamMap().get("isJiraExecution").equals("true")){
			if(scenario.isFailed()){
				File file = new File("snapshotBackup//"+scenario.getName()+".jpg");
				System.out.println(scenario.getName());
				InputStream inputStream = new  ByteArrayInputStream(screenshotBytes);
				FileUtils.copyInputStreamToFile(inputStream, file);
				JiraAPIConsumer.updateExecutionStatus(executionId2, "fail");
				jiraAPIConsumer.updateExecutionStepsResult(executionId2, "fail");
				jiraAPIConsumer.addAttachmentToExecution(executionId2,file); 
			}else{
				jiraAPIConsumer.updateExecutionStepsResult(executionId2,"pass");
				JiraAPIConsumer.updateExecutionStatus(executionId2, "pass");
			}
		}else if(CucumberDriver.testDriver==null &&getjiraExecution().equals("true")){
			if(scenario.isFailed()){
				JiraAPIConsumer.updateExecutionStatus(executionId2, "fail");
				jiraAPIConsumer.updateExecutionStepsResult(executionId2, "fail");
				}else{
				jiraAPIConsumer.updateExecutionStepsResult(executionId2,"pass");
				JiraAPIConsumer.updateExecutionStatus(executionId2, "pass");
			}
		}
	}
	
	private static void parseTags(Iterator<String> tagIterator) throws Exception {
		while (tagIterator.hasNext()) {
			String tagValue = tagIterator.next().replace("@", "").replaceAll("___", " ");
			if (tagValue.contains("|")) {
				String tagArray[] = tagValue.split("\\|");
				if (tagArray[0].equalsIgnoreCase("cyclename")) {
					cycleName = tagArray[1];
					System.out.println("cycleName is: " + tagArray[1]);
				} else if (tagArray[0].equalsIgnoreCase("projectkey")) {
					projectKey = tagArray[1];
					System.out.println("projectkey is: " + tagArray[1]);
				} else if (tagArray[0].equalsIgnoreCase("issueid")) {
					issueID = tagArray[1];
					System.out.println("issueid is: " + tagArray[1]);
				} else if (tagArray[0].equalsIgnoreCase("versionname")) {
					versionName = tagArray[1];
					System.out.println("versionid is: " + tagArray[1]);
				} else {
					System.out.println("invalid tags " + tagArray[1]);
					throw new Exception("Invalid tag " + tagValue);
				}
			}
		}
	}
	
	public static String getPlatform() throws IOException{
		input = new FileInputStream("config/GlobalParameter.properties");
		prop.load(input);			
		return prop.getProperty("platform");
	}
	
	public static String getjiraExecution() throws IOException{
		input = new FileInputStream("config/GlobalParameter.properties");
		prop.load(input);			
		return prop.getProperty("isJiraExecution");
	}
}
