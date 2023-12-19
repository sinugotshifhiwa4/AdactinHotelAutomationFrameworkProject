package webAutomation.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import webAutomation.reports.ExtentReport;
import webAutomation.webPageObjects.*;
import webAutomation.webUtilities.FileGenerator;
import webAutomation.webUtilities.WebActions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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

    public void selectHotel(WebDriver driver, ExtentTest node){

        SelectHotelPage selectHotelPage = new SelectHotelPage(driver);

        try{

            clickObjects(selectHotelPage.selectHotelRadioBtn, driver);
            clickObjects(selectHotelPage.continueBtn, driver);

            Thread.sleep(2000);

            String filename = report.CaptureScreenShot(driver);

            //Validation
            if (selectHotelPage.bookHotelTitle.isDisplayed()) {
                node.pass("Select Hotel was Successful", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
            } else {
                node.fail("Select Hotel was Unsuccessful", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
                Assert.fail("Select Hotel was Unsuccessful");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void bookHotel(WebDriver driver, String FirstName, String LastName, String BillingAddress, String CreditCardNo, String CreditCardType,
                          String ExpMonth, String ExpYear, String CVV, ExtentTest node){

        BookHotelPage bookHotelPage = new BookHotelPage(driver);
        FileGenerator generator = new FileGenerator();

        try{

            passData(bookHotelPage.firstName, driver, FirstName);
            passData(bookHotelPage.lastName, driver, LastName);
            passData(bookHotelPage.billingAddress, driver, BillingAddress);
            passData(bookHotelPage.creditCardNumber, driver, CreditCardNo);
            selectObjects(bookHotelPage.creditCardType, driver, "SELECTBYVISIBLETEXT", CreditCardType);
            selectObjects(bookHotelPage.expMonth, driver, "SELECTBYVISIBLETEXT", ExpMonth);
            selectObjects(bookHotelPage.expYear, driver, "SELECTBYVISIBLETEXT", ExpYear);
            passData(bookHotelPage.cvvNumber, driver, CVV);

            clickObjects(bookHotelPage.bookNowBtn, driver);

            //Thread.sleep(4000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(bookHotelPage.orderNumberCreated));

            String filename = report.CaptureScreenShot(driver);

            WebElement orderNumber = bookHotelPage.orderNumberCreated;
            String _orderNumber = orderNumber.getAttribute("value");
            generator.writeToFile(_orderNumber);

            //Validation
            if (bookHotelPage.orderNumberCreated.isDisplayed() && !_orderNumber.isEmpty()) {
                node.pass("Booking was Successful." + " " + "Confirmation Oder Number is: " + _orderNumber, MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());

            } else {
                node.fail("Booking was Unsuccessful", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
                Assert.fail("Booking was Unsuccessful");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void logOut(WebDriver driver, ExtentTest node){

        LogoutPage logoutPage = new LogoutPage(driver);

        try{
            Thread.sleep(2000);
            clickObjects(logoutPage.logout, driver);
            Thread.sleep(2000);
            clickObjects(logoutPage.linkToGoLoginPage, driver);

            Thread.sleep(2000);

            String filename = report.CaptureScreenShot(driver);


            //Validation
            if (logoutPage.existingUserLogin.isDisplayed()) {
                node.pass("Logout successful", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());

            } else {
                node.fail("Logout was Unsuccessful", MediaEntityBuilder.createScreenCaptureFromBase64String(filename).build());
                Assert.fail("Logout was Unsuccessful");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
