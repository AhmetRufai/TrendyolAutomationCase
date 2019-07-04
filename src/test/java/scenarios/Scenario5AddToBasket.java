package scenarios;
/* In this class, a random product is added to the basket. */

import com.aventstack.extentreports.GherkinKeyword;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.TimeoutException;
import utility.JavaScript;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.Variables;
import utility.Utility;

import java.util.List;

public class Scenario5AddToBasket {
    private WebDriverWait wait = new WebDriverWait(Variables.driver, 5, 100);

    @When("^go to detail of a random product$")
    public void goToDetailOfARandomProduct() throws ClassNotFoundException {
        Variables.scenarioDef.createNode(new GherkinKeyword("When"), "Go to detail of a random product");
        List<WebElement> mProductImageList = Variables.driver.findElements(By.className("prc-picture"));
        int mProductImageListCount = mProductImageList.size();
        int mRandomBoutique = Utility.generateRandomValue(0, mProductImageListCount - 1);
        mProductImageList.get(mRandomBoutique).click();
    }

    @And("^add to basket$")
    public void addToBasket() throws ClassNotFoundException {
        Variables.scenarioDef.createNode(new GherkinKeyword("And"), "Add to basket");
        if (!Variables.driver.findElements(By.className("pr-in-drp-u")).isEmpty()) {
            JavaScript.clickClassName("vrn-item", 0);
        }
        int beforeBasketItemCount = 0;
        try {
            // To handle basket element count when it has no item
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basketItemCount")));
            beforeBasketItemCount = Integer.parseInt(Variables.driver.findElement(By.id("basketItemCount")).getText());
        } catch (TimeoutException e) {
        }

        Variables.driver.findElement(By.cssSelector(".pr-in-btn.add-to-bs")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pr-in-btn.add-to-bs.success")));
        int afterBasketItemCount = Integer.parseInt(Variables.driver.findElement(By.id("basketItemCount")).getText());

        if (afterBasketItemCount == beforeBasketItemCount + 1) {
            System.out.println("Product successfully added to basket");
        } else {
            Assert.fail("Product failed to add to the basket successfully!!!");
        }

    }
}
