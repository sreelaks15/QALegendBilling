package com.QALegendBilling.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.QALegendBilling.utilities.TestHelperUtility;



public class LoginPage extends TestHelperUtility {
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	private final String _username="//input[@id='username']";
	@FindBy(xpath=_username)
	WebElement username;
	private final String _password="//input[@id='password']";
	@FindBy(xpath=_password)
	WebElement password;
	private final String _submitbutton="//button[@class='btn btn-primary']";
	@FindBy(xpath= _submitbutton)
	WebElement submitbutton;
	private final String _endtourButton="//button[@class='btn btn-default btn-sm']";
	@FindBy(xpath=_endtourButton)
	WebElement endtourButton;
	private final String _forgotpassword="//a[@class='btn btn-link']";
	@FindBy(xpath=_forgotpassword)
	WebElement forgotpassword;
	//private final String _prfile="//span[text()='Sreelakshmi S']";
	private final String _prfile="//a[@class='dropdown-toggle']//following-sibling::span";
	@FindBy(xpath=_prfile)
	WebElement prfile;
	
	private final String _errorMessage ="//span[@class='help-block']";
	@FindBy(xpath = _errorMessage)
	private WebElement errorMessage;
	
	private final String _loginButton = "//button[@class='btn btn-primary']";
	@FindBy(xpath = _loginButton)
	private WebElement loginButton;
	
	public void enterUserName(String uname) {
		page.enterText(username, uname);	
	}
	public void enterUserEmail(String userName)
	{
		page.enterText(username, userName);
	}
	public void enterPassword(String paswrd) {
		page.enterText(password, paswrd);
	}
	public HomePage clickSubmit() {
		page.clickOnElement(submitbutton);
		wait.waitForElementToBeVisible(driver, endtourButton);
		page.clickOnElement(endtourButton);
		return new HomePage(driver);
		
	}
	
	public ResetPage clickforgot() {
		page.clickOnElement(forgotpassword);
		return new ResetPage(driver);
	}

	public String loginPageTitle() {
	String title=	page.getPageTitle(driver);
		return title;
		
	}
	public String getErrorMessage()
	{
		String err_msg = page.getElementText(errorMessage);
		return err_msg;
	}
	public void clickOnSubmitButton()
	{
		page.clickOnElement(loginButton);
	}

}
	



