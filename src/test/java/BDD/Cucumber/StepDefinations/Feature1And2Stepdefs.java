package BDD.Cucumber.StepDefinations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import BDD.Cucumber.Core.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Contains step definitions for two feature files: first.feature & second.feature
 * @author jk
 */
public class Feature1And2Stepdefs {

    static Logger log = Logger.getLogger(Feature1And2Stepdefs.class);
    WebDriver driver = DriverManager.getDriver();
    WebElement webElement;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Given("^I am on (.+)$")
    public void givenIAmOn(String URL) {
        log.info("Given I'm on "+URL+"<br/>");
        driver.get(URL);
    }

    @When("^I search for element (.+)$")
    public void whenISearchForElement(String element_id) {
    	driver.findElement(By.xpath("//h1[@title='Cucumber']"));
    }

    @Then("^I should see this element$")
    public void thenIShouldSeeThisElement() {
        log.info("Then I should see this element");
     
    }
}
