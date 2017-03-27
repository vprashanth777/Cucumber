package BDD.Cucumber.Core;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Based on the LocalDriverFactory found at: onrationaleemotions.wordpress.com
 * 
 * @author: Confusions Personified
 * @src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-
 *       executions-using-testng/
 */
public class LocalDriverFactory {

	public static WebDriver createInstance() {
		WebDriver driver;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// browserName = (browserName != null) ? browserName : "firefox";
		String browserName = System.getProperty("BROWSER", "Firefox");
		switch (Browser.valueOf(browserName.toUpperCase())) {
		case FIREFOX:
			driver = new FirefoxDriver();
			break;

		case IE:
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", "src/test/resources/Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
			break;

		case CHROME:
			System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
		case HTMLUNIT:
			LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
			java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
			java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
			driver = new HtmlUnitDriver();
			break;
			
		case PHANTOMJS:
			 System.setProperty("phantomjs.binary.path", "src/test/resources/Drivers/phantomjs.exe");
			 driver = new PhantomJSDriver();	
			break;
		default:
			driver = new FirefoxDriver();
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		return driver;
	}

	private static enum Browser {
		FIREFOX, CHROME, IE, HTMLUNIT, PHANTOMJS;
	}
}