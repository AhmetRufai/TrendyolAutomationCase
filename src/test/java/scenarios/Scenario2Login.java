package scenarios;
/* In this class, the site is accessed. If the user is not registered, the user is registered to the site. */

import com.aventstack.extentreports.GherkinKeyword;
import cucumber.api.java.en.But;
import cucumber.api.java.en.When;
import utility.JavaScript;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.Variables;

public class Scenario2Login {
    private WebDriverWait wait = new WebDriverWait(Variables.driver, 5, 100);
    private JavaScript javaScript = new JavaScript();
    private boolean mIsMembers = false;

    // In this method, the site is accessed.
    @When("^sign in as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void signInAsAnd(String eMailAddress, String password) throws ClassNotFoundException {

        String mCheckLogin = Variables.driver.findElement(By.id("logged-in-container")).getAttribute("class");

        if (mCheckLogin.equals("hidden-container")) {
            Variables.scenarioDef.createNode(new GherkinKeyword("When"), "Sign in as " + eMailAddress + " and " + password + "");
            javaScript.clickID("not-logged-in-container");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            Variables.driver.findElement(By.id("email")).sendKeys(eMailAddress);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            Variables.driver.findElement(By.id("password")).sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(By.id("loginSubmit")));
            Variables.driver.findElement(By.id("loginSubmit")).click();
            boolean mIsErrorBox = Variables.driver.findElement(By.id("errorBox")).isDisplayed();
            if (!mIsErrorBox) {
                wait.until(ExpectedConditions.elementToBeClickable(By.id("logged-in-container")));
                mCheckLogin = Variables.driver.findElement(By.id("logged-in-container")).getAttribute("class");
                if (mCheckLogin.equals("")) {
                    System.out.println("Login Successful");
                    mIsMembers = true;
                } else {
                    Assert.fail("Login Unsuccessful!!!");
                }
            }
        }
    }

    //If the user is not registered, the user is registered to the site.
    @But("^sign up with \"([^\"]*)\" and \"([^\"]*)\" if I'm not registered$")
    public void signUpWithAndIfIMNotRegistered(String eMailAddress, String password) throws ClassNotFoundException {
        if (!mIsMembers) {
            Variables.scenarioDef.createNode(new GherkinKeyword("But"), "Sign up if I'm not registered");
            String mSingUpCssSelectorPath = "#foorterMain > a";
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(mSingUpCssSelectorPath)));
            Variables.driver.findElement(By.cssSelector(mSingUpCssSelectorPath)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            Variables.driver.findElement(By.id("email")).sendKeys(eMailAddress);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            Variables.driver.findElement(By.id("password")).sendKeys(password);
            boolean mCheckBoxSelected = Variables.driver.findElement(By.id("newTermsOfUse")).isSelected();
            if (!mCheckBoxSelected) {
                javaScript.setValue("MemberShipPolicy");
            }
            wait.until(ExpectedConditions.elementToBeClickable(By.id("registerSubmit")));
            Variables.driver.findElement(By.id("registerSubmit")).click();
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("smiling-face")));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("errorBox")));
                System.out.println("Sign Up Successful");
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='javascript:;']")));
                Variables.driver.findElement(By.xpath("//a[@href='javascript:;']")).click();
            } catch (TimeoutException e) {
                Assert.fail("Sign Up Unsuccessful!!!");
            }
        }
    }
}
