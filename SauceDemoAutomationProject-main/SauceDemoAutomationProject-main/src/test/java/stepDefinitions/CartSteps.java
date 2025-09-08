package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.CartPage;

public class CartSteps {

    private CartPage cartPage;

    @Then("cart page should be displayed")
    public void cart_page_should_be_displayed() {
        cartPage = new CartPage(DriverFactory.getDriver());
        Assert.assertEquals(cartPage.getCartTitle(), "Your Cart", "Cart page not displayed");
    }

    @Then("the cart should contain the product {string}")
    public void the_cart_should_contain_the_product(String productName) {
        Assert.assertTrue(cartPage.isProductInCart(productName), "Product not found in cart: " + productName);
    }

    @Then("the cart badge count should be {string}")
    public void the_cart_badge_count_should_be(String expectedCount) {
        Assert.assertEquals(cartPage.getCartBadgeCount(), expectedCount, "Cart badge mismatch");
    }

    @When("user removes product {string} from cart")
    public void user_removes_product_from_cart(String productName) {
        cartPage.removeProduct(productName);
    }

    @When("user clicks checkout button in cart")
    public void user_clicks_checkout_button_in_cart() {
        cartPage.clickCheckout();
    }
}
