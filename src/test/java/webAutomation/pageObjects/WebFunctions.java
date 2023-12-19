package webAutomation.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import webAutomation.reports.ExtentReport;
import webAutomation.webPageObjects.LoginPage;
import webAutomation.webPageObjects.SearchHotelPage;
import webAutomation.webUtilities.WebActions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public void searchHotel(WebDriver driver, String Location, String Hotels, String RoomType, String NumberOfRooms, String CheckInDate, String CheckOutDate,
                            String AdultPerRoom, String ChildrenPerRoom, ExtentTest node){

        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);


        try{

            selectObjects(searchHotelPage.location, driver, "SELECTBYVISIBLETEXT", Location);
            selectObjects(searchHotelPage.hotels, driver, "SELECTBYVISIBLETEXT", Hotels);
            selectObjects(searchHotelPage.roomType, driver, "SELECTBYVISIBLETEXT", RoomType);
            selectObjects(searchHotelPage.numberOfRooms, driver, "SELECTBYVISIBLETEXT", NumberOfRooms);
            passData(searchHotelPage.checkInDate, driver, CheckInDate);
            passData(searchHotelPage.checkOutDate, driver, CheckOutDate);
            selectObjects(searchHotelPage.adultPerRoom, driver, "SELECTBYVISIBLETEXT", AdultPerRoom);
            selectObjects(searchHotelPage.childrenPerRoom, driver, "SELECTBYVISIBLETEXT", ChildrenPerRoom);
            clickObjects(searchHotelPage.searchBtn, driver);

            Thread.sleep(2000);

            String filename = report.CaptureScreenShot(driver);

            //Validation
            if (searchHotelPage.selectHotelTitle.isDisplayed()) {
                node.pass("Search Hotel was Successful", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
            } else {
                node.fail("Search Hotel was Unsuccessful", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
                Assert.fail("Search Hotel was Unsuccessful");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
