package BDD.Reusables;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.genium_framework.appium.support.server.AppiumServer;
import com.github.genium_framework.server.ServerArguments;
import com.google.common.base.Predicate;

import BDD.Cucumber.Core.DriverManager;

public class ReUsablesKeywords {

	Robot robot;
	Logger log;
	WebDriver driver;
	WebDriverWait WD;
	AppiumServer appiumServer=null;
	public ReUsablesKeywords() {
		this.driver = DriverManager.getDriver();
		WD = new WebDriverWait(driver, 60);
		log = Logger.getLogger(ReUsablesKeywords.class);

	}

	public void SelectFromList(WebElement WE, String Value) {
		Select S = new Select(WE);

		try {
			S.selectByValue(Value);
		} catch (Exception e) {
			log.warn("SelectFromList selectByValue exception ::" + e.toString());
		}

		try {
			S.selectByVisibleText(Value);
		} catch (Exception e) {
			log.warn("SelectFromList selectByVisibleText exception ::" + e.toString());
		}

		try {
			S.selectByIndex(Integer.parseInt(Value));
		} catch (Exception e) {
			log.warn("SelectFromList selectByIndex exception ::" + e.toString());
		}

		WE.sendKeys(Keys.TAB);
		WaitforPageLoad();
	}

	public void WaitforPageLoad() {

		Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {

	
			public boolean apply(WebDriver input) {
				return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
			}

		};
		try {
			WD.until(pageLoaded);
		} catch (Exception e) {

			log.error("WaitforPageLoad exception occured " + e.toString());
		}
	}

	public void UploadfilefromLocal(String Path) {

		try {
			robot = new Robot();

			robot.delay(5000);
			// enterChars ("oupbeta2");

			StringSelection ss = new StringSelection(Path);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			log.error("UploadfilefromLocal exception occured " + e.toString());
			e.printStackTrace();
		}
	}

	public void HandleAlert() {
		try {

			if (driver.getCurrentUrl().contains("rto-partnering-enhance-your-rto")) {
				WD.until(ExpectedConditions.alertIsPresent()).accept();
			}

			WD.until(ExpectedConditions.alertIsPresent()).dismiss();
			log.info("Alert was dismissed");
		}

		catch (Exception e) {
			// Assert.fail("No Alert Present");
			System.out.println("No Alert Present");
			log.warn("No Alert Present ,Exception occured " + e.toString());
		}

	}

	public String XpathBuilder(String str) {
		String xpathbuilder = "//a[translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='"
				+ str.toLowerCase() + "']";
		return xpathbuilder;

	}


	public void ActionClick(WebElement WE) {
		Actions actions = new Actions(driver);
		actions.moveToElement(WE).build().perform();
		ImplicitlyWait(1000);
		actions.click(WE).build().perform();
	}

	public void dragAndDrop(WebElement sourceElement,WebElement destinationElement) {



		ActionClick(destinationElement);
		ImplicitlyWait(3000);
		try {
			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
				Actions builder = new Actions(driver);

				// Click and hold fromElem
				builder = builder.clickAndHold(sourceElement);

				builder.moveToElement(destinationElement).release().build().perform();

				ImplicitlyWait(2000);
				WaitforPageLoad();
				ImplicitlyWait(2000);

			} else {
				System.out.println("Element was not displayed to drag");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + sourceElement + "or" + destinationElement
					+ "is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sourceElement + "or" + destinationElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error occurred while performing drag and drop operation " + e.getStackTrace());
		}

	}

	
	 public void ImplicitlyWait(long I)
	 {
		 try {
				Thread.sleep(I);
			} catch (Exception e) {
				
	 }
	 }
	 
	public void switchwindow() {

		for (String S : driver.getWindowHandles()) {
			if (!S.equalsIgnoreCase(DriverManager.winHandleBefore)) {
				driver.switchTo().window(S);
				log.info("Switch to Child Window");

			}
		}

	}

	public void switchtoparentwindow() {
		for (String S : driver.getWindowHandles()) {
			if (!S.equalsIgnoreCase(DriverManager.winHandleBefore)) {
				driver.switchTo().window(S).close();

				WaitforPageLoad();

			}
		}

		driver.switchTo().window(DriverManager.winHandleBefore);
	}

}
