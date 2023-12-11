package webAutomation.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import webAutomation.reports.ExtentReport;
import webAutomation.webPageObjects.LoginPage;
import webAutomation.webUtilities.WebActions;

public class WebFunctions extends WebActions {

    ExtentReport report = new ExtentReport();

    public void logIn(WebDriver driver, String Username, String Password, ExtentTest node){

        LoginPage loginPage = new LoginPage(driver);

        try{
            passData(loginPage.txtUsernme, driver, Username);
            passData(loginPage.txtPassword, driver, Password);
            clickObjects(loginPage.btnLogin, driver);

            Thread.sleep(2000);

            String filename = report.CaptureScreenShot(driver);

            //Validation
            if (loginPage.txtWelcomeMessage.isDisplayed()) {
                node.pass("Login was Successful", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
            } else {
                node.fail("Login was Unsuccessful", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
                Assert.fail("Login was Unsuccessful");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
