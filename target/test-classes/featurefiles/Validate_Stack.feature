@Infra @Stackvalidation
Feature: This feature will help to validate the cloudformation stack configurations as a part of Infra Validation
			As this feature will be executed after Cloudformation stack create/update

Background:
Given the aws credentials/IAM role used to connect is valid
 
Scenario Outline: Verify Cloudformation stack configurations
Given the list of Stacks on the environment is greater than zero
When i try to search for the stack "<Stack_Name>"
Then stack "<Stack_Name>" should exist
And Parameters defined on stack should be "<Stack_params>"
And Tags defined on stack should be "<Stack_tags>"
And Status of the stack should be "<Stack_Status>"
					
Examples:
|Stack_Name        	   |Stack_Status   |Stack_params      |Stack_tags     |
|BMS-TestAutomation-dev|CREATE_COMPLETE|VitalizeId=v428096|Project=v486121|