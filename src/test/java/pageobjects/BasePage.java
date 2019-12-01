package pageobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class BasePage {
	
	static String projectdirectory = System.getProperty("user.dir");
	static WebDriver driver;
	
	static DateFormat datefrmat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static Date date = new Date();
	static String currentdate = datefrmat.format(date).toString();
	static ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(projectdirectory+"\\Reports\\AutomationReport.html");
	static ExtentReports report = new ExtentReports();
	static ExtentTest test;
	
	
	public static String ReadEnvironmentParameters(String key) throws IOException
	{
			Properties setupenvironment = new Properties();
			FileInputStream filepath = new FileInputStream(new File(projectdirectory+"\\src/test/resources\\Environment.properties"));
			setupenvironment.load(filepath);			
			String value = setupenvironment.getProperty(key);
			return value;	
		
	}
	
	
	public static String ReadObjectRepository(String key) throws IOException
	{
		
			Properties objectrepository = new Properties();
			FileInputStream filepath = new FileInputStream(new File(projectdirectory+"\\src/test/resources\\ObjectRepository.properties"));
			objectrepository.load(filepath);
			String value = objectrepository.getProperty(key);
			return value;
		
	}
	
	public static void SetupEnvironment() throws IOException
	{
		
		String browser = ReadEnvironmentParameters("BROWSER");
		String url = ReadEnvironmentParameters("URL");
		String headless = ReadEnvironmentParameters("HEADLESS");
		htmlreport.loadConfig(projectdirectory+"\\src/test/resources\\extent-config.xml");
		report.attachReporter(htmlreport);
		test = report.createTest("Launch Application", "Browser = "+browser+"and Date = "+currentdate+"");
		test.assignAuthor("Pratip Banerjee");
		test.assignCategory("Regression Test Suite");
		if(browser.equalsIgnoreCase("chrome"))
		{	
			if(headless.equalsIgnoreCase("yes"))
			{
				System.setProperty("webdriver.chrome.driver", projectdirectory+"\\src/test/resources\\Drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("headless");
				driver = new ChromeDriver(options);
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
				test.log(Status.PASS, "Browser CHROME Launched Successfully With Headless Option");
				test.log(Status.PASS, "Application Opened Successfully");
				
			}
			else
			{
				System.setProperty("webdriver.chrome.driver", projectdirectory+"\\src/test/resources\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
				test.log(Status.PASS, "Browser CHROME Launched Successfully Without Headless Option");
				test.log(Status.PASS, "Application Opened Successfully");
			}
			
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			if(headless.equalsIgnoreCase("yes"))
			{
				System.setProperty("webdriver.gecko.driver", projectdirectory+"\\src/test/resources\\Drivers\\geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);
				driver = new FirefoxDriver(options);
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
				test.log(Status.PASS, "Browser FIREFOX Launched Successfully With Headless Option");
				test.log(Status.PASS, "Application Opened Successfully");
			}
			else
			{
				System.setProperty("webdriver.gecko.driver", projectdirectory+"\\src/test/resources\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
				test.log(Status.PASS, "Browser FIREFOX Launched Successfully Without Headless Option");
				test.log(Status.PASS, "Application Opened Successfully");
			}			
			
		}
	}
	
	public static void CloseBrowser()
	{
		driver.quit();
		report.flush();
	}
	
	public static void CaptureScreen(WebDriver driver, String fileWithPath)
	{
		
	}
	
	public static void WaitForElement(String xpath)
	{		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
	public static String GetTestData(String columnname) throws FilloException
	{
			String testdatavalue = null;
			Fillo TestSuite = new Fillo();
			Connection gettestcasestorun = TestSuite.getConnection(projectdirectory+"\\src/test/resources\\TestSuite.xlsx");
			String testsuitequery = "Select Testcase from TestSuite where RunStatus = 'Y'";
			Recordset testsuitedata = gettestcasestorun.executeQuery(testsuitequery);
			while(testsuitedata.next())
			{
				String testcasetorun = testsuitedata.getField("Testcase");
				Fillo TestData = new Fillo();
				Connection gettestdata = TestData.getConnection(projectdirectory+"\\src/test/resources\\TestData.xlsx");
				String testdataquery = "Select * from '"+testcasetorun+"'";
				Recordset teststepdata = gettestdata.executeQuery(testdataquery);
				teststepdata.next();
				ArrayList<String> TestDataList = teststepdata.getFieldNames();
				Iterator<String> TestDataIterator = TestDataList.iterator();
				while(TestDataIterator.hasNext())
				{
					for(int i = 0; i<TestDataList.size()-1; i++)
					{
						String data = TestDataIterator.next();
						String datavalue = teststepdata.getField(data);
						if(datavalue.equalsIgnoreCase(columnname))
						{
							i=i+1;
	                        String testData=TestDataList.get(i);
	                        String testValue= teststepdata.getField(testData);
	                        testdatavalue = testValue;
	                        
						}
					}
				}
				
			}
			return testdatavalue;
		}
		
		
		
	

}
