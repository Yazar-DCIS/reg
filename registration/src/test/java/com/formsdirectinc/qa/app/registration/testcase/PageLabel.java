package com.formsdirectinc.qa.app.registration.testcase;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.formsdirectinc.qa.enums.Sites;

public class PageLabel {
	
	protected WebDriver driver;
	
	public PageLabel(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public PageLabel verifyPageLabel(Sites site, String productinfo,String lang,Properties data){
	
		System.out.println("4444444444444444444444444");
		driver.findElement(By.id("customerSignup-password")).click();
		String[] pageLabel = data.getProperty(
				site+"_registrationpageLabelCopy"+lang).split(";;");
		String[] pageLabelXpath = data.getProperty(
				site+"_registrationpageLabelXpath").split(";;");

		String sts;
		String c3;

		for (int i = 0; i <= pageLabelXpath.length - 1; i++) {

			try {

				List<WebElement> copy = driver.findElements(By
						.cssSelector(pageLabelXpath[i]));

				int Disclaimersize = copy.size();

				for (int k = 0; k <= Disclaimersize - 1; k++) {

					c3 = copy.get(k).getText();
					if (i == 0) {

						sts = pageLabel[k];
					}

					else {
						sts = pageLabel[i];
					}

					if (!(sts.equals(c3))) {
						System.out.println("Page Label Copy Check in "
								+ driver.getCurrentUrl());

						System.out
								.println("Page Label Copy mismatched \nFrom: \n\n"
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
	
	
	
	
	


	


	

	
	
	

		
		

	}


