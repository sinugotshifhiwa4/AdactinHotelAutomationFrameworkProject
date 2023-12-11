package webAutomation.webUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WebActions {


    public void clickObjects(WebElement element, WebDriver driver){

        try{

            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5000))
                    .pollingEvery(Duration.ofMillis(1000))
                    .ignoring(WebDriverException.class);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();

        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + "Not Found");
        }
    }

    public void passData(WebElement element, WebDriver driver, String passValue){

        try{

            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5000))
                    .pollingEvery(Duration.ofMillis(1000))
                    .ignoring(WebDriverException.class);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(passValue);

        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + "Not Found");
        }
    }

    public void selectObjects(WebElement element, WebDriver driver, String selectMethod, Object vValue){

        Select select = new Select(element);

        try{

            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5000))
                    .pollingEvery(Duration.ofMillis(1000))
                    .ignoring(WebDriverException.class);
            wait.until(ExpectedConditions.elementToBeClickable(element));

            switch (selectMethod.toUpperCase()){

                case "SELECTBYINDEX":
                    select.selectByIndex((Integer) vValue);
                    break;

                case "SELECTBYVALUE":
                    select.selectByValue((String) vValue);
                    break;

                case "SELECTBYVISIBLETEXT":
                    select.selectByVisibleText((String) vValue);
                    break;
            }


        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + "Not Found");
        }
    }

}