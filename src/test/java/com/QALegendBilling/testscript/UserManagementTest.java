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

public class UserManagementTest extends Base {
	LoginPage login;
	HomePage home;
@Test(priority=1,description="TC_007 verify if user management tabs are displayed",groups= {"Regression"})
public void TC_007_verifyUserManagementTabsAreDisplayed(){
	List<ArrayList<String>>data=ExcelUtility.excelDataReader("LoginPage");
	String uname=data.get(0).get(1);
	String pwrd=data.get(1).get(1);
	login=new LoginPage(driver);
	login.enterUserName(uname);		
	login.enterPassword(pwrd);
	home=login.clickSubmit();  
	home.clickOnUserManagementTab();
	boolean userMenuStatus = home.userMenuIsDisplayed();
	boolean roleMenuStatus = home.rolesMenuIsDisplayed();
	boolean salesMenuStatus = home.salesMenuIsDisplayed();
	Assert.assertTrue(userMenuStatus, ErrorMessages.USERSUBMENU_NOTFOUND);
	Assert.assertTrue(roleMenuStatus, ErrorMessages.ROLESUBMENU_NOTFOUND);
	Assert.assertTrue(salesMenuStatus, ErrorMessages.SALESSUBMENU_NOTFOUND);
}
}

