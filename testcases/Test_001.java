package com.testleaf.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.testleaf.base.ProjectSpecificMethods;
import com.testleaf.pages.LoginPage;

public class Test_001 extends ProjectSpecificMethods {
	
	@BeforeTest
	public void setFilename()
	{
	filename = "Login";
	}
	

	@Test(dataProvider = "fetchData")
	//@Test
	public void runLogin(String username,String password) throws InterruptedException
	
	{
		LoginPage lp = new LoginPage(driver);
		lp.enterUsername(username)
		.enterPassword(password)
		.clickLogin();
	}

}
