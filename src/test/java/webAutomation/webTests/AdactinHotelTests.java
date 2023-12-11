package webAutomation.webTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webAutomation.webUtilities.WebUtilities;

public class AdactinHotelTests {


    WebUtilities webUtilities = new WebUtilities();


    String sUrl, sBrowser;


    @BeforeClass
    @Parameters({"adactinUrl", "Browser"})
    public void init(String adactinUrl, String Browser){

        sUrl = adactinUrl;
        sBrowser = Browser;

        webUtilities.setWebDriver(webUtilities.initializeWebDriver(sBrowser));
    }

    @Test
    public void runTests(){

        try{
            webUtilities.navigate(sUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {

        Thread.sleep(3000);
        webUtilities.getWebDriver().close();
        webUtilities.getWebDriver().quit();
    }
}
