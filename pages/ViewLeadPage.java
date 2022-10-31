package com.testleaf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.testleaf.base.ProjectSpecificMethods;

public class ViewLeadPage  extends ProjectSpecificMethods{

	

	public ViewLeadPage(ChromeDriver driver) {
		
	this.driver=driver;	
		
	}

	public ViewLeadPage editLead(String editCompanyName)
	{

		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys(editCompanyName);
		driver.findElement(By.name("submitButton")).click();
		return this;
	}
}
