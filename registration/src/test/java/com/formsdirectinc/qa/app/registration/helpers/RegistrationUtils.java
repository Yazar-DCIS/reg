package com.formsdirectinc.qa.app.registration.helpers;



import java.util.List;
import java.util.Properties;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/*import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/



import com.formsdirectinc.qa.enums.Sites;

public class RegistrationUtils {
	
	private WebDriver driver;
	
	public RegistrationUtils(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(this.driver, this);
	}
	
	
	public void verifyRegistrationDisclaimer(Sites site, String productinfo,Properties data,String lang) {
		
		String actualcopy;
		String expectedcopy;
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
		String[] footerDisclaimer = data.getProperty(site + "_" +"registration.footerDisclaimerCopy" + lang).split(";;");
		String[] footerDisclaimerXpath = data.getProperty(site + "_" +"registration.footerDisclaimerXpath").split(";;");

		
		for (int i = 0; i <= footerDisclaimerXpath.length - 1; i++) {
			
			try {
					
				List<WebElement> copy = driver.findElements(By.cssSelector(footerDisclaimerXpath[i]));
				int Disclaimersize = copy.size();

				for (int k = 0; k <= Disclaimersize - 1; k++) {

					expectedcopy = copy.get(k).getText();

					if (i == 0) {

						actualcopy = footerDisclaimer[k];
					}

					else {
						actualcopy = footerDisclaimer[i];
					}

					if (!(actualcopy.equals(expectedcopy))) {
						System.out.println("Footer Disclaimer Copy Check in "+ driver.getCurrentUrl());
						System.out.println("Footer Disclaimer copy mismatched \nFrom: \n\n"+ actualcopy + "\n\nTo:\n\n" + expectedcopy);
					}

				}
			}

			catch (Exception e) {
				e.printStackTrace();
			}

		}
	
	}
	
	public void verifyPageTitle(Sites site, String product,Properties data){
		System.out.println("-------------------------");
		String ttl;
		String title = driver.getTitle();
		ttl = data.getProperty(site + "_title");
		if (!(ttl.equals(title))) {
			System.out.println("Page title Copy Check in "+ driver.getCurrentUrl());
			System.out.println("Page title mismatched \nFrom:\n\n " + ttl+ "\n\nTo:\n\n " + title);

		}

}
	}


