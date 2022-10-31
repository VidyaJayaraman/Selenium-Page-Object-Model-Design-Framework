package com.testleaf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.testleaf.base.ProjectSpecificMethods;

public class CreateLeadPage extends ProjectSpecificMethods{
	

	public CreateLeadPage(ChromeDriver driver)
	{
		this.driver = driver;
	}
	
	public CreateLeadPage enterCompanyname(String companyname)
	{
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(companyname);
		
		
		return this;
	}
	
	public CreateLeadPage enterFirstname(String firstname)
	{
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(firstname);
		return this;
	}

	public CreateLeadPage enterLastname(String lastname)
	{
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lastname);
		return this;
	}
	
	public CreateLeadPage clickCreateLead() throws InterruptedException
	{
		Thread.sleep(4000);
		driver.findElement(By.name("submitButton")).click();
		String titlePage = driver.getTitle();
		Assert.assertTrue(titlePage.contains("Lead"));
		return this;
	}
	
	public CreateLeadPage verifyLead()
	{
	    String verifyLeadName = driver.findElement(By.xpath("//span[@id='viewLead_firstName_sp']")).getText();
		Assert.assertEquals(verifyLeadName, "Vidya");
		return this;
	}
	
	
	
}
