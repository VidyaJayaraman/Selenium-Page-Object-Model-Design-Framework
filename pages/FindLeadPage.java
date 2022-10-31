package com.testleaf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testleaf.base.ProjectSpecificMethods;

public class FindLeadPage extends ProjectSpecificMethods {

	public FindLeadPage(ChromeDriver driver) {
		
		this.driver = driver;
	
	}
	
	public FindLeadPage findByName(String firstname,String lastname) throws InterruptedException
    {
	
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("//span[text()='Name and ID']")).click();
		driver.findElement(By.xpath("//label[text()='First name:']/following::input")).sendKeys(firstname);
		driver.findElement(By.xpath("//label[text()='Last name:']/following::input")).sendKeys(lastname);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		return this;
	}
	
	public ViewLeadPage clickLeadName(String firstname) throws InterruptedException
	{
		WebElement eleLead = driver.findElement(By.xpath("//a[text()='"+firstname+"']"));
		wait.until(ExpectedConditions.elementToBeClickable(eleLead));
	    eleLead.click();
		return new ViewLeadPage(driver);
		
	}

}
