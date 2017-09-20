package com.bms.rwr.stepdefs;

import com.bms.rwr.utilities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Validate_S3Bucket {

	private AmazonS3 getAmazonS3(){
		AmazonS3 s3=null;
		try{
			AmazonS3ClientBuilder s3Client = AmazonS3ClientBuilder.standard().withClientConfiguration(AWSCommon.returnClientConfigs());
			 s3 = s3Client.build();
		}catch(Exception ex){
			System.out.println("unable to create the Amazon S3 service "+ex);
			Assert.assertTrue(false, "unable to create the Amazon S3 service");
		}
		return s3;
		
	}
	@Given("^the aws credentials/IAM role used to connect is valid$")
	public void the_aws_credentials_token_used_to_connect_is_valid() throws Throwable {
		try{
		//AmazonEC2ClientBuilder ec2 = AmazonEC2ClientBuilder.standard().withClientConfiguration(AWSCommon.returnClientConfigs());
		Boolean s3Bucket = Region.getRegion(Regions.SA_EAST_1).isServiceSupported("ec2");
		if (s3Bucket) {
			System.out.println("the aws credentials/IAM role used to connect is valid");
		} else {
			System.out.println("the aws credentials/IAM role used to connect is not valid");
		}
		}catch(Exception ex){
			Assert.assertTrue(false, "unable to create the connection to AWS: "+ex);
		}
	}

	@Given("^the list of S(\\d+) buckets on the environment is greater than zero$")
	public void the_list_of_S_buckets_on_the_environment_is_greater_than_zero(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("^the list of S(\\d+) buckets on the environment is greater than zero$");
	}

	@When("^i try to search for \"([^\"]*)\"$")
	public void i_try_to_search_for(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("^i try to search for \"([^\"]*)\"$");
	}

	@Then("^S(\\d+) bucket \"([^\"]*)\" should exist$")
	public void s_bucket_should_exist(int arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("^S(\\d+) bucket \"([^\"]*)\" should exist$");
	}

	@Then("^bucket create/update date should be valid$")
	public void bucket_create_update_date_should_be_valid() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("^bucket create/update date should be valid$");
	}

	@When("^i try to list the files in the bucket$")
	public void i_try_to_list_the_files_in_the_bucket() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("^i try to list the files in the bucket$");
	}

	@Then("^it should list few default files$")
	public void it_should_list_few_default_files() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("^it should list few default files$");
	}
}
