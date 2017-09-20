package com.bms.rwr.jira.service;

public class APIArguments {
	
	public static enum DesiredAPIHeaders {
		HTTP_METHOD, CONTENT_TYPE , ATLASSIAN_TOKEN,BASIC_AUT;
	}
	
	public static enum DesiredAPIHeaderValues {
		HTTP_METHOD_GET("get"),
		HTTP_METHOD_POST("post"),
		HTTP_METHOD_PUT("put"),
		CONTENT_TYPE_APP_JSON("application/json"),
		R_ATLASSIAN_TOKEN("no-check"),
		BASIC_AUT_VAL("Yes");
		
		private final String message;
		
		
		DesiredAPIHeaderValues(String message){
			this.message = message;
		}
		
		
		public String getMessage(){
			return message;
		}
	}
	

}
