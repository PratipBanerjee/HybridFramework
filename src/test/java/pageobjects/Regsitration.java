package pageobjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Regsitration extends BasePage {
	
	public static void RegisterUser()
	{
		try
		{
			WaitForElement(ReadObjectRepository("EmailField"));
			driver.findElement(By.xpath(ReadObjectRepository("EmailField"))).sendKeys("");
			driver.findElement(By.xpath(ReadObjectRepository("CreateAccount"))).click();
			WaitForElement(ReadObjectRepository("Firstname"));
			driver.findElement(By.xpath(ReadObjectRepository("Gender_Mr"))).click();
			driver.findElement(By.xpath(ReadObjectRepository("Firstname"))).sendKeys("");
			driver.findElement(By.xpath(ReadObjectRepository("Lastname"))).sendKeys("");
			driver.findElement(By.xpath(ReadObjectRepository("Password"))).sendKeys("");
			Select days = new Select(driver.findElement(By.xpath(ReadObjectRepository("Days"))));
			days.selectByVisibleText("");
			Select months = new Select(driver.findElement(By.xpath(ReadObjectRepository("Months"))));
			months.selectByVisibleText("");
			Select years = new Select(driver.findElement(By.xpath(ReadObjectRepository("Years"))));
			years.selectByVisibleText("");
			driver.findElement(By.xpath(ReadObjectRepository("AddressLine1"))).sendKeys("");
			driver.findElement(By.xpath(ReadObjectRepository("City"))).sendKeys("");
			Select state = new Select(driver.findElement(By.xpath(ReadObjectRepository("State"))));
			state.selectByVisibleText("");
			driver.findElement(By.xpath(ReadObjectRepository("Postcode"))).sendKeys("");
			driver.findElement(By.xpath(ReadObjectRepository("Phone"))).sendKeys("");
			driver.findElement(By.xpath(ReadObjectRepository("Alias"))).clear();
			driver.findElement(By.xpath(ReadObjectRepository("Alias"))).sendKeys("");
			driver.findElement(By.xpath(ReadObjectRepository("RegisterButton"))).click();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void ValidateRegistration()
	{
		try
		{
			WaitForElement(ReadObjectRepository("RegistrationSuccessMsg"));
		
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
