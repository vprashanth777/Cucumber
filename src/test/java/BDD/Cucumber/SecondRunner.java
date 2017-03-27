package BDD.Cucumber;





import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)

@CucumberOptions(
		format = { "pretty", "html:target/cucumber","json:target/JSON/OutputRerun.json" },
				features ={ "."},
       // features ={ "@target/rerun.txt"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/CucumberReport.html","rerun:target/rerun.txt"}
      //  plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/CucumberReport_ReRun.html","rerun:target/rerun1.txt"}
   

)


public class SecondRunner extends AbstractTestNGCucumberTests {
	
	
	
}


