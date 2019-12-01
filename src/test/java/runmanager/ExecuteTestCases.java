package runmanager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExecuteTestCases {
  
	static String projectdirectory = System.getProperty("user.dir");
	
	@Test
	public void Run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		
		try {
			
			Fillo TestSuite = new Fillo();
			Connection gettestcasestorun = TestSuite.getConnection(projectdirectory+"\\src/test/resources\\TestSuite.xlsx");
			String testsuitequery = "Select Testcase from TestSuite where RunStatus = 'Y'";
			Recordset testsuitedata = gettestcasestorun.executeQuery(testsuitequery);
			while(testsuitedata.next())
			{
				String testcasetorun = testsuitedata.getField("Testcase");
				Fillo TestSteps = new Fillo();
				Connection getteststeps = TestSteps.getConnection(projectdirectory+"\\src/test/resources\\TestSteps.xlsx");
				String teststepquery = "Select Pages, Steps from TestSteps where Testcase = '"+testcasetorun+"'";
				Recordset teststepdata = getteststeps.executeQuery(teststepquery);
				while(teststepdata.next())
				{
					String pages = teststepdata.getField("Pages");
					String steps = teststepdata.getField("Steps");
					Object instancecreation = Class.forName("pageobjects."+pages).newInstance();
					Method executestep = instancecreation.getClass().getMethod(steps);
					executestep.invoke(instancecreation);
				}
			}
		}
		catch(FilloException e)
		{
			e.printStackTrace();
		}
  }
}
