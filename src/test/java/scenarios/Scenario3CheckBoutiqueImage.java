package scenarios;
/* In this class, all the tabs are visited and boutique pictures are checked. */

import com.aventstack.extentreports.GherkinKeyword;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import utility.ImageControl;
import runner.Variables;
import utility.JavaScript;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Scenario3CheckBoutiqueImage extends JavaScript {
    private Integer mCount;
    private String mPageName;
    private String mActiveTab;
    private ImageControl imageControl = new ImageControl();


    private void checkImage(String pageName, String activeTab, int index) throws ClassNotFoundException {
        Variables.scenarioDef.createNode(new GherkinKeyword("When"), "Check boutique images on " + pageName.toUpperCase() + " tab");
        mPageName = pageName;
        mActiveTab = activeTab;
        JavaScript.clickCssSelector("#navigation-wrapper > nav > ul > li:nth-child(" + index + ") > a");
        imageControl.checkPage(mPageName, mActiveTab);
        JavaScript.scrollDown(); //Scroll down to upload images.
        List<WebElement> webElements = Variables.driver.findElements(By.className("image-container"));
        mCount = webElements.size();
        imageControl.controlBoutiqueImage(webElements, mCount, mActiveTab);
    }

    @When("^check boutique images on KADIN tab$")
    public void checkBoutiqueImagesOnKADINTab() throws ClassNotFoundException {
        checkImage("kadin", "kadin", 1);
    }

    @And("^check boutique images on ERKEK tab$")
    public void checkBoutiqueImagesOnERKEKTab() throws ClassNotFoundException {
        checkImage("erkek", "erkek", 2);
    }

    @And("^check boutique images on COCUK tab$")
    public void checkBoutiqueImagesOnCOCUKTab() throws ClassNotFoundException {
        checkImage("cocuk", "çocuk", 3);
    }

    @And("^check boutique images on AYAKKABI/CANTA tab$")
    public void checkBoutiqueImagesOnAYAKKABICANTATab() throws ClassNotFoundException {
        checkImage("ayakkabi--canta", "AYAKKABI & ÇANTA", 4);
    }

    @And("^check boutique images on SAAT/AKSESUAR tab$")
    public void checkBoutiqueImagesOnSAATAKSESUARTab() throws ClassNotFoundException {
        checkImage("saat--aksesuar", "SAAT & AKSESUAR", 5);
    }

    @And("^check boutique images on KOZMETIK tab$")
    public void checkBoutiqueImagesOnKOZMETIKTab() throws ClassNotFoundException {
        checkImage("kozmetik", "KOZMETİK", 6);
    }

    @And("^check boutique images on EV/YASAM tab$")
    public void checkBoutiqueImagesOnEVYASAMTab() throws ClassNotFoundException {
        checkImage("ev--yasam", "EV & YAŞAM", 7);

    }

    @And("^check boutique images on ELEKTRONIK tab$")
    public void checkBoutiqueImagesOnELEKTRONIKTab() throws ClassNotFoundException {
        checkImage("elektronik", "ELEKTRONİK", 8);
    }

    @And("^check boutique images on SUPERMARKET tab$")
    public void checkBoutiqueImagesOnSUPERMARKETTab() throws ClassNotFoundException {
        checkImage("supermarket", "SÜPERMARKET", 9);
    }
}
