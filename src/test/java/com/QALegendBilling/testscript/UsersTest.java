package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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

public class UsersTest extends Base {
	LoginPage login;
	HomePage home;
	UsersPage users;
	CreatePage create;

	@Test(priority=1,description="TC_006 verify add user details",groups= {"Smoke"})
	public void TC_006_verifyAddUserDetails() {
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
	}
}
