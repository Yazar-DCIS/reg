package com.formsdirectinc.qa.app.registration;

import java.util.Properties;

import com.formsdirectinc.qa.app.registration.helpers.*;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import com.formsdirectinc.qa.TestBase;
import com.formsdirectinc.qa.enums.*;
import com.formsdirectinc.qa.utils.*;

/**
 * Selenium Page Model for RegistrationPage CoreSites
 * 
 * @author:Orina
 * @Date: 6/29/15
 * @Updated:24.02.2016
 */

public class RegistrationTest extends TestBase {
	
	private String testproduct;
	private Sites site;
	private static final String REGISTRATION_DEFAULT_URL="%s/registration/createaccounts.do?lang=en&next=/payment/payment.do?application=%s";
	private CaptureScreen myscreen;
	private String username;
	private String password;
	
	
	
	@BeforeTest(alwaysRun=true)
	@Parameters({ "sitename", "siteurl", "product", "username", "password"})
	public void startTest(String sitename,String siteurl,String product,String username,String password) {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		myscreen = new CaptureScreen(getDriver());
		this.username=username;
		this.password=password;
		if (System.getProperty("site.name") != null)
			site = Sites.valueOf(System.getProperty("site.name"));
		
		site = Sites.valueOf(sitename);

		if (System.getProperty("product") != null)
			testproduct = Products.valueOf(
					String.format(product, System.getProperty("product")).toUpperCase()).getProductName();

			testproduct = Products.valueOf(String.format(product).toUpperCase()).getProductName();
		

		if (siteURL() != null)
			driver.get(String.format(REGISTRATION_DEFAULT_URL, siteURL(),testproduct));
			
		driver.get(String.format(REGISTRATION_DEFAULT_URL, siteurl,testproduct));
	}

	
	@AfterTest(groups="default")
	@Parameters({ "sitename", "siteurl", "product"})
	public void test(String sitename, String siteurl, String product) throws Exception {
System.out.println("&&&&&&&&&&&&&&&&&&");
		EmailIDGenerator writeEmail = PageFactory.initElements(getDriver(),EmailIDGenerator.class);
		
		Registration createUser = new Registration(getDriver(),"customerSignup");
		createUser.setFirstName(data().getProperty("FirstName"));
		createUser.setLastName(data().getProperty("LastName"));

		if (username.isEmpty()) 
			createUser.setEMail(writeEmail.generateEmailID(site.name()));
		createUser.setEMail(username);
		createUser.setPassword(password);
		createUser.setPhoneAreaCode(data().getProperty("PhoneAreaCode"));
		createUser.setPhoneNumber1(data().getProperty("PhoneNumber1"));
		createUser.setPhoneNumber2(data().getProperty("PhoneNumber2"));
		createUser.createAccount();
		
	}
	
	@Test(groups="Registration.Test.scriptVerification")
	@Parameters({ "sitename", "siteurl", "product"})
	public void test2(String sitename, String siteurl, String product) {
		
		ConverstionScripts script = PageFactory.initElements(driver,ConverstionScripts.class);
		script.verifyScripts(site, testproduct, data());
		}
	
	
	@Test(groups="Registration.Test.footerdisclaimer")
	@Parameters({ "sitename", "siteurl", "product"})
	public void test3(String sitename, String siteurl, String product){
		
		RegistrationUtils utils = PageFactory.initElements(driver,RegistrationUtils.class);
		utils.verifyRegistrationDisclaimer(site, testproduct, data(),"en");
		
	}
	
	
	@Test(groups="Registration.Test.title")
	@Parameters({ "sitename", "siteurl", "product"})
	public void test4(String sitename, String siteurl, String product){
		
		
		
		RegistrationUtils title = PageFactory.initElements(driver,RegistrationUtils.class);
		title.verifyPageTitle(site, testproduct, data());
		
	}
	

	@AfterSuite
	public void endTest() {
		driver.quit();
	}
		
	
	protected Properties data() {
		PropertyResource propertyFile = new PropertyResource();
		return propertyFile.loadProperty("registration");
	}
	
	
}
