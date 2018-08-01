package com.quantum.steps;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.testng.Assert;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.utils.AppiumUtils;
import com.quantum.utils.ConfigurationUtils;
import com.quantum.utils.ConsoleUtils;
import com.quantum.utils.DeviceUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

@QAFTestStepProvider
public class FifththirdLoginDefs {

	@Given("^I start the application by the name \"([^\"]*)\"$")
	public void i_start_the_application_by_the_name(String name) throws Throwable {
		HashMap<String, Object> script = new HashMap<String, Object>();
		script.put("name", "Fifth Third");
		AppiumUtils.getAppiumDriver().executeScript("mobile:application:open", script);
		switchToContext("NATIVE_APP");
		ConsoleUtils.logInfoBlocks("Context Switch Completed");
	}
	
	@Given("^I am using an AppiumDriver$")
	public void i_am_using_an_AppiumDriver() throws Throwable {
		if (ConfigurationUtils.getBaseBundle().getPropertyValue("driver.name").contains("Remote"))
			ConsoleUtils.logInfoBlocks("Driver is an instance of QAFExtendedWebDriver");
		else if (AppiumUtils.getAppiumDriver() instanceof IOSDriver)
			ConsoleUtils.logInfoBlocks("Driver is an instance of IOSDriver");
		else if (AppiumUtils.getAppiumDriver() instanceof AndroidDriver)
			ConsoleUtils.logInfoBlocks("Driver is an instance of AndroidDriver");
		
	}

	@Given("^User is in login page$")
	public void user_is_in_login_page() throws Throwable {
		ConsoleUtils.logInfoBlocks("User is in login page");
	
	}

	@When("^I enter \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_enter_and(String arg1, String arg2) throws Throwable {
		ConsoleUtils.logInfoBlocks("------------------->"+getCurrentContextHandle());
		ConsoleUtils.logInfoBlocks("I am here in user name and password");
		new QAFExtendedWebElement("input.userdidtxt").click();
		ConsoleUtils.logInfoBlocks("*********************UserId got clicked");
		new QAFExtendedWebElement("input.userdidtxt").sendKeys(arg1);
		ConsoleUtils.logInfoBlocks("*********************UserId Got Entered");
		new QAFExtendedWebElement("input.password").click();
		ConsoleUtils.logInfoBlocks("*********************Password got clicked");
		new QAFExtendedWebElement("input.password").sendKeys(arg2);
		ConsoleUtils.logInfoBlocks("*********************Password Got Entered");
		Thread.sleep(1200);

	}

	@When("^I click on login button$")
	public void i_click_on_login_button() throws Throwable {
		new QAFExtendedWebElement("btn.login").click();
	
	}

	@Then("^I get an error message \"([^\"]*)\"$")
	public void i_get_an_error_message(String arg1) throws Throwable {
		Thread.sleep(2000);
		QAFExtendedWebElement errorText = new QAFExtendedWebElement("invaliderr.msg");
		errorText.getText();
		Thread.sleep(2000);
		ConsoleUtils.logInfoBlocks("Error Text is ----------->" + errorText.getText());
		Assert.assertEquals(errorText.getText(), "User ID and Password invalid.");
		Thread.sleep(200);
		ConsoleUtils.logInfoBlocks("Assertion completed");
		new QAFExtendedWebElement("clkok.btn").click();
		ConsoleUtils.logInfoBlocks("Clicked Ok on Attention");

	}

	@Given("^there is no internet connection$")
	public void there_is_no_internet_connection() throws Throwable {
		
		if (AppiumUtils.getAppiumDriver() instanceof IOSDriver)
		{
		Map<String, Object> appName = new HashMap<>();
		appName.put("name", "Settings");
		AppiumUtils.getAppiumDriver().executeScript("mobile:application:open", appName);
		AppiumUtils.getAppiumDriver().executeScript("mobile:application:close", appName);
		AppiumUtils.getAppiumDriver().executeScript("mobile:application:open", appName);
		switchToContext("NATIVE_APP");
		AppiumUtils.getAppiumDriver().findElementByXPath("//*[@value=\"Wi-Fi\"]").click();
		switchToContext("NATIVE_APP");
		WebElement switchOnOff = AppiumUtils.getAppiumDriver()
				.findElementByXPath("//*[@label=\"Wi-Fi\" and @class=\"UIASwitch\"]");
		switchOnOff.click();
		Thread.sleep(2000);
		// switchOnOff.click();
		ConsoleUtils.logInfoBlocks("Wifi is swithed off");
		HashMap<String, Object> script = new HashMap<String, Object>();
		script.put("name", "Fifth Third");
		AppiumUtils.getAppiumDriver().executeScript("mobile:application:open", script);
		script.put("name", "Fifth Third");
		AppiumUtils.getAppiumDriver().executeScript("mobile:application:open", script);
		}
		else
		{
			Map params = new HashMap<>(); // Set the "wifi" value to turn off the Wifi
			params.put("wifi", "disabled");
			AppiumUtils.getAppiumDriver().executeScript("mobile:network.settings:set", params);
		}
	}

	@When("^I have enter password as \"([^\"]*)\"$")
	public void i_have_enter_password_as(String arg1) throws Throwable {
		new QAFExtendedWebElement("input.password").click();
		ConsoleUtils.logInfoBlocks("Password got clicked");
		new QAFExtendedWebElement("input.password").sendKeys(arg1);
		ConsoleUtils.logInfoBlocks("Password Got Entered");
	}

	@Then("^I get an no internet error message$")
	public void i_get_an_no_internet_error_message() throws Throwable {
		QAFExtendedWebElement errorText = new QAFExtendedWebElement("wifi.msg");
		errorText.getText();
		Thread.sleep(2000);
		ConsoleUtils.logInfoBlocks("Error Text is " + errorText.getText());
		if (AppiumUtils.getAppiumDriver() instanceof IOSDriver)
		{
		errorText.assertText("You're not connected to the internet. A network connection is required.");
		}
		else
		errorText.assertText("A connection error has occurred. You may experience delays.");
		new QAFExtendedWebElement("wifi.msg").click();
		ConsoleUtils.logInfoBlocks("Clicked Ok on Attention");

	}

	public void switchToContext(String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(AppiumUtils.getAppiumDriver());
		Map<String, String> params = new HashMap<>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}
	private String getCurrentContextHandle() {         
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(AppiumUtils.getAppiumDriver());
        String context =  (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
        return context;
    }

}