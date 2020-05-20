package pageobjects;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import base.TestBase;
import com.codoid.products.exception.FilloException;
import framework.GetData;
import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import javax.swing.plaf.metal.MetalLookAndFeel;

public class HomePage extends TestBase {



	public static void BrokerLogin() throws IOException
	{
		TestBase.WaitForElement(GetData.ReadObjectRepository("LoginLink"));
		driver.findElement(By.xpath(GetData.ReadObjectRepository("LoginLink"))).click();
		TestBase.WaitForElement(GetData.ReadObjectRepository("Username"));
		driver.findElement(By.xpath(GetData.ReadObjectRepository("Username"))).
				sendKeys(GetData.ReadEnvironmentParameters("BrokerUsername"));
		driver.findElement(By.xpath(GetData.ReadObjectRepository("Password"))).
				sendKeys(GetData.ReadEnvironmentParameters("BrokerPassword"));
		driver.findElement(By.xpath(GetData.ReadObjectRepository("LoginButton"))).click();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		TestBase.WaitForElement(GetData.ReadObjectRepository("LoginValidation"));

	}

	public static void UnderwriterLogin() throws IOException
	{

		TestBase.WaitForElement(GetData.ReadObjectRepository("LoginLink"));
		driver.findElement(By.xpath(GetData.ReadObjectRepository("LoginLink"))).click();
		TestBase.WaitForElement(GetData.ReadObjectRepository("Username"));
		driver.findElement(By.xpath(GetData.ReadObjectRepository("Username"))).
				sendKeys(GetData.ReadEnvironmentParameters("UndereriterUsername"));
		driver.findElement(By.xpath(GetData.ReadObjectRepository("Password"))).
				sendKeys(GetData.ReadEnvironmentParameters("UnderwriterPassword"));
		driver.findElement(By.xpath(GetData.ReadObjectRepository("LoginButton"))).click();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		TestBase.WaitForElement(GetData.ReadObjectRepository("LoginValidation"));

	}

	public static void SelectProduct() throws IOException
	{
		TestBase.WaitForElement(GetData.ReadObjectRepository("SelectProduct"));
		driver.findElement(By.xpath(GetData.ReadObjectRepository("SelectProduct"))).click();
	}

	public static void Logout() throws IOException
	{
		TestBase.WaitForElement(GetData.ReadObjectRepository("Logout"));
		driver.findElement(By.xpath(GetData.ReadObjectRepository("Logout"))).click();

	}

}
