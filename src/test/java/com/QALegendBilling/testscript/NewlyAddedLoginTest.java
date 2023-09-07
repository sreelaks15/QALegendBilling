package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constant.ErrorMessages;
import com.QALegendBilling.pages.CreatePage;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.pages.UsersPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.demowebshop.utilities.RandomUtility;

public class NewlyAddedLoginTest extends Base{
	LoginPage login;
	HomePage home;
	UsersPage users;
	CreatePage create;
	
	@Test(priority=1,description="TC_010 Verify user login with newly added user",groups= {"Regression"})
	public void TC_010_verifyLoginUsingNewlyAddedUser() {
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
		String uname = data.get(0).get(1);
		String pwrd = data.get(1).get(1);
		List<ArrayList<String>> data1 = ExcelUtility.excelDataReader("CreatePage");
		String prefix = data1.get(0).get(1);
		String firstName = RandomUtility.getfName();
		String lastName = RandomUtility.getlName();
		String email = RandomUtility.getRandomEmail();
		String userName = firstName + lastName;
		String password = firstName + "@123";
		String confirmPassword = password;
		String salesCommissionPercentage = data1.get(8).get(1);

		login = new LoginPage(driver);
		login.enterUserName(uname);
		login.enterPassword(pwrd);
		home = login.clickSubmit();
		home.clickOnUserManagementTab();
		users = home.clickOnUsers();
		create = users.clickOnAddButton();
		create.enterUserDetails(prefix, firstName, lastName, email, userName, password, confirmPassword, salesCommissionPercentage);
		create.clickOnIsActive();
		users=create.clickOnSaveButton();
		users.setValidEmail(email);
		String actEmail = users.getValidEmail();
		Assert.assertEquals(email, actEmail, ErrorMessages.RESET_PASSWORD_ERROR_MESSAGE);
		
		String actTitile = login.loginPageTitle();
		login = home.clickSighnoutButton();
		String expTitle = login.loginPageTitle();
		Assert.assertEquals(actTitile, expTitle, ErrorMessages.TITLE_FAILURE_MESSAGE);
		
		login.enterUserName(uname);		
		login.enterPassword(pwrd);
		home=login.clickSubmit();     
		String actualUserName=home.getUserAccountName();
		String expUserName=uname;
		Assert.assertEquals(expUserName, actualUserName,ErrorMessages.USERNAME_FAILURE_MESSAGE);
	}
	
}
