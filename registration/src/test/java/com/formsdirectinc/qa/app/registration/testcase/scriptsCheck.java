package com.formsdirectinc.qa.app.registration.testcase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

/*import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/


import com.formsdirectinc.qa.utils.PropertyResource;
import com.formsdirectinc.qa.enums.Sites;
import com.formsdirectinc.qa.pages.WizardPage;

public class scriptsCheck extends WizardPage {

	
	
	public scriptsCheck registrationScripTtest(Sites site, String productinfo,Properties data) throws Exception {
	
		//PropertyResource propertyFile = new PropertyResource();
		//Properties data = propertyFile.loadProperty("registration");
		
		
	System.out.println("HELLO"+site+productinfo);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String[] prod_scripts = data.getProperty(site+"_scripts").split(":");
		for (int i = 0; i <= prod_scripts.length - 1; i++) {
			int size = driver.getPageSource().split("" + prod_scripts[i] + "").length - 1;
			if (size >= 2) {
				System.out.println(site + productinfo);
				String currentURL = driver.getCurrentUrl();
				System.out.println(currentURL);
				System.out.println("Problem in " + prod_scripts[i]
						+ "script -might be repeated");
				System.out.println("\n");
			}
			if (size == 0) {
				System.out.println(site + productinfo);
				String currentURL = driver.getCurrentUrl();
				System.out.println(currentURL);
				System.out.println("Problem in " + prod_scripts[i]
						+ "script -might be left out");
				System.out.println("\n");
			}

		}
		return this;

	
	}
	
	
	public scriptsCheck(WebDriver driver) {
		super(driver);
	}


	


	

	
	
	

		
		

	}


