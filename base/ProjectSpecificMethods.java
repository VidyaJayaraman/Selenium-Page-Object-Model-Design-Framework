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

	
//	public static Properties prop1;
//	public static ChromeDriver driver; // Sequencial Execution
	
	@BeforeMethod
	public void preCondition() throws IOException {

		// set the node
		node = test.createNode(testName);

		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));

//		// Get URL and Language From Property File
//		Properties prop = new Properties();
//		FileInputStream fis = new FileInputStream(new File("./src/main/resources/config.properties"));
//		prop.load(fis);
//		String url = prop.getProperty("url");
//		driver.get("http://leaftaps.com/opentaps/control/main");
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		String lang = prop.getProperty("lang2");
//	    prop1 = new Properties();
//		FileInputStream fis1 = new FileInputStream(new File("./src/main/resources/" + lang + ".properties"));
//		prop1.load(fis1);
//		System.out.println(prop1.getProperty("lead"));
//		System.out.println(prop1.getProperty("create_lead"))
		
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
	
	@BeforeSuite
	public void startReport() {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/result.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@BeforeClass
	public void testDetails() {
		test = extent.createTest(testName, testDescription);
		test.assignAuthor(testAuthor);
		test.assignCategory(testCategory);
	}
	
	@AfterSuite
	public void stopReport() {
		extent.flush();
	}

	public void reportStep(String message, String status) throws IOException {
		if (status.equalsIgnoreCase("pass")) {
			node.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(".././images/img" + takeSnap() + ".png").build());
		} else if (status.equalsIgnoreCase("fail")) {
			node.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(".././images/img" + takeSnap() + ".png").build());
			throw new RuntimeException("See the report for details");
		}
	}
	
	public int takeSnap() throws IOException {
		int random = (int) (Math.random() * 9999999);
		File src = driver.getScreenshotAs(OutputType.FILE);
		File desc = new File("./images/img" + random+".png");
		FileUtils.copyFile(src, desc);
		return random;
	}


}
