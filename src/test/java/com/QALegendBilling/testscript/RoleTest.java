package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constant.ErrorMessages;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.pages.RolePage;
import com.QALegendBilling.utilities.ExcelUtility;

public class RoleTest extends Base{
	LoginPage login;
	HomePage home;
	RolePage role;

@Test(priority=1,description="TC_005 verify role search",groups= {"Sanity"})
public void TC_005_verifyRoleSearch() {
	List<ArrayList<String>>data=ExcelUtility.excelDataReader("LoginPage");
	String uname=data.get(0).get(1);
	String pwrd=data.get(1).get(1);
	List<ArrayList<String>>data1=ExcelUtility.excelDataReader("RoleSearch");//Excel read for role search
	String rname=data1.get(0).get(1);		
	login = new LoginPage(driver);
	login.enterUserName(uname);
	login.enterPassword(pwrd);
	home = login.clickSubmit();
	home.clickOnUserManagementTab();//clickUserManagementTab
	role = home.clickRolesTab();//clickRolesTab
	role.enterRoleName(rname);		
	String td_Search=role.getSearchValue();
	Assert.assertEquals(rname, td_Search,ErrorMessages.INVALID_ROLE_NAME);
}
}

