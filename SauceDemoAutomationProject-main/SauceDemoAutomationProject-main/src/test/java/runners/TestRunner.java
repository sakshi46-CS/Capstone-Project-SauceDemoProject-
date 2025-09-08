package runners;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features/Checkout.feature",
	    glue = {"stepDefinitions"}, 
	    plugin = {"pretty", "html:reports/cucumber-report.html"},
	    monochrome = true
	)
	public class TestRunner extends AbstractTestNGCucumberTests {

	    @BeforeClass
	    public static void setup() {
	        System.out.println("==== Test Execution Started ====");
	    }

	    @AfterClass
	    public static void teardown() {
	        System.out.println("==== Test Execution Finished ====");
	    }
	}

