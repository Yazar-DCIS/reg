package com.formsdirectinc.qa.app.registration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import com.formsdirectinc.qa.app.registration.services.FilesReader;
import com.formsdirectinc.qa.app.registration.services.MailSender;
import com.formsdirectinc.qa.app.registration.testcase.*;

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
	private String testlang;
	private static final String REGISTRATION_DEFAULT_URL = "%s/registration/createaccounts.do?lang=%s&next=/payment/payment.do?application=%s";
	private CaptureScreen myscreen;
	private String username;
	private String password;
	private String emailAlert = System.getProperty("mailAlert");
	
	@BeforeTest(alwaysRun = true)
	@Parameters({ "sitename", "siteurl", "product", "username", "password" })
	public void startTest(String sitename, String siteurl, String product,
			String username, String password) throws IOException {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		myscreen = new CaptureScreen(getDriver());
		this.username = username;
		this.password = password;
		System.out.println("---------->" + username + "------------>"
				+ password);

		if (System.getProperty("site.name") != null) {
			site = Sites.valueOf(System.getProperty("site.name"));
		} else {
			site = Sites.valueOf(sitename);
		}
		System.out.println("-------------->" + site);

		if (System.getProperty("product") != null) {
			testproduct = Products.valueOf(System.getProperty("product").toUpperCase()).getProductName();
		} else {
			testproduct = Products.valueOf(product.toUpperCase())
					.getProductName();
		}
		System.out.println("-------------->" + testproduct);

		if (System.getProperty("lang") != null) {

			testlang = System.getProperty("lang");
		} else {
			testlang = "en";
		}
		System.out.println("-------------->" + testlang);

		if (siteURL() != null) {
			driver.get(String.format(REGISTRATION_DEFAULT_URL, siteURL(),
					testlang, testproduct));
		} else {
			driver.get(String.format(REGISTRATION_DEFAULT_URL, siteurl,
					testlang, testproduct));

		}
		
		
		File outputFile = new File(
				"src/test/resources/registration/output/"+site+ product+".txt");
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		String outputFilePath = outputFile.getAbsolutePath();
		FileOutputStream fos = new FileOutputStream(outputFilePath);

		if (!(emailAlert == null) && (emailAlert.equalsIgnoreCase("yes"))) {

			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
		}

		
		
	}

	@Test(groups = "Registration.Test.scriptVerification")
	public void testCase1() {

		ConverstionScripts script = PageFactory.initElements(driver,
				ConverstionScripts.class);
		script.verifyScripts(site, testproduct, data());
	}

	@Test(groups = "Registration.Test.footerdisclaimer")
	public void testCase2() {

		Footer utils = PageFactory.initElements(driver, Footer.class);
		utils.verifyRegistrationDisclaimer(site, testproduct,testlang ,data());

	}

	@Test(groups = "Registration.Test.title")
	public void testCase3() {

		Header utils = PageFactory.initElements(driver, Header.class);
		utils.verifyPageTitle(site, testproduct, data());

	}

	@Test(groups = "Registration.Test.pagelabel")
	public void testCase4() {
		
		PageLabel utils = PageFactory.initElements(driver, PageLabel.class);
		utils.verifyPageLabel(site, testproduct,testlang, data());

	}

	@AfterTest(groups = "default")
	@Parameters({ "sitename", "siteurl", "product","senderemail","receiveremail","receiveremailcc","senderpassword" })
	public void testCaseDefault(String sitename, String siteurl, String product,String senderemail,String receiveremail,String receiveremailcc,String senderpassword)
			throws Exception {
		System.out.println("Finallllllllllllllllllllllll");
		EmailIDGenerator writeEmail = PageFactory.initElements(getDriver(),
				EmailIDGenerator.class);

		Registration createUser = new Registration(getDriver(),
				"customerSignup");
		createUser.setFirstName(data().getProperty("FirstName"));
		createUser.setLastName(data().getProperty("LastName"));

		if (username.isEmpty()) {
			createUser.setEMail(writeEmail.generateEmailID(site.name(),
					testlang, testproduct));
		} else {
			createUser.setEMail(username);
		}
		createUser.setPassword(password);
		createUser.setPhoneAreaCode(data().getProperty("PhoneAreaCode"));
		createUser.setPhoneNumber1(data().getProperty("PhoneNumber1"));
		createUser.setPhoneNumber2(data().getProperty("PhoneNumber2"));
		myscreen.takeScreenShot(product, "RegistrationPage");
		
		
		
		String s = FilesReader.readFile(site+product);
		if (!(emailAlert == null || emailAlert.equalsIgnoreCase("no"))) {

			if ((s != null)) {
				MailSender.sendmail(s, site + product +"registration",senderemail,senderpassword,receiveremail,receiveremailcc);

			}
		}
		
		
		
		createUser.createAccount();

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
