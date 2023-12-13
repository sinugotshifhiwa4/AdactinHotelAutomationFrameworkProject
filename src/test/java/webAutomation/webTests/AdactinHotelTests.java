package webAutomation.webTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webAutomation.pageObjects.WebFunctions;
import webAutomation.reports.ExtentReport;
import webAutomation.webUtilities.DataProviders;
import webAutomation.webUtilities.WebUtilities;

public class AdactinHotelTests {


    WebUtilities webUtilities = new WebUtilities();
    WebFunctions functions = new WebFunctions();
    ExtentReport report = new ExtentReport();
    ExtentReports reports;


    String sUrl, sBrowser;


    @BeforeClass
    @Parameters({"adactinUrl", "Browser"})
    public void init(String adactinUrl, String Browser){

        sUrl = adactinUrl;
        sBrowser = Browser;

        webUtilities.setWebDriver(webUtilities.initializeWebDriver(sBrowser));
        reports = report.initilizeExtentReporters("src/reports/report.html");
    }

    @Test(dataProvider = "Credentials", dataProviderClass = DataProviders.class)
    public void runTests(String Username, String Password){


        ExtentTest test = reports.createTest("Adaction Hotel Automation Framework").assignAuthor("Tshifhiwa Sinugo");
        ExtentTest node = test.createNode("Adaction Hotel").assignAuthor("Tshifhiwa Sinugo");


        try{
            webUtilities.navigate(sUrl);
            functions.logIn(webUtilities.getWebDriver(), Username, Password, node);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        reports.flush();
        webUtilities.getWebDriver().close();
        webUtilities.getWebDriver().quit();
    }
}
