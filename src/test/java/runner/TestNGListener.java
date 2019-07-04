package runner;
/*This class is used to run TestNG Listener*/

import com.aventstack.extentreports.gherkin.model.Feature;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    ExtentReportUtil extentReportUtil = new ExtentReportUtil();

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("On Test Start...");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("On Test Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("On Test Failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("On Test Skipped");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("On Test Percentage");
    }

    @Override
    public void onStart(ITestContext context) {
        extentReportUtil.ExtentReport();
        Variables.features = extentReportUtil.extent.createTest(Feature.class, "Initial value of Feature file name");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("On Finish");
        extentReportUtil.FlushReport();
    }
}
