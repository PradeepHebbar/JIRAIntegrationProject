package com.bms.rwr.stepdefs;

import java.util.List;

import org.testng.Assert;

import com.amazonaws.services.cloudformation.AmazonCloudFormationClient;
import com.amazonaws.services.cloudformation.model.DescribeStacksRequest;
import com.amazonaws.services.cloudformation.model.DescribeStacksResult;
import com.amazonaws.services.cloudformation.model.Parameter;
import com.amazonaws.services.cloudformation.model.Stack;
import com.amazonaws.services.cloudformation.model.Tag;
import com.automation.framework.core.DriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.pojs.TestDriver;
import com.bms.rwr.utilities.AWSCommon;
import com.bms.rwr.utilities.CucumberDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Validate_Stack {
	boolean stackExists = false;
	/*TestDriver testDriver;
	public Validate_Stack() throws DriverScriptException {
		testDriver = CucumberDriver.testDriver;
	}	*/


	private static final AmazonCloudFormationClient CLOUDFORMATION_CLIENT() {
		//AWSCredentials credentials = new ProfileCredentialsProvider("default").getCredentials();
		//AmazonCloudFormationClient cf = new AmazonCloudFormationClient(credentials, AWSCommon.returnClientConfigs());
		AmazonCloudFormationClient cf = new AmazonCloudFormationClient();
		return cf;
	}

	private DescribeStacksResult describeStacksResult() {
		DescribeStacksResult describeStacksResult = null;
		try {
			AmazonCloudFormationClient cloudFormation = CLOUDFORMATION_CLIENT();
			DescribeStacksRequest describeStacksRequest = new DescribeStacksRequest();
			describeStacksResult = cloudFormation.describeStacks(describeStacksRequest);
		} catch (Exception ex) {
			//DriverScript.logMessage(testDriver,"testStepFail","unable to describe stack results");
			System.out.println("unable to describe stack results");
			Assert.assertTrue(false, "unable to describe stack results "+ex);
		}
		return describeStacksResult;

	}

	@Given("^the list of Stacks on the environment is greater than zero$")
	public void the_list_of_Stacks_on_the_environment_is_greater_than_zero() throws Throwable {
		try {
			List<Stack> stacks = describeStacksResult().getStacks();
			if (!stacks.isEmpty()) {
				//DriverScript.logMessage(testDriver,"testStepPass","stacks are found in this AWS account");
				System.out.println("stacks are found in this AWS account");
				Assert.assertTrue(true, "stacks are found in this AWS account");
			} else {
				//DriverScript.logMessage(testDriver,"testStepFail","No stacks are found in this AWS account");
				System.out.println("No stacks are found in this AWS account");
				Assert.assertTrue(false, "No stacks are found in this AWS account");
			}
		} catch (Exception ex) {
			//DriverScript.logMessage(testDriver,"testStepFail","No stacks are found in this AWS account");
			System.out.println("No stacks are found in this AWS account");
			Assert.assertTrue(false, "No stacks are found in this AWS account " + ex);
		}
	}

	@When("^i try to search for the stack \"([^\"]*)\"$")
	public void i_try_to_search_for(String stackName) throws Throwable {
		for (Stack stack : describeStacksResult().getStacks()) {
			if (stack.getStackName().equalsIgnoreCase(stackName)) {
				stackExists = true;
			}
		}
	}

	@Then("^stack \"([^\"]*)\" should exist$")
	public void stack_should_exist(String stackName) throws Throwable {
		if (stackExists) {
			//DriverScript.logMessage(testDriver,"testStepPass","stack exists in the AWS account");
			System.out.println("stack exists in the AWS account");
			Assert.assertTrue(true, stackName + " stack exists in the AWS account");
		} else {
			//DriverScript.logMessage(testDriver,"testStepFail","stack does not exist in the AWS account");
			System.out.println("stack does not exist in the AWS account");
			Assert.assertTrue(false, stackName + " stack does not exist in the AWS account");
		}
	}

	@Then("^Parameters defined on stack should be \"([^\"]*)\"$")
	public void parameters_defined_on_stack_should_be_valid(String parameters) throws Throwable {
		String params[] = parameters.split("=");
		for (Stack stack : describeStacksResult().getStacks()) {
			if (stack.getStackName().equalsIgnoreCase("BMS-TestAutomation-dev")) {
				for (Parameter parameter : stack.getParameters()) {
					if(parameter.getParameterKey().equalsIgnoreCase(params[0])){
						if (parameter.getParameterValue().equalsIgnoreCase(params[1])) {
							//DriverScript.logMessage(testDriver,"testStepPass",parameter.getParameterKey() + " parameter is defined on stack");
							System.out.println(parameter.getParameterKey() + " parameter is defined on stack");
							Assert.assertTrue(true, parameter.getParameterKey() + " parameter is defined on stack");
						} else {
							//DriverScript.logMessage(testDriver,"testStepFail",parameter.getParameterKey() + " parameter is not defined on stack");
							System.out.println(parameter.getParameterKey() + " parameter is not defined on stack");
							Assert.assertTrue(false, parameter.getParameterKey() + " parameter is not defined on stack");
						}
					}
				}
			}
		}
	}

	@Then("^Tags defined on stack should be \"([^\"]*)\"$")
	public void tags_defined_on_stack_should_be_valid(String tags) throws Throwable {
		String params[] = tags.split("=");
		for (Stack stack : describeStacksResult().getStacks()) {
			if (stack.getStackName().equalsIgnoreCase("BMS-TestAutomation-dev")) {
				for (Tag tag : stack.getTags()) {
					if(tag.getKey().equalsIgnoreCase(params[0])){
						if (tag.getValue().equalsIgnoreCase(params[1])) {
							//DriverScript.logMessage(testDriver,"testStepPass",tag.getKey() + " tag is defined on stack");
							System.out.println(tag.getKey() + " tag is defined on stack");
							Assert.assertTrue(true, tag.getKey() + " tag is defined on stack");
						} else {
							System.out.println(tag.getKey() + " tag is not defined on stack");
							Assert.assertTrue(false, tag.getKey() + " tag is not defined on stack");
						}
					}
				}
			}
		}
	}

	@Then("^Status of the stack should be \"([^\"]*)\"$")
	public void status_of_the_stack_should_be_valid(String stackStatus) throws Throwable {
		for (Stack stack : describeStacksResult().getStacks()) {
			if (stack.getStackName().equalsIgnoreCase("BMS-TestAutomation-dev")) {
				if (stack.getStackStatus().toUpperCase().contains("COMPLETE")
						|| stack.getStackStatus().equalsIgnoreCase(stackStatus)) {
					//DriverScript.logMessage(testDriver,"testStepPass",stack.getStackStatus() + " status of the stack is valid");
					System.out.println(stack.getStackStatus() + " status of the stack is valid");
					Assert.assertTrue(true, stack.getStackStatus() + " status of the stack is valid");
				} else {
					//DriverScript.logMessage(testDriver,"testStepFail",stack.getStackStatus() + " status of the stack is not valid");
					System.out.println(stack.getStackStatus() + " status of the stack is not valid");
					Assert.assertTrue(false, stack.getStackStatus() + " status of the stack is not valid");
				}
			}
		}
	}
}
