package utility;
/* This class contains methods of image controls. */

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import runner.Variables;

import java.util.List;
import java.util.stream.IntStream;

public class ImageControl {
    public static final String mSrcInCorrectImageLink = "https://static.dsmcdn.com/frontend/web/production/large_boutique_placeholder.png";
    private String mSrc;
    private String mAlt;
    private String mProductBrand;
    private String mProductName;

    public void controlBoutiqueImage(List<WebElement> classes, Integer count, String page) {
        IntStream.range(0, count).forEach(i -> {
            mSrc = classes.get(i).findElement(By.tagName("img")).getAttribute("src");
            if (mSrc.equals(mSrcInCorrectImageLink) || mSrc.equals("")) {
                mAlt = classes.get(i).findElement(By.tagName("img")).getAttribute("alt");
                System.out.println("Boutique image failed to load properly in " + page + " page. Boutique title information : " + mAlt);
            }
        });
    }

    public void checkPage(String pageName, String activeTab) {
        String currentUrl = Variables.driver.getCurrentUrl();
        WebElement mActiveTab = Variables.driver.findElement(By.cssSelector(".tab-link.active"));
        String mActiveTabText = mActiveTab.findElement(By.tagName("a")).getText().toLowerCase();
        try {
            Assert.assertTrue(currentUrl.contains(pageName.toLowerCase()));
            Assert.assertTrue(mActiveTabText.equals(activeTab.toLowerCase()));
        } catch (AssertionError ae) {
            Assert.fail("\n" + "Related page not loaded !!" + "\n" + ae);
        }
    }

    public void controlProductImage(List<WebElement> classes, Integer productCount) {
        List<WebElement> mProductBrandList = Variables.driver.findElements(By.className("brand"));
        List<WebElement> mProductNameList = Variables.driver.findElements(By.className("name"));
        WebElement mBoutiqueTitle = Variables.driver.findElement(By.className("boutique-title"));
        String mBoutiqueTitleText = mBoutiqueTitle.getText();

        IntStream.range(0, productCount).forEach(i -> {
            mSrc = classes.get(i).getAttribute("src");
            if (mSrc.equals("")) {
                mProductBrand = mProductBrandList.get(i).getText();
                mProductName = mProductNameList.get(i).getText();
                System.out.println("Product image failed to load properly in " + mBoutiqueTitleText
                        + " page. Product brand: " + mProductBrand
                        + "and Product name: " + mProductName);
            }
        });
    }
}
