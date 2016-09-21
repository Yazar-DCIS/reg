package com.formsdirectinc.qa.app.registration;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.formsdirectinc.qa.tags.BaseTag;
import com.formsdirectinc.qa.utils.EmailIDGenerator;

/**
 * Sign UP: Selenium page Model for Register And Payment Controls
 * 
 * @author Orina
 * @Date: 24/02/2016
 */
public class Registration extends BaseTag {

	String firstNameFieldExpression = "%s-firstName";
	String emailFieldExpression = "%s-emailId";
	String lastNameFieldExpression = "%s-lastName";
	String passwordFieldExpression = "%s-password";
	String confirmPasswordFieldExpression = "confirm-%s-password";
	String phoneAreaCodeFieldExpression = "%s-signinPhone-areaCode";
	String phoneNumber1FieldExpression = "%s-signinPhone-phone1";
	String phoneNumber2FieldExpression = "%s-signinPhone-phone2";
	String phoneNumber3FieldExpression = "%s-signinPhone-phone3";

	String hintQuestionFieldExpression = "%s-hintQuestion";
	String hintAnswerFieldExpression = "%s-hintAnswer";
	String termsFieldExpression = "sign_up_accepts_terms";
	String createFieldExpression = "Createbutton";

	WebElement valuefield, confirmvaluefield;

	public Registration(WebDriver driver, String property) {
		super(driver, property);

	}

	public Registration(WebDriver driver) {
		super(driver);

	}

	public Registration setFirstName(String firstName) {
		valuefield = driver.findElement(By.id(String.format(
				firstNameFieldExpression, property)));
		valuefield.sendKeys(firstName);

		return this;
	}

	public String setEMail(String email) {
		valuefield = driver.findElement(By.id(String.format(
				emailFieldExpression, property)));
		valuefield.sendKeys(email);
		Reporter.log("<br>" + "EMail ID" + "<br>" + "<font color=\"green\">"
				+ email + "</font>");
		EmailIDGenerator writeEmail = PageFactory.initElements(driver,
				EmailIDGenerator.class);
		try {
			writeEmail.writeEMail_ID(email);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}

	public Registration setConfirmPassword(String confirmPassword) {
		valuefield = driver.findElement(By.id(String.format(
				confirmPasswordFieldExpression, property)));
		valuefield.sendKeys(confirmPassword);

		return this;
	}

	public Registration setLastName(String lastName) {
		valuefield = driver.findElement(By.id(String.format(
				lastNameFieldExpression, property)));
		valuefield.sendKeys(lastName);

		return this;
	}

	public Registration setPassword(String password) {
		valuefield = driver.findElement(By.id(String.format(
				passwordFieldExpression, property)));
		valuefield.sendKeys(password);

		return this;
	}

	public Registration setPhoneAreaCode(String phoneNumber) {
		valuefield = driver.findElement(By.id(String.format(
				phoneAreaCodeFieldExpression, property)));
		valuefield.sendKeys(phoneNumber);

		return this;
	}

	public Registration setPhoneNumber1(String phoneNumber) {
		valuefield = driver.findElement(By.id(String.format(
				phoneNumber1FieldExpression, property)));
		valuefield.sendKeys(phoneNumber);

		return this;
	}

	public Registration setPhoneNumber2(String phoneNumber) {
		valuefield = driver.findElement(By.id(String.format(
				phoneNumber2FieldExpression, property)));
		valuefield.sendKeys(phoneNumber);

		return this;
	}

	public Registration setPhoneNumber3(String phoneNumber) {
		valuefield = driver.findElement(By.id(String.format(
				phoneNumber3FieldExpression, property)));
		valuefield.sendKeys(phoneNumber);

		return this;
	}

	public Registration setQuestion(String question) {
		valuefield = driver.findElement(By.id(String.format(
				hintQuestionFieldExpression, property)));
		valuefield.sendKeys(question);

		return this;
	}

	public Registration setAnswer(String answer) {
		valuefield = driver.findElement(By.id(String.format(
				hintAnswerFieldExpression, property)));
		valuefield.sendKeys(answer);

		return this;
	}

	public Registration createAccount() {
		valuefield = driver.findElement(By.id(createFieldExpression));
		valuefield.click();

		return this;
	}

}
