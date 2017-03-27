package BDD.Cucumber.Core;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;


public class BeforeAfterHooks {

	static Logger log;

	static {
		log = Logger.getLogger(BeforeAfterHooks.class);
	}


	@Before
	public void deleteAllCookies() {
		log.info("Deleting all cookies...");
		DriverManager.getDriver().manage().deleteAllCookies();
	}


	@After
	public static void embedScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			log.error("Scenario failed! Browser: " + DriverManager.getBrowserInfo() + " Taking screenshot...");
			scenario.write("Current Page URL is: " + DriverManager.getDriver().getCurrentUrl());
			scenario.write("Scenario Failed in: " + DriverManager.getBrowserInfo());
			try {
				byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				log.error(somePlatformsDontSupportScreenshots.getMessage());
			}
		}
		
		DriverManager.getDriver().quit();
	}
}