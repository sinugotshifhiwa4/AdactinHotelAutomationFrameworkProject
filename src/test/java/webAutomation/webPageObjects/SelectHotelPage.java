package webAutomation.webPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SelectHotelPage {

    protected WebDriver driver;

    public SelectHotelPage(WebDriver driver){

        this.driver = driver;

        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//*[@id=\"radiobutton_0\"]")
    public WebElement selectHotelRadioBtn;

    @FindBy(xpath = "//*[@id=\"continue\"]")
    public WebElement continueBtn;

    @FindBy(xpath = "//td[contains(@class, 'login_title') and contains(text(), 'Book A Hotel')]\n")
    public WebElement bookHotelTitle;
}
