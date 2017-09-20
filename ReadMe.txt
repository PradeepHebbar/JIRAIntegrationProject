Command to execute selected feature file using Maven command.
		
mvn compile exec:java -Dexec.mainClass="com.bms.rwr.utilities.CucumberRunner" -Dexec.args="Validate_Stack.feature" clean test
