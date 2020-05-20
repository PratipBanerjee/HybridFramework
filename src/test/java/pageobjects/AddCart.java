package pageobjects;

import base.TestBase;
import framework.GetData;
import org.openqa.selenium.By;

import java.io.IOException;

public class AddCart extends TestBase{



        public static void AddProductToCart() throws IOException, InterruptedException
        {
            TestBase.WaitForElement(GetData.ReadObjectRepository("AddToCart"));
            driver.findElement(By.xpath(GetData.ReadObjectRepository("AddToCart"))).click();
            Thread.sleep(5000);
            driver.switchTo().alert().accept();

        }

        public static void SelectCart() throws IOException
        {
            TestBase.WaitForElement(GetData.ReadObjectRepository("Cart"));
            driver.findElement(By.xpath(GetData.ReadObjectRepository("Cart"))).click();
            TestBase.WaitForElement(GetData.ReadObjectRepository("CartValidate"));

        }

}
