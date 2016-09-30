package com.formsdirectinc.qa.app.registration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.StringTokenizer;

import com.formsdirectinc.qa.app.registration.services.*;
import com.formsdirectinc.qa.app.registration.services.EmailIDGenerator;
import com.formsdirectinc.qa.app.registration.testcase.*;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;


import com.formsdirectinc.qa.utils.*;

/**
 * @author Mohamed Yazar
 *
 */

public class RegistrationTest extends BaseRegistrationTest {

	@Parameters({ "username", "password" })
	public RegistrationTest(String username, String password) {
		super(username, password);
	}
	
	
	protected FirefoxDriver driver;
	private FileOutputStream fos;

	@BeforeTest(alwaysRun = true)
	public void startTest() throws IOException {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(absolutizeURL());

		File outputFile = new File("src/test/resources/registration/output/"
				+ getSite() + getProduct() + ".txt");
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}
		String outputFilePath = outputFile.getAbsolutePath();
		fos = new FileOutputStream(outputFilePath);

		if (isMailAlert()) {

			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);

		}

	}

	@Test
	public void test() {
		StringTokenizer token = null;
		String registrationScope = System.getProperty("registrationTest");

		String acqDefaultScope = "scripts,header,footer,pageLabel";
		if (!(registrationScope == null)) {
			if (registrationScope.equalsIgnoreCase("All"))

				registrationScope = acqDefaultScope;

			token = new StringTokenizer(registrationScope, ",");

			while (token.hasMoreElements()) {
				switch (token.nextToken()) {

				case "scripts":
					ConverstionScripts script = PageFactory.initElements(
							driver, ConverstionScripts.class);
					script.verifyScripts(getSite(), data());
					break;

				case "header":

					Header util = PageFactory
							.initElements(driver, Header.class);
					util.verifyPageTitle(getSite(), getProduct(), getLang(),
							data());
					break;

				case "footer":
					System.out.println("testin***************");
					Footer utils = PageFactory.initElements(driver,
							Footer.class);
					utils.verifyRegistrationDisclaimer(getSite(), getProduct(),
							getLang(), data());
					break;

				case "pageLabel":

					PageLabel label = PageFactory.initElements(driver,
							PageLabel.class);
					label.verifyPageLabel(getSite(), getProduct(), getLang(),
							data());
				}

			}
		}
	}

	@AfterTest(alwaysRun = true)
	@Parameters({ "emailconfiguration" })
	public void testCaseDefault(@Optional String emailconfigProperties)
			throws Exception {
		CaptureScreen myscreen = new CaptureScreen(driver);
		myscreen.takeScreenShot(product, "RegistrationPage");
		EmailIDGenerator writeEmail = PageFactory.initElements(driver,
				EmailIDGenerator.class);

		Registration createUser = new Registration(driver, "customerSignup");
		createUser.setFirstName(data().getProperty("FirstName"));
		createUser.setLastName(data().getProperty("LastName"));

		if (username.isEmpty()) {
			createUser.setEMail(writeEmail.generateEmailID(getSite().name(),
					getLang(), getProduct()), getProduct(), getLang());
		} else {
			createUser.setEMail(username, getProduct(), getLang());
		}
		createUser.setPassword(password);
		createUser.setPhoneAreaCode(data().getProperty("PhoneAreaCode"));
		createUser.setPhoneNumber1(data().getProperty("PhoneNumber1"));
		createUser.setPhoneNumber2(data().getProperty("PhoneNumber2"));

		myscreen.takeScreenShot(product, "RegistrationPage");
		createUser.createAccount();
		String s = FilesReader.readFile(getSite() + getProduct());
		if (isMailAlert()) {

			if ((s != null)) {
				MailSender.sendmail(s, getSite() + getProduct()
						+ "registration", emailconfigProperties);

			}
		}

		fos.close();

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
