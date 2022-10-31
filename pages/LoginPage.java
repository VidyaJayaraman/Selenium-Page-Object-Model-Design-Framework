package com.testleaf.pages;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.testleaf.base.ProjectSpecificMethods;

public class LoginPage extends ProjectSpecificMethods {
	
	public LoginPage(ChromeDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(how = How.ID , using ="username")
	WebElement eleUsername;
	public LoginPage enterUsername(String username)throws IOException {
		try {
			eleUsername.sendKeys(username);
			reportStep("Username is entered successfully", "pass");
		} catch (NoSuchElementException e) {
			reportStep("Username element is not found", "fail");
		}
//		LoginPage lp = new LoginPage();
		return this;

	}

	@FindBy(how = How.ID , using ="password")
	WebElement elePassword;
	public LoginPage enterPassword(String password)
	{
		//driver.findElement(By.id("password")).sendKeys(password);
	    elePassword.sendKeys(password);
		return this;
	}

	public HomePage clickLogin()
	{
		driver.findElement(By.className("decorativeSubmit")).click();
		return new HomePage(driver);
	}
	
}
