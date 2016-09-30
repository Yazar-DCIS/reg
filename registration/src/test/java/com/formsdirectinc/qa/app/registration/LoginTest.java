package com.formsdirectinc.qa.app.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.formsdirectinc.qa.pages.Login;

/**
 * Login: Selenium Page Object for Login(Existing User)
 *
 * @author:Orina Revision: $Rev$
 */
public class LoginTest extends BaseRegistrationTest {
	@Parameters({ "Username", "Password" })
	public LoginTest(String Username, String Password) {
		super(Username, Password);
	}

	@BeforeTest
	public void startTest() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(absolutizeURL());
	}

	@Test
	public void test() {
		driver.findElement(By.cssSelector("a.signin-btn")).click();
		Login existingUser = new Login(driver);
		existingUser.setUserID(username);
		existingUser.setUserPassword(password);
		existingUser.logIntoApplication(username, getSite());

	}

}
