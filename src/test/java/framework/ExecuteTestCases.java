package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecuteTestCases {
  
	static String projectdirectory = System.getProperty("user.dir");
	DateFormat datefrmat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	String currentdate = datefrmat.format(date).toString();
	ExtentReports reports;
	ExtentTest test;
	
	@Test
	public void Run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException, SecurityException {

		reports = new ExtentReports();

		try {
			
			Fillo TestSuite = new Fillo();
			Connection gettestcasestorun = TestSuite.getConnection(projectdirectory+"\\src\\test\\resources\\TestSuite.xlsx");
			String testsuitequery = "Select Testcase from TestSuite where RunStatus = 'Y'";
			Recordset testsuitedata = gettestcasestorun.executeQuery(testsuitequery);
			while(testsuitedata.next())
			{
				String testcasetorun = testsuitedata.getField("Testcase");
				Fillo TestSteps = new Fillo();
				Connection getteststeps = TestSteps.getConnection(projectdirectory+"\\src\\test\\resources\\TestSteps.xlsx");
				String teststepquery = "Select Pages, Steps from TestSteps where Testcase = '"+testcasetorun+"'";
				Recordset teststepdata = getteststeps.executeQuery(teststepquery);
				ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(projectdirectory+"\\Reports\\AutomationReport_"+testcasetorun+".html");
				htmlreport.loadConfig(projectdirectory+"\\extent-config.xml");
				reports.attachReporter(htmlreport);
				test = reports.createTest(testcasetorun, " Date = "+currentdate+"");
				while(teststepdata.next())
				{
					String pages = teststepdata.getField("Pages");
					String steps = teststepdata.getField("Steps");
					Object instancecreation = Class.forName("pageobjects."+pages).newInstance();
					Method executestep = instancecreation.getClass().getMethod(steps);
					executestep.invoke(instancecreation);
					test.log(Status.PASS, steps + "Passed Successfully");



				}
				reports.flush();
			}
		}
		catch(FilloException e)
		{
			e.printStackTrace();

		}
  }
}
