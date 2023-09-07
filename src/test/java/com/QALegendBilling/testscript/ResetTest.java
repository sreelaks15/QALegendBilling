package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.QALegendBilling.automationcore.Base;

import com.QALegendBilling.constant.ErrorMessages;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.pages.ResetPage;
import com.QALegendBilling.utilities.ExcelUtility;

public class ResetTest extends Base {
	ResetPage reset;
	LoginPage login;

	@Test(priority=1,description="TC_004 verify if error message is displayed on the reset page with invalid email id",groups= {"Smoke"})
	public void TC_004_errorMessage_Displayed_onthe_Reset_passwordPage_with_InavlidemailID() {
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("ResetPage");
		String email = data.get(0).get(1);
		String act_ErrorMsg = data.get(1).get(1);
		login = new LoginPage(driver);
		reset = login.clickforgot();
		reset.getEmail(email);
		reset.clickResetLinkButton();
		String expEmailMsg = reset.EmailMessage();
		Assert.assertEquals(expEmailMsg, act_ErrorMsg, ErrorMessages.RESET_PASSWORD_ERROR_MESSAGE);

	}

}

