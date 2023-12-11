package webAutomation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ExtentReport {


    ExtentSparkReporter spark;

    ExtentReports extent;

    public ExtentReports initilizeExtentReporters(String ReportName){

        spark = new ExtentSparkReporter(ReportName);

        extent = new ExtentReports();

        extent.attachReporter(spark);

        return extent;

    }

    public String CaptureScreenShot(WebDriver driver) throws IOException {
        File sFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte [] fileContent = FileUtils.readFileToByteArray(new File(String.valueOf(sFile)));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }
}
