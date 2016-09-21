package com.formsdirectinc.qa.app.registration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.formsdirectinc.qa.TestBase;
import com.formsdirectinc.qa.enums.Sites;
import com.formsdirectinc.qa.pages.Login;
import com.formsdirectinc.qa.utils.CheckPageErrors;

/**
 * Login: Selenium Page Object for Login(Existing User)
 *
 * @author:Orina Revision: $Rev$
 */
public class LoginTest extends TestBase {

	@BeforeTest
	public void startTest() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	@Parameters({ "sitename", "siteurl", "username", "password" })
	public void test(String sitename, String siteurl, String username,
			String password) {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		String loginURL = "%s/login.jsp?next=applicationcenter.do&lang=en";

		Sites site;
		if (System.getProperty("site.name") == null) {
			site = Sites.valueOf(sitename);
		} else {
			site = Sites.valueOf(System.getProperty("site.name"));
		}

		if (siteURL() == null) {
			driver.get(String.format(loginURL, siteurl));
		} else {
			driver.get(String.format(loginURL, siteURL()));
		}

		Login existingUser = new Login(driver);
		existingUser.setUserID(username);
		existingUser.setUserPassword(password);
		existingUser.logIntoApplication(username, site);

		CheckPageErrors pageValidation = new CheckPageErrors(driver);
		pageValidation.checkWhetherLoginPageHasError(site);

	}

	@AfterTest
	public void endTest() {
		driver.quit();
	}
}
