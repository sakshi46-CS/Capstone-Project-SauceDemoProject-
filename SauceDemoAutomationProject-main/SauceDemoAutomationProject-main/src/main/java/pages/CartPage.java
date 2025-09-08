package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By cartTitle = By.className("title");
    private By cartItems = By.cssSelector(".cart_item .inventory_item_name");
    private By checkoutBtn = By.id("checkout");
    private By continueShoppingBtn = By.id("continue-shopping");
    private By cartBadge = By.className("shopping_cart_badge");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Wait until cart page is visible
    public void waitForCartPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle));
    }

    public String getCartTitle() {
        waitForCartPage();
        return driver.findElement(cartTitle).getText().trim();
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> items = driver.findElements(cartItems); // no hard wait
        for (WebElement item : items) {
            String actualName = item.getText().trim();
            if (actualName.equalsIgnoreCase(productName.trim())) {
                return true;
            }
        }
        return false; // product not found
    }


    public void removeProduct(String productName) {
        WebElement item = driver.findElement(By.xpath(
                "//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button"
        ));
        item.click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingBtn).click();
    }

    public String getCartBadgeCount() {
        try {
            WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
            return badge.getText().trim();
        } catch (Exception e) {
            return "0"; // no badge displayed
        }
    }
}
