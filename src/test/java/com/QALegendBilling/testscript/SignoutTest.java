package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constant.ErrorMessages;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.pages.SignoutPage;
import com.QALegendBilling.utilities.ExcelUtility;

public class SignoutTest extends Base {
	SignoutPage signout;
	LoginPage login;
	HomePage home;

	@Test(priority=2,description="TC_003 verify if user is navigated to loginpage by clicking signout button",groups= {"Sanity"})
	public void TC_003_verifyuser_isNavigate_to_LoginPage_by_clicking_signout() {
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("HomePage");
		String uname = data.get(0).get(1);
		login = new LoginPage(driver);
		String actTitile = login.loginPageTitle();
		System.out.println(actTitile);
		login.enterUserName(uname);
		String pwrd = data.get(1).get(1);
		login.enterPassword(pwrd);
		home = login.clickSubmit();
		login = home.clickSighnoutButton();
		String expTitle = login.loginPageTitle();
		Assert.assertEquals(actTitile, expTitle, ErrorMessages.TITLE_FAILURE_MESSAGE);

	}
}

