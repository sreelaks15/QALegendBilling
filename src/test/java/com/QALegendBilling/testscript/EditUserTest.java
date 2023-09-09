package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constant.ErrorMessages;
import com.QALegendBilling.pages.CreatePage;
import com.QALegendBilling.pages.EditUserPage;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.pages.UsersPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.QALegendBilling.utilities.RandomUtility;

public class EditUserTest extends Base{
	LoginPage login;
	HomePage home;
	UsersPage users;
	EditUserPage edit;
	CreatePage create; 
	
	@Test(priority=1,description="TC_009 verify edit user",groups= {"Regression"})
	public void TC_009_verifyEditPageTab() {
		
			List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
			String uname = data.get(0).get(1);
			String pwrd = data.get(1).get(1);
			List<ArrayList<String>> data1 = ExcelUtility.excelDataReader("CreatePage");
			String prefix = data1.get(0).get(1);
			String firstName = RandomUtility.getfName();
			String lastName = RandomUtility.getlName();
			String email = RandomUtility.getRandomEmail();
			String updateEmail = RandomUtility.getRandomEmail();
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
			edit=users.clickOnEditButton();
			edit.editUserDetails(updateEmail);
			users=edit.clickOnUpdateButton();
			users.setValidEmail(updateEmail);
			String actEmail = users.getValidEmail();
			Assert.assertEquals(updateEmail, actEmail, ErrorMessages.UPDATE_ERROR_MESSAGE);
			
	
		
}
}
