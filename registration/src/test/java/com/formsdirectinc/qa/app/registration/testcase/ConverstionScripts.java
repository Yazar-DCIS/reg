package com.formsdirectinc.qa.app.registration.testcase;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.formsdirectinc.qa.enums.Sites;

public class ConverstionScripts {
	
	protected WebDriver driver;
	
	public ConverstionScripts(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public ConverstionScripts verifyScripts(Sites site,Properties data){
	
		
		
		String[] prod_scripts = data.getProperty(site+".scripts").split(":");
		
		for (int i = 0; i <= prod_scripts.length - 1; i++) {
		
			int size = driver.getPageSource().split("" + prod_scripts[i] + "").length - 1;
			
			if (size >= 2) {
				System.out.println("Problem in " + prod_scripts[i]+ "script -might be repeated");
				System.out.println("\n");
			}
			if (size == 0) {
				System.out.println("Problem in " + prod_scripts[i]+ "script -might be left out");
				System.out.println("\n");
			}

		}
		return this;
	}
	
	
	
	
	


	


	

	
	
	

		
		

	}


