package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;

    // Locators
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By orderCompleteMsg = By.className("complete-header");
    private By backHomeBtn = By.id("back-to-products");
    private By cancelBtn = By.id("cancel");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }

    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }

    public void clickFinish() {
        driver.findElement(finishBtn).click();
    }

    public boolean isOrderComplete() {
        return driver.findElements(orderCompleteMsg).size() > 0;
    }

    public String getOrderCompleteText() {
        return driver.findElement(orderCompleteMsg).getText();
    }
    
   

    public void clickBackHome() {
        driver.findElement(backHomeBtn).click();
    }
    
   

    public void clickCancel() {
        driver.findElement(cancelBtn).click();
    }


}
