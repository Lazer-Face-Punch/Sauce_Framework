package com.booj.sauce.sauce_framework;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.booj.sauce.pageObject.SauceAccountLoginTest;
import com.booj.sauce.tools.SauceSuper;
import com.saucelabs.testng.SauceOnDemandTestListener;


@Listeners ({SauceOnDemandTestListener.class})
 public class BairdSaucePassTest extends SauceSuper{
		/* WebDriver driver; 
		 SauceAccountLoginTest objLogin;*/ 
		/* BaseTestSub objSetup; */
	 
	 
	 

		@Test(/*dataProvider = "hardCodedBrowsers", dataProviderClass = com.baird.pageObject.Sauce.class*/)
		public void test_Account_Login_Page_Appear_Correct_Pass() throws Exception {
			
			// Create login page object
			objLogin = new SauceAccountLoginTest(driver);

			objLogin.loginToBairdAccount("brenden@activewebsite.com", "active");
			
			//Returns welcome title and asserts true
			String loginWelcomeTitle = objLogin.getLoginWelcome();
			System.out.println("Welcome Login Title =" + loginWelcomeTitle);
			Assert.assertTrue(loginWelcomeTitle.equals("Welcome, brenden thornsberry"));

		}
		
		/*@AfterMethod
		public void tearDownAfterTestClass()throws Exception{
	Sauce.tearDown();
		}*/

	}
