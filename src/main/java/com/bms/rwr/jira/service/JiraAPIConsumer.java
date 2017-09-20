package com.bms.rwr.jira.service;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bms.rwr.jira.model.ExecutionRequestVO;
import com.bms.rwr.jira.service.APIArguments.DesiredAPIHeaderValues;
import com.bms.rwr.jira.service.APIArguments.DesiredAPIHeaders;
import com.jayway.restassured.response.Response;

public class JiraAPIConsumer {

	public String getProjectIDByKey(String projectKey){
		try {
			HashMap<DesiredAPIHeaders,String> dataMap = new HashMap<>();
			dataMap.put(DesiredAPIHeaders.CONTENT_TYPE, String.valueOf(DesiredAPIHeaderValues.CONTENT_TYPE_APP_JSON.getMessage()));
			dataMap.put(DesiredAPIHeaders.BASIC_AUT, String.valueOf(DesiredAPIHeaderValues.BASIC_AUT_VAL.getMessage()));
			Response projectDetails = APIWrapper.getWrapper(dataMap).get(JiraAPIBuilder.formatURIforJiraProjectDetails(projectKey));
			return projectDetails.body().jsonPath().get("id").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String getIssueIDByKey(String issueKey){
		try {
			HashMap<DesiredAPIHeaders,String> dataMap = new HashMap<>();
			dataMap.put(DesiredAPIHeaders.CONTENT_TYPE, String.valueOf(DesiredAPIHeaderValues.CONTENT_TYPE_APP_JSON.getMessage()));
			dataMap.put(DesiredAPIHeaders.BASIC_AUT, String.valueOf(DesiredAPIHeaderValues.BASIC_AUT_VAL.getMessage()));
			Response issueDetails = APIWrapper.getWrapper(dataMap).get(JiraAPIBuilder.formatURIforJiraIssueDetails(issueKey));
			return issueDetails.body().jsonPath().get("id").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * accept project key and version name 
	 * @param projectKey
	 * @param versionName
	 * @return versionID
	 */
	public String getVersionIDByKey(String projectKey, String versionName){
		try {
			String versionID=null;
			HashMap<DesiredAPIHeaders,String> dataMap = new HashMap<>();
			dataMap.put(DesiredAPIHeaders.CONTENT_TYPE, String.valueOf(DesiredAPIHeaderValues.CONTENT_TYPE_APP_JSON.getMessage()));
			dataMap.put(DesiredAPIHeaders.BASIC_AUT, String.valueOf(DesiredAPIHeaderValues.BASIC_AUT_VAL.getMessage()));
			Response projectDetails = APIWrapper.getWrapper(dataMap).get(JiraAPIBuilder.formatURIforJiraProjectDetails(projectKey));
			JSONObject json = new JSONObject(projectDetails.asString());
		    JSONArray versionsDetails = json.getJSONArray("versions");
		        int versionsNumber = versionsDetails.length();
		        for (int i = 0; i < versionsNumber; ++i) {
		          JSONObject version = versionsDetails.getJSONObject(i);
		          if(version.getString("name").equals(versionName)){
		        	  versionID = version.get("id").toString();
		        	  break;
		          }
		        }
		        return versionID;
			} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String getCycleIDByKey(String projectKey, String versionName, String cycleName){
		try {
			
			String projectId = getProjectIDByKey(projectKey);
			String versionId = getVersionIDByKey(projectKey, versionName);			
			String cycleId = null;			
			HashMap<DesiredAPIHeaders,String> dataMap = new HashMap<>();
			dataMap.put(DesiredAPIHeaders.CONTENT_TYPE, String.valueOf(DesiredAPIHeaderValues.CONTENT_TYPE_APP_JSON.getMessage()));
			dataMap.put(DesiredAPIHeaders.BASIC_AUT, String.valueOf(DesiredAPIHeaderValues.BASIC_AUT_VAL.getMessage()));
			Response cycleDetailsResponse = APIWrapper.getWrapper(dataMap).get(JiraAPIBuilder.formatURIforJiraCycleDetails(projectId, versionId));
			System.out.println(cycleDetailsResponse.asString());
			JSONObject json = new JSONObject(cycleDetailsResponse.asString());
		    Iterator<String> cycleKeys = json.keys();
			while (cycleKeys.hasNext()) {
				String currentKey = cycleKeys.next();
				if (null != currentKey && !"recordsCount".equalsIgnoreCase(currentKey)) {
					JSONObject cycleKeyDetails = new JSONObject(json.get(currentKey).toString());
					String currentCycleName = cycleKeyDetails.get("name").toString();
					if (null != currentCycleName && currentCycleName.equalsIgnoreCase(cycleName)) {
						cycleId = currentKey;
						break;
					}
				}
			}
			return cycleId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * CUSTOM Method
	 * Consumer method to fetch project details by project key
	 * @param {projectKey, cycleName, versionName}
	 * @return 
	 * @return {projectId, cycleId, versionId}
	 */
	public ExecutionRequestVO getRequiredDataForNewExecution(String projectKey, String cycleName, String versionName, String issueName){
		
		ExecutionRequestVO executionRequestVO = new ExecutionRequestVO();
		executionRequestVO.setCycleId(getCycleIDByKey(projectKey, versionName, cycleName));
		executionRequestVO.setIssueId(getIssueIDByKey(issueName));
		executionRequestVO.setProjectId(getProjectIDByKey(projectKey));
		executionRequestVO.setVersionId(getVersionIDByKey(projectKey, versionName));
		
		return executionRequestVO;
	}
	
	/**
	 * CREATE EXECUTION IN JIRA
	 * @param Consume the above API to get all required details and call create execution API
	 * @return 
	 * @throws Exception 
	 */
	public String createNewExecution(String projectKey, String versionName, String cycleName, String issueName) throws Exception{
		ExecutionRequestVO executionRequestVO = getRequiredDataForNewExecution(projectKey, cycleName, versionName, issueName);
		String createExecutionRequestBody = new JSONObject(executionRequestVO).toString();
		System.out.println(createExecutionRequestBody);		
		HashMap<DesiredAPIHeaders,String> dataMap = new HashMap<>();
		dataMap.put(DesiredAPIHeaders.CONTENT_TYPE, String.valueOf(DesiredAPIHeaderValues.CONTENT_TYPE_APP_JSON.getMessage()));
		dataMap.put(DesiredAPIHeaders.BASIC_AUT, String.valueOf(DesiredAPIHeaderValues.BASIC_AUT_VAL.getMessage()));
		Response newExecutionDetails = APIWrapper.postWrapper(dataMap, createExecutionRequestBody).post(JiraAPIBuilder.formatURIforCreatingJiraExecution());
		JSONObject json = new JSONObject(newExecutionDetails.asString());
		return json.keys().next();
	}
	
	
	public static boolean updateExecutionStatus(String executionId, String status) throws Exception{
		String commandBody;
		if(status.equalsIgnoreCase("pass")){
			commandBody = JiraAPIBuilder.getScenarioPassCommand();
		}
		else{
			commandBody = JiraAPIBuilder.getScenarioFailCommand();
		}
		String statusCode = getStatusEquivalentCode(status);
		String newExecutionStatusBody = new JSONObject("{\"status\": " + statusCode + ", \"comment\":\" "+commandBody+"\"}").toString();
		System.out.println(newExecutionStatusBody);		
		HashMap<DesiredAPIHeaders,String> dataMap = new HashMap<>();
		dataMap.put(DesiredAPIHeaders.CONTENT_TYPE, String.valueOf(DesiredAPIHeaderValues.CONTENT_TYPE_APP_JSON.getMessage()));
		dataMap.put(DesiredAPIHeaders.BASIC_AUT, String.valueOf(DesiredAPIHeaderValues.BASIC_AUT_VAL.getMessage()));
		Response newExecutionDetails = APIWrapper.putWrapper(dataMap, newExecutionStatusBody).put(JiraAPIBuilder.formatURIforUpdatingJiraExecutionStatus(executionId));
		if(null != newExecutionDetails){
			return (newExecutionDetails.body().jsonPath().get("executionStatus") == statusCode);
		}
		return false;
	}
	
	public boolean addAttachmentToExecution(String executionId ,File file) throws Exception{
		HashMap<DesiredAPIHeaders,String> dataMap = new HashMap<>();
		dataMap.put(DesiredAPIHeaders.ATLASSIAN_TOKEN, String.valueOf(DesiredAPIHeaderValues.R_ATLASSIAN_TOKEN.getMessage()));
		dataMap.put(DesiredAPIHeaders.BASIC_AUT, String.valueOf(DesiredAPIHeaderValues.BASIC_AUT_VAL.getMessage()));
		Response newAttachmentDetails = APIWrapper.postWrapper(dataMap, null, "Yes", file).post(JiraAPIBuilder.formatURIforAttachingJiraExecution(executionId));
		JSONObject json = new JSONObject(newAttachmentDetails.asString());
		if(!json.keys().next().equals("error")){
			System.out.println("File attached sucessfully");
			return true;
		}
		System.out.println("Failed to attach File");
		return false;
		}
	

	public static String getStatusEquivalentCode(String statusName) throws Exception{	
		String statusID = null;
		HashMap<DesiredAPIHeaders,String> dataMap = new HashMap<>();
		dataMap.put(DesiredAPIHeaders.CONTENT_TYPE, String.valueOf(DesiredAPIHeaderValues.CONTENT_TYPE_APP_JSON.getMessage()));
		dataMap.put(DesiredAPIHeaders.BASIC_AUT, String.valueOf(DesiredAPIHeaderValues.BASIC_AUT_VAL.getMessage()));
		Response executionResponse = APIWrapper.getWrapper(dataMap).get(JiraAPIBuilder.formatURIforStatusEquivalentCode());
		System.out.println(executionResponse.getBody().jsonPath().get("status").toString());
		JSONObject json = new JSONObject(executionResponse.asString());
	    Iterator<String> executionResponseKeys = json.keys();
		while (executionResponseKeys.hasNext()) {
			String currentKey = executionResponseKeys.next();
			if (currentKey.equals("status")) {
				JSONObject cycleKeyDetails = new JSONObject(json.get(currentKey).toString());
				Iterator<String> statusKeys = cycleKeyDetails.keys();
				while (statusKeys.hasNext()) {
					String statusKey = statusKeys.next();
					JSONObject statusDetails = new JSONObject(cycleKeyDetails.get(statusKey).toString());
					String currentStatusDetails = statusDetails.get("name").toString();
					if(currentStatusDetails.equalsIgnoreCase(statusName)){
						statusID = statusKey;
						break;
					}
				}
				break;
			}
		}
		
		return statusID;
	
	}
	
	public void updateExecutionStepsResult(String executionID,String result){
		try{
		HashMap<DesiredAPIHeaders,String> dataMap = new HashMap<>();
		dataMap.put(DesiredAPIHeaders.CONTENT_TYPE, String.valueOf(DesiredAPIHeaderValues.CONTENT_TYPE_APP_JSON.getMessage()));
		dataMap.put(DesiredAPIHeaders.BASIC_AUT, String.valueOf(DesiredAPIHeaderValues.BASIC_AUT_VAL.getMessage()));
		Response stepDetails = APIWrapper.getWrapper(dataMap).get(JiraAPIBuilder.formatURIforStepResultByExecutionID(executionID));
		JSONArray stepDetailsArray=new JSONArray(stepDetails.asString());
		int stepDetail = stepDetailsArray.length();
        for (int i = 0; i < stepDetail; ++i) {
          JSONObject stepArray = stepDetailsArray.getJSONObject(i);
          updateStepResult(stepArray.get("id").toString(), result);
         }
		
		}catch(Exception e){
			e.getMessage();
		}
		
	}
	
	public void updateStepResult(String id, String result){
	try{
		String commandBody;
		if(result.equalsIgnoreCase("pass")){
			commandBody = JiraAPIBuilder.getStepPassCommand();
		}
		else{
			commandBody = JiraAPIBuilder.getStepFailCommand();
		}
		String statusCode = getStatusEquivalentCode(result);
		String executionStepStatusBody = new JSONObject("{\"status\": " + statusCode + ", \"comment\":\" "+commandBody+"\"}").toString();
		HashMap<DesiredAPIHeaders,String> dataMap = new HashMap<>();
		dataMap.put(DesiredAPIHeaders.CONTENT_TYPE, String.valueOf(DesiredAPIHeaderValues.CONTENT_TYPE_APP_JSON.getMessage()));
		dataMap.put(DesiredAPIHeaders.BASIC_AUT, String.valueOf(DesiredAPIHeaderValues.BASIC_AUT_VAL.getMessage()));
		APIWrapper.putWrapper(dataMap, executionStepStatusBody).put(JiraAPIBuilder.formatURIforUpdatingStepByExecutionID(id));
	}catch(Exception e){
		e.getMessage();
	}
		
	}
}