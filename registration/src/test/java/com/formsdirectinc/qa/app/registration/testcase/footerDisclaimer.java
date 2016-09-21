package com.formsdirectinc.qa.app.registration.testcase;



import java.util.List;
import java.util.Properties;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/



import com.formsdirectinc.qa.enums.Sites;
import com.formsdirectinc.qa.pages.WizardPage;

public class footerDisclaimer extends WizardPage {

	
	
	public footerDisclaimer registrationScripTtest(Sites site, String productinfo,Properties data,String lang) throws Exception {
	
		String page="registration";
		
		
	String[] footerDisclaimer = data.getProperty(
				site + "_" + page + "footerDisclaimerCopy" + lang).split(";;");
		String[] footerDisclaimerXpath = data.getProperty(
				site + "_" + page + "footerDisclaimerXpath").split(";;");

		String sts;
		String c3;

		for (int i = 0; i <= footerDisclaimerXpath.length - 1; i++) {

			try {

				List<WebElement> copy = driver.findElements(By
						.cssSelector(footerDisclaimerXpath[i]));

				int Disclaimersize = copy.size();

				for (int k = 0; k <= Disclaimersize - 1; k++) {

					c3 = copy.get(k).getText();

					if (i == 0) {

						sts = footerDisclaimer[k];
					}

					else {
						sts = footerDisclaimer[i];
					}

					if (!(sts.equals(c3))) {
						System.out.println("Footer Disclaimer Copy Check in "
								+ driver.getCurrentUrl());

						System.out
								.println("Footer Disclaimer copy mismatched \nFrom: \n\n"
										+ sts + "\n\nTo:\n\n" + c3);

					}

				}
			}

			catch (Exception e) {

				System.out.println(e);

			}

		}
		return this;

	
	}
	
	
	public footerDisclaimer(WebDriver driver) {
		super(driver);
	}


	


	

	
	
	

		
		

	}


