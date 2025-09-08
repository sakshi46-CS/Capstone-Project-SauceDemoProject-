package stepDefinitions;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LoginSteps extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        setUp();   // from BaseTest (launch browser)
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @When("user enters {string} and {string}")
    public void user_enters_and(String username, String password) {
        loginPage.setUsername(username);
        loginPage.setPassword(password);
    }

    @When("clicks login button")
    public void clicks_login_button() {
        loginPage.clickLogin();
    }

    @Then("user should see the product page")
    public void user_should_see_the_product_page() {
        productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getTitleText(), "Products");
        tearDown();
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
        tearDown();
    }
}
