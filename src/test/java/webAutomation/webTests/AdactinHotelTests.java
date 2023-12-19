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

    @Test(priority = 1, dataProvider = "Credentials", dataProviderClass = DataProviders.class)
    public void testLogIn(String Username, String Password){


        ExtentTest test = reports.createTest("Login").assignAuthor("Tshifhiwa");
        ExtentTest node = test.createNode("Validate login was successful");

        try{
            webUtilities.navigate(sUrl);
            functions.logIn(webUtilities.getWebDriver(), Username, Password, node);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2, dataProvider = "SearchHotelData", dataProviderClass = DataProviders.class)
    public void testSearchHotel(String Location, String Hotels, String RoomType, String NumberOfRooms, String CheckInDate,
                                String CheckOutDate, String AdultPerRoom, String ChildrenPerRoom){

        ExtentTest test = reports.createTest("Search Hotel").assignAuthor("Tshifhiwa");
        ExtentTest node = test.createNode("Validate select hotel is displayed");

        try{
            functions.searchHotel(webUtilities.getWebDriver(),
                    Location,
                    Hotels,
                    RoomType,
                    NumberOfRooms,
                    CheckInDate,
                    CheckOutDate,
                    AdultPerRoom,
                    ChildrenPerRoom,
                    node);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 3)
    public void testSelectHotel(){

        ExtentTest test = reports.createTest("Select Hotel").assignAuthor("Tshifhiwa");
        ExtentTest node = test.createNode("Validate hotel is selected successfully");

        try{
            functions.selectHotel(webUtilities.getWebDriver(), node);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test(priority = 4, dataProvider = "BookHotelData", dataProviderClass = DataProviders.class)
    public void testBookHotel(String FirstName, String LastName, String BillingAddress, String CreditCardNo, String CreditCardType,
                              String ExpMonth, String ExpYear, String CVV){

        ExtentTest test = reports.createTest("Book Hotel").assignAuthor("Tshifhiwa");
        ExtentTest node = test.createNode("Validate hotel is booked successfully");

        try{

            functions.bookHotel(webUtilities.getWebDriver(),
                    FirstName,
                    LastName,
                    BillingAddress,
                    CreditCardNo,
                    CreditCardType,
                    ExpMonth,
                    ExpYear,
                    CVV,
                    node);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 5)
    public void testLogout(){

        ExtentTest test = reports.createTest("Logout").assignAuthor("Tshifhiwa");
        ExtentTest node = test.createNode("Validate Title was displayed successfully");

        try{
            functions.logOut(webUtilities.getWebDriver(), node);
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
