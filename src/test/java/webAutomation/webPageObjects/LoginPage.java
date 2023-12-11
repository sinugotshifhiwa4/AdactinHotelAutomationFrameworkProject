package webAutomation.webPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {

    protected WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//*[@id=\"username\"]")
    public WebElement txtUsernme;
    @FindBy(xpath = "//*[@id=\"password\"]")
    public WebElement txtPassword  ;
    @FindBy(xpath = "//*[@id=\"login\"]")
    public WebElement btnLogin;

    // validation xpath
    @FindBy(xpath = "//*[@id=\"username_show\"]")
    public WebElement txtWelcomeMessage;
}
