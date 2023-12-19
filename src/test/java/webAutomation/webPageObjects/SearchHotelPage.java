package webAutomation.webPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchHotelPage {


    protected WebDriver driver;
    public SearchHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//*[@id=\"location\"]")
    public WebElement location;

    @FindBy(xpath = "//*[@id=\"hotels\"]")
    public WebElement hotels;

    @FindBy(xpath = "//*[@id=\"room_type\"]")
    public WebElement roomType;

    @FindBy(xpath = "//*[@id=\"room_nos\"]")
    public WebElement numberOfRooms;

    @FindBy(xpath = "//*[@id=\"datepick_in\"]")
    public WebElement checkInDate;

    @FindBy(xpath = "//*[@id=\"datepick_out\"]")
    public WebElement checkOutDate;

    @FindBy(xpath = "//*[@id=\"adult_room\"]")
    public WebElement adultPerRoom;

    @FindBy(xpath = "//*[@id=\"child_room\"]")
    public WebElement childrenPerRoom;

    @FindBy(xpath = "//*[@id=\"Submit\"]")
    public WebElement searchBtn;


    // validation xpath
    @FindBy(xpath = "//td[@class='login_title']\n")
    public WebElement selectHotelTitle;
}
