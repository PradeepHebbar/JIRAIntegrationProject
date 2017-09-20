package com.bms.rwr.jira.service;

import java.io.File;
import java.util.HashMap;

import com.bms.rwr.jira.service.APIArguments.DesiredAPIHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

public class APIWrapper {

	public static RequestSpecification getWrapper(HashMap<DesiredAPIHeaders, String> config) throws Exception {
		RequestSpecification withAuthenticate = addAuthenticationWrapper(config);
		RequestSpecification withContentType= addContentTypeWrapper(withAuthenticate,config);		
		return withContentType.when();
		
	}
	
	public static RequestSpecification postWrapper(HashMap<DesiredAPIHeaders, String> config, String requestBody) throws Exception {
		RequestSpecification withAuthenticate = addAuthenticationWrapper(config);
		RequestSpecification withContentType= addContentTypeWrapper(withAuthenticate,config);		
		RequestSpecification withBody = addBodyWrapper(withContentType, requestBody);
		return withBody.when();		
	}
	
	public static RequestSpecification postWrapper(HashMap<DesiredAPIHeaders, String> config, String requestBody,String multiPart,File file) throws Exception {
		RequestSpecification withMultiPart = addMultiPartWrapper(multiPart, file);
		RequestSpecification withAuthenticate = addAuthenticationWrapper(withMultiPart,config);
		RequestSpecification withContentType= addContentTypeWrapper(withAuthenticate,config);		
		RequestSpecification withBody = addBodyWrapper(withContentType, requestBody);
		return withBody.when();		
	}

	public static RequestSpecification putWrapper(HashMap<DesiredAPIHeaders, String> config, String requestBody) throws Exception {
		RequestSpecification withAuthenticate = addAuthenticationWrapper(config);
		RequestSpecification withContentType= addContentTypeWrapper(withAuthenticate,config);		
		RequestSpecification withBody = addBodyWrapper(withContentType, requestBody);
		return withBody.when();	
	}
	
	public static RequestSpecification addContentTypeWrapper(RequestSpecification requestSpecification,HashMap<DesiredAPIHeaders, String> config) throws Exception{
		if (config.get(DesiredAPIHeaders.CONTENT_TYPE) != null) {
			return requestSpecification.contentType(config.get(DesiredAPIHeaders.CONTENT_TYPE));
		}else{
			return addAtlassinTokenWrapper(requestSpecification,config);
		}		
	}
	
	public static RequestSpecification addAtlassinTokenWrapper(RequestSpecification requestSpecification,HashMap<DesiredAPIHeaders, String> config) throws Exception{
		if (config.get(DesiredAPIHeaders.ATLASSIAN_TOKEN) != null) {
			return requestSpecification.header("X-Atlassian-Token", "nocheck");
		}else{
			return requestSpecification;
		}		
	}
	
	public static RequestSpecification addAuthenticationWrapper(HashMap<DesiredAPIHeaders, String> config) throws Exception{
	 if(config.get(DesiredAPIHeaders.BASIC_AUT).equalsIgnoreCase("Yes")){
		return RestAssured.given().auth().preemptive().basic(JiraAPIBuilder.getUsername(),JiraAPIBuilder.getPassword());
			}else{
			return RestAssured.given();
			}
	}
	
	public static RequestSpecification addAuthenticationWrapper(RequestSpecification requestSpecification,HashMap<DesiredAPIHeaders, String> config) throws Exception{
		 if(config.get(DesiredAPIHeaders.BASIC_AUT).equalsIgnoreCase("Yes")){
			return requestSpecification.auth().preemptive().basic(JiraAPIBuilder.getUsername(),JiraAPIBuilder.getPassword());
				}else{
				return requestSpecification;
				}
		}
	
	public static RequestSpecification addBodyWrapper(RequestSpecification requestSpecification,String requestBody) throws Exception{
		 if(requestBody!=null){
			return requestSpecification.body(requestBody);
				}else{
				return requestSpecification;
				}
		}
	
	public static RequestSpecification addMultiPartWrapper(String multiPart,File file) throws Exception{
		if(multiPart!=null && file!=null){
		return RestAssured.given().multiPart(file);				
		}else{
			return RestAssured.given();
		}
	}
}
