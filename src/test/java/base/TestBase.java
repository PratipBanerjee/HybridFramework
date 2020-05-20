package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import framework.GetData;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestBase {

    static String projectdirectory = System.getProperty("user.dir");
    public static WebDriver driver;

    public static void SetupEnvironment() throws IOException
    {

        String browser = GetData.ReadEnvironmentParameters("BROWSER");
        String url = GetData.ReadEnvironmentParameters("URL");
        String headless = GetData.ReadEnvironmentParameters("HEADLESS");

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

            }
            else
            {
                System.setProperty("webdriver.chrome.driver", projectdirectory+"\\src/test/resources\\Drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get(url);
                driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

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

            }
            else
            {
                System.setProperty("webdriver.gecko.driver", projectdirectory+"\\src/test/resources\\Drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.get(url);
                driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

            }

        }
    }

    public static void CloseBrowser()
    {
        driver.quit();
    }

    public static void CaptureScreen(String fileWithPath)
    {


    }

    public static void WaitForElement(String xpath)
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }





}
