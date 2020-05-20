package pageobjects;

import base.TestBase;
import com.codoid.products.exception.FilloException;
import framework.GetData;
import org.openqa.selenium.By;

import java.io.IOException;

public class Checkout extends TestBase {


    public static void PlaceOrder () throws FilloException, InterruptedException, IOException
    {
        TestBase.WaitForElement(GetData.ReadObjectRepository("PlaceOrder"));
        driver.findElement(By.xpath(GetData.ReadObjectRepository("PlaceOrder"))).click();
        TestBase.WaitForElement(GetData.ReadObjectRepository("Name"));
        driver.findElement(By.xpath(GetData.ReadObjectRepository("Name"))).
                sendKeys(GetData.GetTestData("Checkout","Name"));
        driver.findElement(By.xpath(GetData.ReadObjectRepository("Country"))).
                sendKeys(GetData.GetTestData("Checkout","Country"));
        driver.findElement(By.xpath(GetData.ReadObjectRepository("City"))).
                sendKeys(GetData.GetTestData("Checkout","City"));
        driver.findElement(By.xpath(GetData.ReadObjectRepository("CreditCard"))).
                sendKeys(GetData.GetTestData("Checkout","Creditcard"));
        driver.findElement(By.xpath(GetData.ReadObjectRepository("Month"))).
                sendKeys(GetData.GetTestData("Checkout","Month"));
        driver.findElement(By.xpath(GetData.ReadObjectRepository("Year"))).
                sendKeys(GetData.GetTestData("Checkout","Year"));
        driver.findElement(By.xpath(GetData.ReadObjectRepository("Purchase"))).click();
        TestBase.WaitForElement(GetData.ReadObjectRepository("PurchaseConfirmation"));
        Thread.sleep(5000);
        driver.findElement(By.xpath(GetData.ReadObjectRepository("ReturnToHome"))).click();

    }
}
