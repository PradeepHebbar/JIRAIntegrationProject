package com.bms.test;

import java.io.File;

import com.bms.rwr.jira.service.JiraAPIConsumer;

public class JiraTest1 {

	public void getProjectTest(){
		System.out.println("ProjectId: " + new JiraAPIConsumer().getProjectIDByKey("SAM"));
	}
	
	public void getIssueTest(){
		System.out.println("IssueId: " + new JiraAPIConsumer().getIssueIDByKey("SAM-24"));
	}
	
	public void getVersionsTest(){
		System.out.println("VersionId: " + new JiraAPIConsumer().getVersionIDByKey("SAM", "Version 1.0"));
	}
	
	public void getCycleTest(){
		System.out.println("CycleId: " + new JiraAPIConsumer().getCycleIDByKey("SAM", "Version 1.0", "Test Cycle 1"));
	}
	
	public void postNewExecutionTest() throws Exception{
		System.out.println("ExecutionId: " + new JiraAPIConsumer().createNewExecution("SAM", "Version 1.0", "Test Cycle 1", "SAM-24"));
	}
	
	public void putExecutionStatusTest() throws Exception{
		String executionId = new JiraAPIConsumer().createNewExecution("SAM", "Version 1.0", "Test Cycle 1", "SAM-24");
		System.out.println("[Update] executionId: "+ executionId);
		System.out.println(new JiraAPIConsumer().updateExecutionStatus(executionId, "1"));
	}
	
	public void postAttachmentToExecutionTest() throws Exception{
		new JiraAPIConsumer().addAttachmentToExecution("27", new File("C:\\Users\\kunal.mazumdar\\Desktop\\ZAPI.PPTX"));
	}
	
	public void postStatusEquivalentCode() throws Exception{
		System.out.println(new JiraAPIConsumer().getStatusEquivalentCode("pass"));
	}
	
	public void postExecutionStepDetails() throws Exception{
		new JiraAPIConsumer().updateExecutionStepsResult("30","2");
	}
	
	public static void main(String[] args) throws Exception {
		JiraTest1 test = new JiraTest1();
		
		/*test.getProjectTest();
		test.getIssueTest();
		test.getVersionsTest();
		test.getCycleTest();		
		test.postNewExecutionTest();
		test.putExecutionStatusTest();		
		test.postAttachmentToExecutionTest();		
		test.postStatusEquivalentCode();*/
		
		
		//test.postExecutionStepDetails();
		
	}
}
