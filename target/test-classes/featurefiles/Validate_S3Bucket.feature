@Infra @AWS
Feature: This feature will help to validate the S3 buckets as a part of Infra Validation
			As this feature will be executed after Cloudformation stack create/update

	Background:
		Given the aws credentials/token used to connect is valid
 
		Scenario Outline: Verify S3 bucket info
			Given the list of S3 buckets on the environment is greater than zero
			When i try to search for "<S3_bucket_Name>"
			Then S3 bucket "<S3_bucket_Name>" should exist
			And bucket create/update date should be valid
			When i try to list the files in the bucket
			Then it should list few default files
			
			Examples:
			| S3_bucket_Name |
    		| bms-cards-test | 