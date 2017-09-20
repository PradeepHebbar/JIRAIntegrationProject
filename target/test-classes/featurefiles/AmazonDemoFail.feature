@projectKey|SAM
Feature: Verify Invalid login
    As a user login to amazon with invlid credentials 
    verify the invalid login message
    
     @issueID|SAM-25
	 @cycleName|Test___Cycle___1
	 @versionName|Version___1.0
    Scenario: verify the invalid login message2    	
    Given user navigates to Amazon Login page
		When enter Username and Password and click login1
		Then User should be able to see Invalid login message
		