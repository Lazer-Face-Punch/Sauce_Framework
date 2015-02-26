package com.booj.sauce.utilities;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.booj.sauce.drivers.DriverHelper;
import com.saucelabs.common.SauceOnDemandAuthentication;

public class TestSessionHelper {

    public static TestSessionInformation getTestSessionInformation(Method testName, SauceOnDemandAuthentication authentication) throws IOException {
    	
    	/*HtmlUnitDriver unitDriver = null;*/
        WebDriver driver;
        String sessionId = null;
        HtmlUnitDriver unitDriver = new HtmlUnitDriver();
        unitDriver.setJavascriptEnabled(true);

        Properties testProperties = getTestProperties();
        if (TestPropertiesHelper.testOnSauce(testProperties)) {
            driver = DriverHelper.getRemoteWebDriver(testName, authentication, System.getProperty("browserAndOs"));
            sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        } else {
        
        	driver = new FirefoxDriver();
        
        }
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

        TestSessionInformation testSessionInformation = new TestSessionInformation();
        testSessionInformation.setDriver(driver);
        testSessionInformation.setSessionId(sessionId);
        testSessionInformation.setTestProperties(new TestProperties(testProperties));

        return testSessionInformation;
    }

    private static Properties getTestProperties() throws IOException {
        return TestPropertiesHelper.getProperties();
    }
}
