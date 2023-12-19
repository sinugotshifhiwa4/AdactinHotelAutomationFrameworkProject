package webAutomation.webPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LogoutPage {

    protected WebDriver driver;
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()='Logout']\n")
    public WebElement logout;
    @FindBy(xpath = "//a[text()='Click here to login again']\n")
    public WebElement linkToGoLoginPage;

    //validate
    @FindBy(xpath = "//td[@colspan='2' and @class='login_title' and text()='Existing User Login - Build 1']\n")
    public WebElement existingUserLogin;

}
