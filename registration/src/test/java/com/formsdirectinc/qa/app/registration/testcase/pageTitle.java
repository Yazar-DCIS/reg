package com.formsdirectinc.qa.app.registration.testcase;


import java.util.Properties;


/*import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/


import org.openqa.selenium.WebDriver;

import com.formsdirectinc.qa.enums.Sites;
import com.formsdirectinc.qa.utils.PropertyResource;
import com.formsdirectinc.qa.pages.WizardPage;

public class pageTitle extends WizardPage {


	
	
	
	public pageTitle registrationScripTtest(String sitename, String product) throws Exception {
	
		PropertyResource propertyFile = new PropertyResource();
		Properties data = propertyFile.loadProperty("registration");
		
	
			site = Sites.valueOf(sitename);
		
		
		
		
		
		String ttl;
		String title = driver.getTitle();
		ttl = data.getProperty(site + "_title");
		if (!(ttl.equals(title))) {
			System.out.println("Page title Copy Check in "
					+ driver.getCurrentUrl());
			System.out.println("Page title mismatched \nFrom:\n\n " + ttl
					+ "\n\nTo:\n\n " + title);

		}
		return this;

	
	}
	
	
	public pageTitle(WebDriver driver) {
		super(driver);
	}

	
	
	

		
		

	}


