package scenarios;
/* In this class, product pictures are checked. */

import com.aventstack.extentreports.GherkinKeyword;
import cucumber.api.java.en.When;
import utility.ImageControl;
import utility.JavaScript;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import runner.Variables;
import utility.Utility;

import java.util.List;

public class Scenario4CheckProductImage {
    @When("^check product image on random boutique$")
    public void checkProductImageOnRandomBoutique() throws ClassNotFoundException {
        ImageControl imageControl = new ImageControl();
        Variables.scenarioDef.createNode(new GherkinKeyword("When"), "Check product images on random boutique");
        int randomTab = Utility.generateRandomValue(1, 9);
        JavaScript.clickCssSelector("#navigation-wrapper > nav > ul > li:nth-child(" + randomTab + ") > a");
        WebElement mActiveTab = Variables.driver.findElement(By.cssSelector(".tab-link.active"));
        String mActiveTabText = mActiveTab.findElement(By.tagName("a")).getText();
        System.out.println("generateRandomValue Tab: " + mActiveTabText);
        JavaScript.scrollDown();
        List<WebElement> mBoutiqueList = Variables.driver.findElements(By.cssSelector(".campaign.campaign-big"));
        int mBoutiqueListCount = mBoutiqueList.size();
        int mRandomBoutique = Utility.generateRandomValue(0, mBoutiqueListCount - 1);
        mBoutiqueList.get(mRandomBoutique).click();
        JavaScript.scrollDown();
        List<WebElement> mProductImageList = Variables.driver.findElements(By.className("prc-picture"));
        int mProductImageListCount = mProductImageList.size();
        imageControl.controlProductImage(mProductImageList, mProductImageListCount);
    }

}
