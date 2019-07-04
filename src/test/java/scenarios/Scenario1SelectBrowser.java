package scenarios;
/* In this class, the browser name given in the Feature file is used parametrically. */

import com.aventstack.extentreports.GherkinKeyword;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.JavaScript;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.ExtentReportUtil;
import runner.Variables;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Scenario1SelectBrowser {
    static {
        //The language of Javanese is set in English
        Locale.setDefault(new Locale("en", "EN"));
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        Variables.scenarioDef = Variables.features.createNode(scenario.getName());
        String getFeatureName = processWords(scenario.getId());
        Variables.features.getModel().setName(getFeatureName);
    }

    @When("^I have opened \"([^\"]*)\" with \"([^\"]*)\" browser$")
    public void iHaveOpenedWithBrowser(String baseUrl, String browser) throws ClassNotFoundException {
        Variables.scenarioDef.createNode(new GherkinKeyword("When"), "I have opened " + baseUrl + " with " + browser + " browser");


        if (browser.toLowerCase().contains("chrome")) {
            WebDriverManager.chromedriver().arch32().setup();
            //For Decline Notification
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            Variables.driver = new ChromeDriver(options);
        } else if (browser.toLowerCase().contains("firefox")) {
            WebDriverManager.firefoxdriver().arch32().setup();
            Variables.driver = new FirefoxDriver();
        } else if (browser.toLowerCase().contains("explorer")) {
            WebDriverManager.iedriver().arch32().setup();
            Variables.driver = new InternetExplorerDriver();
        } else System.out.println("Enter a valid browser name(chrome,firefox,explorer)");
        Variables.driver.get(baseUrl);
        Variables.driver.manage().window().maximize();

        if (Variables.driver.findElement(By.id("popupContainer")).isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(Variables.driver, 5, 100);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='javascript:;']")));
            Variables.driver.findElement(By.xpath("//a[@href='javascript:;']")).click();
        }
        boolean mTextSearch1 = Variables.driver.getPageSource().contains("Sepetim");
        boolean mTextSearch2 = Variables.driver.getPageSource().contains("Favorilerim");
        if (!(mTextSearch1 || mTextSearch2)) {
            System.out.println("An error occurred, page failed to load!");
            System.exit(-1);
        }
    }

    //In this method, the feature name to be written to the dashboard is processed.
    public static String processWords(String sentence) {
        String replaceSentence = sentence.split(";")[0].replace("-", " ");
        String words[] = replaceSentence.replaceAll("\\s+", " ").trim().split(" ");
        String newSentence = "";
        for (String word : words) {
            for (int i = 0; i < word.length(); i++)
                newSentence = newSentence + ((i == 0) ? word.substring(i, i + 1).toUpperCase() :
                        (i != word.length() - 1) ? word.substring(i, i + 1).toLowerCase() :
                                word.substring(i, i + 1).toLowerCase().toLowerCase() + " ");
        }

        return newSentence;
    }

    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReportUtil extentReportUtil = new ExtentReportUtil();
            try {
                JavaScript.scrollUp();
                extentReportUtil.ExtentReportScreenShot();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(scenario.getName());
        }
    }
}