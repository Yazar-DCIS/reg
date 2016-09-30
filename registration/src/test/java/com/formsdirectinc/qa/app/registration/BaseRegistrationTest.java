package com.formsdirectinc.qa.app.registration;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.formsdirectinc.qa.app.registration.services.EmailIDGenerator;
import com.formsdirectinc.qa.enums.Products;
import com.formsdirectinc.qa.enums.Sites;

public class BaseRegistrationTest {

	final static Logger logger = LoggerFactory
			.getLogger(BaseRegistrationTest.class);
	private static final String REGISTRATION_DEFAULT_URL = "/registration/createaccounts.do";
	private Sites site;
	protected String product;
	private String siteurl;
	private String lang;
	private boolean alert;
	protected String username;
	protected String password;
	protected WebDriver driver;

	public BaseRegistrationTest(String Username, String Password) {
		this.username = Username;
		this.password = Password;
	}

	public String getLang() {
		return lang;
	}

	@BeforeSuite
	@Parameters({ "lang" })
	public void setLang(@Optional String lang) {
		if (lang == null)
			lang = "en";
		this.lang = lang;
	}

	public Sites getSite() {
		return site;
	}

	@BeforeSuite
	@Parameters({ "sitename" })
	public void setSite(Sites site) {
		if (!(System.getProperty("site.name") == null))
			site = Sites.valueOf(System.getProperty("site.name"));

		this.site = site;
	}

	@BeforeSuite
	@Parameters({ "product" })
	public void setProduct(String product) {
		if (!(System.getProperty("product") == null))
			product = Products.valueOf(
					(System.getProperty("product")).toUpperCase())
					.getProductName();
		this.product = product;
	}

	public String getProduct() {
		return product;
	}

	public String getSiteurl() {
		return siteurl;
	}

	@BeforeSuite
	@Parameters({ "siteurl" })
	public void setSiteurl(String siteurl) {
		if (!(System.getProperty("site.url") == null))
			siteurl = System.getProperty("site.url");
		this.siteurl = siteurl;
	}

	protected String absolutizeURL() {
		StringBuilder urlbuilder = null;
		urlbuilder = new StringBuilder();
		urlbuilder.append(getSiteurl())
				.append(REGISTRATION_DEFAULT_URL + "?lang=")
				.append(getLang() + "&next=/payment/payment.do")
				.append("?application=").append(getProduct());
		return urlbuilder.toString();
	}

	public String writeEmail() {
		EmailIDGenerator writeEmail = PageFactory.initElements(driver,
				EmailIDGenerator.class);
		String randommailid = null;
		try {
			writeEmail.writeEMail_ID(username, getProduct(), getLang());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return randommailid;
	}

	public boolean isMailAlert() {
		return alert;
	}
	@BeforeSuite
	@Parameters({ "mailalert" })
	public void setAlert(@Optional boolean alert) {
	this.alert = alert;
	}

}
