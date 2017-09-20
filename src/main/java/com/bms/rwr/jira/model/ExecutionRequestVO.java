package com.bms.rwr.jira.model;

public class ExecutionRequestVO {

	String issueId;
	String projectId;
	String cycleId;
	String versionId;
	
	public String getIssueId() {
		return issueId;
	}
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCycleId() {
		return cycleId;
	}
	public void setCycleId(String cycleId) {
		this.cycleId = cycleId;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	
}
