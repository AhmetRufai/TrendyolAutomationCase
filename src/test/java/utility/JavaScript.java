package utility;
/* This class contains methods for executing javascript commands via selenium. */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import runner.Variables;

public class JavaScript {

    public static void clickID(String id) {
        WebElement myElement = Variables.driver.findElement(By.id(id));
        JavascriptExecutor mJavascriptExecute = (JavascriptExecutor) Variables.driver;
        mJavascriptExecute.executeScript("document.getElementById('" + id + "').click()", myElement);
    }

    public static void setValue(String id) {
        WebElement myElement = Variables.driver.findElement(By.id(id));
        JavascriptExecutor mJavascriptExecute = (JavascriptExecutor) Variables.driver;
        mJavascriptExecute.executeScript("document.getElementById('" + id + "').setAttribute('value','true')", myElement);
        mJavascriptExecute.executeScript("document.getElementById('" + id + "').click()", myElement);
    }

    public static void clickCssSelector(String cssSelector) {
        WebElement myElement = Variables.driver.findElement(By.cssSelector(cssSelector));
        JavascriptExecutor mJavascriptExecute = (JavascriptExecutor) Variables.driver;
        mJavascriptExecute.executeScript("document.querySelector('" + cssSelector + "').click()", myElement);
    }

    public static void scrollDown() {
        JavascriptExecutor mJavascriptExecute = (JavascriptExecutor) Variables.driver;
        mJavascriptExecute.executeScript("window.scrollTo(document.body.scrollHeight,5000)");
    }

    public static void scrollUp() {
        JavascriptExecutor mJavascriptExecute = (JavascriptExecutor) Variables.driver;
        mJavascriptExecute.executeScript("window.scrollTo(document.body.scrollHeight,-5000)");
    }

    public static void clickClassName(String className, Integer Index) {
        JavascriptExecutor mJavascriptExecute = (JavascriptExecutor) Variables.driver;
        mJavascriptExecute.executeScript("document.getElementsByClassName('" + className + "')[" + Index + "].click()");
    }

}
