package com.QALegendBilling.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.QALegendBilling.utilities.TestHelperUtility;

public class UsersPage extends TestHelperUtility {
	public WebDriver driver;

	public UsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private final String _addButton = "//a[@class='btn btn-block btn-primary']";
	@FindBy(xpath = _addButton)
	private WebElement addButton;

	private final String _deleteButton = "//button[@class='btn btn-xs btn-danger delete_user_button']";
	@FindBy(xpath = _deleteButton)
	private WebElement deleteButton;

	private final String _editButton = "//i[@class='glyphicon glyphicon-edit']";
	@FindBy(xpath = _editButton)
	private WebElement editButton;

	private final String _searchEmail = "//input[@class='form-control input-sm']";
	@FindBy(xpath = _searchEmail)
	private WebElement searchEmail;

	private final String _userEmailField = "//table[@id='users_table']//tbody//td[4]";
	@FindBy(xpath = _userEmailField)
	private WebElement userEmailField;

	private final String _okButton = "//button[@class='swal-button swal-button--confirm swal-button--danger']";
	@FindBy(xpath = _okButton)
	private WebElement okButton;
	
	private final String _noRecordFoundField = "//td[@class='dataTables_empty']";
	@FindBy(xpath = _noRecordFoundField)
	private WebElement noRecordFoundField;
	
	private final String _userAccount = "//a[@class='dropdown-toggle']//span";
	@FindBy(xpath = _userAccount)
	WebElement userAccount;
	
	private final String _signoutField = "//a[text()='Sign Out']";
	@FindBy(xpath = _signoutField)
	WebElement signoutField;
	
	public CreatePage clickOnAddButton() {
		page.clickOnElement(addButton);
		return new CreatePage(driver);
	}

	public void clickOnDeleteButton() {
		wait.waitForElementToBeVisible(driver, deleteButton);
		page.clickOnElement(deleteButton);

	}

	public void clickOkButton() {
		page.clickOnElement(okButton);
	}

	public void setValidEmail(String email) {
		wait.waitForElementToBeVisible(driver, searchEmail);
		page.enterText(searchEmail, email);
	}

	public String getValidEmail() {
		wait.waitForElementToBeVisible(driver, userEmailField);
		String email = page.getElementText(userEmailField);
		return email;

	}
	public String getEmptyTableText() {
		
		wait.waitForElementToBeVisible(driver, noRecordFoundField);
		String emptyText= page.getElementText(noRecordFoundField);
		return emptyText;
	}

	public EditUserPage clickOnEditButton() {
		wait.waitForElementToBeVisible(driver, editButton);
		page.clickOnElement(editButton);
		return new EditUserPage(driver);

	}
	public LoginPage clickSighnoutButton() {
		wait.waitForElementToBeVisible(driver, userAccount);
		page.clickOnElement(userAccount);
		wait.waitForElementToBeVisible(driver, signoutField);
		page.clickOnElement(signoutField);
		return new LoginPage(driver);
	}

}
