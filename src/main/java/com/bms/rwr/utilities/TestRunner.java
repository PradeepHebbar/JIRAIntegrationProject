package com.bms.rwr.utilities;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	plugin = { "pretty", "json:report/cucumber.json", },
	monochrome = true, 
	strict = true, 
	features = "src/test/resources/featurefiles/Validate_S3Bucket.feature", 
	glue = "com.bms.rwr.stepdefs"
)
public class TestRunner {
}
