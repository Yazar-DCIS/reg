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
import org.openqa.selenium.support.PageFactory;

import com.formsdirectinc.qa.enums.Sites;


public class Header  {

	private WebDriver driver;



	public Header(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(this.driver, this);
	}
	
	
	public Header verifyPageTitle(Sites site, String product,Properties data){
		System.out.println("3333333333333333333333333");
		String ttl;
		String title = driver.getTitle();
		ttl = data.getProperty(site + "_title");
		if (!(ttl.equals(title))) {
			System.out.println("Page title Copy Check in "+ driver.getCurrentUrl());
			System.out.println("Page title mismatched \nFrom:\n\n " + ttl+ "\n\nTo:\n\n " + title);

		}
		return this;
			
	}
	
	}
	
	
	


