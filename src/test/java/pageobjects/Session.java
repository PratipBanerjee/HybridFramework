package pageobjects;

import base.TestBase;

import java.io.IOException;

public class Session extends TestBase {

    public static void CreateSession() throws IOException
    {
        TestBase.SetupEnvironment();
    }

    public static void DestroySession()
    {
        TestBase.CloseBrowser();
    }

}
