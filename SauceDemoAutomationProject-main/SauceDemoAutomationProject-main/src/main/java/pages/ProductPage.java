package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // 🔹 Common Locators
    private By title = By.className("title");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By cartIcon = By.id("shopping_cart_container");
    private By sortDropdown = By.className("product_sort_container");

    // 🔹 Burger menu & sidebar
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");
    private By resetAppStateLink = By.id("reset_sidebar_link");
    private By closeMenuBtn = By.id("react-burger-cross-btn");
    private By menuPanel = By.className("bm-menu");

    // 🔹 Footer links
    private By twitterIcon = By.cssSelector("a[href*='twitter']");
    private By facebookIcon = By.cssSelector("a[href*='facebook']");
    private By linkedInIcon = By.cssSelector("a[href*='linkedin']");
    private By footerText = By.className("footer_copy");

    // 🔹 Backpack-specific locators
    private By backpackPrice = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']");
    private By backpackImage = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//img");
    private By backpackButton = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // 🔹 Page Actions
    public String getTitleText() {
        return driver.findElement(title).getText();
    }

    public List<WebElement> getAllProductNames() {
        return driver.findElements(productNames);
    }

    public List<WebElement> getAllProductPrices() {
        return driver.findElements(productPrices);
    }

    public void addProductByName(String productName) {
        List<WebElement> products = driver.findElements(productNames);
        for (WebElement product : products) {
            if (product.getText().equalsIgnoreCase(productName)) {
                product.findElement(By.xpath("./ancestor::div[@class='inventory_item']//button")).click();
                break;
            }
        }
    }

    public void removeProductByName(String productName) {
        List<WebElement> products = driver.findElements(productNames);
        for (WebElement product : products) {
            if (product.getText().equalsIgnoreCase(productName)) {
                WebElement button = product.findElement(By.xpath("./ancestor::div[@class='inventory_item']//button"));
                if (button.getText().equalsIgnoreCase("Remove")) {
                    button.click();
                }
                break;
            }
        }
    }

    public void clickOnProduct(String productName) {
        List<WebElement> products = driver.findElements(productNames);
        for (WebElement product : products) {
            if (product.getText().equalsIgnoreCase(productName)) {
                product.click();
                break;
            }
        }
    }

    public void clickCartIcon() {
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cart.click();
    }

    public void sortProducts(String option) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        new Select(dropdown).selectByVisibleText(option);
    }

    public boolean isProductDisplayed(String productName) {
        return driver.findElements(By.xpath("//div[text()='" + productName + "']")).size() > 0;
    }

    // ---------- Backpack specific ----------
    public String getBackpackPrice() {
        return driver.findElement(backpackPrice).getText();
    }

    public boolean isBackpackImageVisible() {
        return driver.findElement(backpackImage).isDisplayed();
    }

    public String getBackpackButtonText() {
        return driver.findElement(backpackButton).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(backpackButton).click();
    }

    // ---------- Burger Menu ----------
    public boolean isBurgerMenuVisible() {
        return driver.findElement(menuButton).isDisplayed();
    }

    public void openBurgerMenu() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        btn.click();

        //  Wait until the sidebar menu is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuPanel));
    }

    public void closeBurgerMenu() {
        if (!isMenuOpen()) return;
        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(closeMenuBtn));
        closeBtn.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(menuPanel));
    }

    private boolean isMenuOpen() {
        try {
            return driver.findElement(menuPanel).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        openBurgerMenu();
        WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }

    public void clickAbout() {
        openBurgerMenu();
        WebElement about = wait.until(ExpectedConditions.elementToBeClickable(aboutLink));
        try {
            about.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", about);
        }
    }


    public void clickResetAppState() {
        openBurgerMenu();
        WebElement reset = wait.until(ExpectedConditions.elementToBeClickable(resetAppStateLink));
        reset.click();
    }

    // ---------- Footer ----------
    public boolean isTwitterIconVisible() {
        return !driver.findElements(twitterIcon).isEmpty();
    }

    public boolean isFacebookIconVisible() {
        return !driver.findElements(facebookIcon).isEmpty();
    }

    public boolean isLinkedInIconVisible() {
        return !driver.findElements(linkedInIcon).isEmpty();
    }

    public String getFooterText() {
        return driver.findElement(footerText).getText();
    }
}
