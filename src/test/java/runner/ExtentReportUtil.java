package runner;
/*This class is used for the dashboard to show the test results*/

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class ExtentReportUtil {
    public ExtentReports extent;

    public void ExtentReport() {
        String fileName = Variables.reportLocation + Variables.getDate() + ".html";
        extent = new ExtentReports();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Test Report for Trendyol Case");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Test Report");
        extent.attachReporter(htmlReporter);
    }

    public void ExtentReportScreenShot() throws IOException {
        File scr = ((TakesScreenshot) Variables.driver).getScreenshotAs(OutputType.FILE);
        String fileName = Variables.reportLocation + Variables.getDate() + ".png";
        Files.copy(scr.toPath(), new File(fileName).toPath());
        Variables.scenarioDef.fail("details").addScreenCaptureFromPath(fileName);
    }

    public void FlushReport() {
        extent.flush();
    }
}
