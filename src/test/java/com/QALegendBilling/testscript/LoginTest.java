package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constant.ErrorMessages;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.QALegendBilling.dataprovider.DataProviders;



public class LoginTest extends Base{
	LoginPage login;
	HomePage home;
	
	@Test(priority=1,description="TC_001 verify valid login",groups= {"Regression"})
	public void TC_001_verifyValidLogin() {
		List<ArrayList<String>>data=ExcelUtility.excelDataReader("LoginPage");
		String uname=data.get(0).get(1);
		String pwrd=data.get(1).get(1);
		String expUserName=data.get(3).get(1);
		login=new LoginPage(driver);
		login.enterUserName(uname);		
		login.enterPassword(pwrd);
		home=login.clickSubmit();     
		String actualUserName=home.getUserAccountName();
		Assert.assertEquals(expUserName, actualUserName,ErrorMessages.USERNAME_FAILURE_MESSAGE);
		}
	
	@Test(priority=1,description="TC_002 verify invalid login",groups= {"Regression"},dataProvider = "InvalidUserCredentials",dataProviderClass = DataProviders.class)
	public void TC_002_verifyInvalidLogin(String username, String password)
	{
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
		String exp_ErrorMsg = data.get(2).get(1);
		home = new HomePage(driver);
		login = home.clickOnLoginLink();
		login.enterUserEmail(username);
		login.enterPassword(password);
		login.clickOnSubmitButton();
		String actErrorMsg = login.getErrorMessage();
		Assert.assertEquals(exp_ErrorMsg, actErrorMsg,ErrorMessages.INVALID_LOGIN);
		
	}
	
}



