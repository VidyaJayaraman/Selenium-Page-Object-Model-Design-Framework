package com.testleaf.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.servicenow.utils.ReadExcelDataServiceNow;
import com.testleaf.utils.ReadExcelData;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProjectSpecificMethods {

	// public static ChromeDriver driver;

	
	public String filename;
	
	public ChromeDriver driver;
	public static WebDriverWait wait;
	
	public String fileName;
	public static Properties prop1;
	public ExtentReports extent;
	public static ExtentTest test, node;
	public String testName, testDescription, testAuthor, testCategory;

	

	public static ChromeDriver driver; 
	
	@BeforeMethod
	public void preCondition() throws IOException {

		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			
	}

	@DataProvider(name = "fetchData")

	public String[][] fetchExcelData() throws IOException 
	{

		String[][] fetchExcelData = ReadExcelData.excelDataFetch(filename);
		return fetchExcelData;
	}

	@AfterMethod
	public void postCondition() 
	{

		driver.close();

	}
	
	
	
}
