package pageobjects;

import java.io.IOException;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

public class HomePage extends BasePage {
	
	
	
	public static void ClickSignIn()
	{
		try
		{
			WaitForElement(ReadObjectRepository("SignInLink"));
			driver.findElement(By.xpath(ReadObjectRepository("SignInLink"))).click();
			test.log(Status.PASS, "Sign In Link Clicked");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			test.log(Status.FAIL, e);
		}
		
	}
	
	public static void SignIn()
	{
		try
		{
			WaitForElement(ReadObjectRepository("Email"));
			driver.findElement(By.xpath(ReadObjectRepository("Email"))).sendKeys("");
			driver.findElement(By.xpath(ReadObjectRepository("Password"))).sendKeys("");
			driver.findElement(By.xpath(ReadObjectRepository("LoginButton"))).click();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void Search()
	{
		try
		{
			WaitForElement(ReadObjectRepository("SearchField"));
			driver.findElement(By.xpath(ReadObjectRepository("SearchField"))).sendKeys("");
			driver.findElement(By.xpath(ReadObjectRepository("SearchButton"))).click();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void ClickShoppingCart()
	{
		try
		{
			WaitForElement(ReadObjectRepository("ShoppingCart"));			
			driver.findElement(By.xpath(ReadObjectRepository("ShoppingCart"))).click();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void Logout()
	{
		try
		{
			WaitForElement(ReadObjectRepository("LogOut"));			
			driver.findElement(By.xpath(ReadObjectRepository("LogOut"))).click();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void NavigateBackToHomePage()
	{
		try
		{
			WaitForElement(ReadObjectRepository("HomePageLink"));			
			driver.findElement(By.xpath(ReadObjectRepository("HomePageLink"))).click();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
