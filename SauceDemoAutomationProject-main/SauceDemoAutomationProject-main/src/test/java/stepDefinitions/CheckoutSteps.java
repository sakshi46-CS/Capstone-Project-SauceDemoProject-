package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutSteps {

    WebDriver driver = DriverFactory.getDriver();

    @Given("user adds {string} to the cart")
    public void user_adds_to_the_cart(String productName) {
        driver.findElement(By.xpath(
            "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button"
        )).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
    }

    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstName, String lastName, String postalCode) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
    }

    @When("clicks continue")
    public void clicks_continue() {
        driver.findElement(By.id("continue")).click();
    }

    @When("clicks finish")
    public void clicks_finish() {
        driver.findElement(By.id("finish")).click();
    }

    @When("user cancels checkout")
    public void user_cancels_checkout() {
        driver.findElement(By.id("cancel")).click();
    }

    @Then("user should be redirected back to cart page")
    public void user_should_be_redirected_back_to_cart_page() {
        String title = driver.findElement(By.className("title")).getText();
        Assert.assertEquals(title, "Your Cart", "User is not redirected to Cart Page");
    }

    @Then("error message {string} should be displayed")
    public void error_message_should_be_displayed(String expectedError) {
        String actualError = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        Assert.assertEquals(actualError, expectedError, "Error message mismatch!");
    }

    @Then("order summary should display product {string}")
    public void order_summary_should_display_product(String expectedProduct) {
        String actualProduct = driver.findElement(By.className("inventory_item_name")).getText();
        Assert.assertEquals(actualProduct, expectedProduct, "Product in order summary does not match!");
    }

    @Then("order success message {string} should be displayed")
    public void order_success_message_should_be_displayed(String expectedMessage) {
        String actualMsg = driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(actualMsg, expectedMessage, "Order success message mismatch!");
    }
}
