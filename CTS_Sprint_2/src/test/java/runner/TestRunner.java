package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
    
@CucumberOptions(features="src/test/java/features/", 
glue = {"hook","stepDefinitions"}, 
plugin = {
	    "pretty",
	    "html:target/cucumber-reports/CucumberTestReport.html",
	    "json:target/cucumber-reports/CucumberTestReport.json",
	    "junit:target/cucumber-reports/CucumberTestReport.xml"
	},

tags = "(@Module1 or @Module2 or @Module3 or @Module4 or @Module5) and (not @ignore)")

public class TestRunner extends AbstractTestNGCucumberTests {
	
}
