package stepDefinitions;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class ProductSteps extends BaseTest {
    private LoginPage loginPage;
    private ProductPage productPage;

    @Given("user is logged in with {string} and {string}")
    public void user_is_logged_in_with_and(String username, String password) {
        setUp();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLogin();
        productPage = new ProductPage(driver);
    }

    @Then("the product page title should be {string}")
    public void the_product_page_title_should_be(String title) {
        Assert.assertEquals(productPage.getTitleText(), title);
     
    }

    @When("user adds a product to the cart")
    public void user_adds_a_product_to_the_cart() {
        productPage.addProductByName("Sauce Labs Backpack");
    }


    @When("user adds a product {string} to the cart")
    public void user_adds_a_product_to_the_cart(String productName) {
        productPage.addProductByName(productName); // Generic method → adds any product
    }

    @And("user clicks on the cart icon")
    public void user_clicks_on_the_cart_icon() {
        productPage.clickCartIcon();
    }
}
