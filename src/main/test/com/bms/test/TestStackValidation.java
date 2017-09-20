package com.bms.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClient;
import com.amazonaws.services.cloudformation.model.DescribeStacksRequest;
import com.amazonaws.services.cloudformation.model.DescribeStacksResult;
import com.amazonaws.services.cloudformation.model.Parameter;
import com.amazonaws.services.cloudformation.model.Stack;
import com.amazonaws.services.cloudformation.model.Tag;
import com.bms.rwr.stepdefs.Validate_Stack;
import com.bms.rwr.utilities.AWSCommon;

public class TestStackValidation {
	private static final Logger logger = LogManager.getLogger(Validate_Stack.class);
	boolean stackExists = false;

	private static final AmazonCloudFormationClient CLOUDFORMATION_CLIENT() {
		AWSCredentials credentials = new ProfileCredentialsProvider("default").getCredentials();
		AmazonCloudFormationClient cf = new AmazonCloudFormationClient(credentials, AWSCommon.returnClientConfigs());
		return cf;
	}

	private DescribeStacksResult describeStacksResult() {
		DescribeStacksResult describeStacksResult = null;
		try {
			AmazonCloudFormationClient cloudFormation = CLOUDFORMATION_CLIENT();
			DescribeStacksRequest describeStacksRequest = new DescribeStacksRequest();
			describeStacksResult = cloudFormation.describeStacks(describeStacksRequest);
		} catch (Exception ex) {
			logger.info("unable to describe stack results " + ex);
			Assert.assertTrue(false, "unable to describe stack results");
		}
		return describeStacksResult;

	}

    @Test
	public void the_list_of_Stacks_on_the_environment_is_greater_than_zero() throws Throwable {
		try {
			List<Stack> stacks = describeStacksResult().getStacks();
			if (!stacks.isEmpty()) {
				Assert.assertTrue(true, "stacks are found in this AWS account");
			} else {
				Assert.assertTrue(false, "No stacks are found in this AWS account");
			}
		} catch (Exception ex) {
			Assert.assertTrue(false, "No stacks are found in this AWS account " + ex);
		}
	}

    @Test
	public void i_try_to_search_for() throws Throwable {
    	String stackName="BMS-TestAutomation-dev";
		for (Stack stack : describeStacksResult().getStacks()) {
			if (stack.getStackName().equalsIgnoreCase(stackName)) {
				stackExists = true;
			}
		}
	}

    @Test
	public void stack_should_exist() throws Throwable {
    	String stackName="BMS-TestAutomation-dev";
		if (stackExists) {
			Assert.assertTrue(true, stackName + " stack exists in the AWS account");
		} else {
			Assert.assertTrue(false, stackName + " stack does not exist in the AWS account");
		}
	}

    @Test
	public void parameters_defined_on_stack_should_be_valid() throws Throwable {
    	String parameters="VitalizeId=v428096";
		String params[] = parameters.split("=");
		for (Stack stack : describeStacksResult().getStacks()) {
			if (stack.getStackName().equalsIgnoreCase("BMS-TestAutomation-dev")) {
				for (Parameter parameter : stack.getParameters()) {
					if(parameter.getParameterKey().equalsIgnoreCase(params[0])){
						if (parameter.getParameterValue().equalsIgnoreCase(params[1])) {
							Assert.assertTrue(true, parameter.getParameterKey() + " parameter is defined on stack");
						} else {
							Assert.assertTrue(false, parameter.getParameterKey() + " parameter is not defined on stack");
						}
					}
				}
			}
		}
	}

    @Test
	public void tags_defined_on_stack_should_be_valid() throws Throwable {
    	String tags="Project=v486121";
		String params[] = tags.split("=");
		for (Stack stack : describeStacksResult().getStacks()) {
			if (stack.getStackName().equalsIgnoreCase("BMS-TestAutomation-dev")) {
				for (Tag tag : stack.getTags()) {
					if(tag.getKey().equalsIgnoreCase(params[0])){
						if (tag.getValue().equalsIgnoreCase(params[1])) {
							Assert.assertTrue(true, tag.getKey() + " tag is defined on stack");
						} else {
							Assert.assertTrue(false, tag.getKey() + " tag is not defined on stack");
						}
					}
				}
			}
		}
	}

    @Test
	public void status_of_the_stack_should_be_valid() throws Throwable {
    	String stackStatus="CREATE_COMPLETE";
		for (Stack stack : describeStacksResult().getStacks()) {
			if (stack.getStackName().equalsIgnoreCase("BMS-TestAutomation-dev")) {
				if (stack.getStackStatus().toUpperCase().contains("COMPLETE")
						|| stack.getStackStatus().equalsIgnoreCase(stackStatus)) {
					Assert.assertTrue(true, stack.getStackStatus() + " status of the stack is valid");
				} else {
					Assert.assertTrue(false, stack.getStackStatus() + " status of the stack is not valid");
				}
			}
		}
	}

   
	public void create_update_date_on_stack_should_be(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		logger.info("^Status of the stack should be valid$");
	}

}
