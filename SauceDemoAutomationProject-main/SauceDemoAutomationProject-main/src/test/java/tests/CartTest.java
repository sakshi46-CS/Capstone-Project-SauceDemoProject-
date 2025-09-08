package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;

public class CartTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUpTest() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    //Open cart and verify added items
    @Test
    public void testVerifyCartItems() {
        productPage.addProductByName("Sauce Labs Backpack");
        productPage.addProductByName("Sauce Labs Bike Light");
        productPage.clickCartIcon();

        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "Backpack not found in cart!");
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"), "Bike Light not found in cart!");
    }

    //  Remove item from the cart in cart page
    @Test
    public void testRemoveItemFromCart() {
        productPage.addProductByName("Sauce Labs Backpack");
        productPage.clickCartIcon();

        cartPage.removeProduct("Sauce Labs Backpack");
        Assert.assertFalse(cartPage.isProductInCart("Sauce Labs Backpack"), 
                           "Product was not removed from the cart!");

    }

    //  Continue shopping from the cart page
    @Test
    public void testContinueShoppingFromCart() {
        productPage.addProductByName("Sauce Labs Backpack");
        productPage.clickCartIcon();

        cartPage.clickContinueShopping();

        Assert.assertTrue(productPage.getTitleText().contains("Products"),
                "Did not navigate back to product listing page!");
    }

    //  Proceed to checkout from cart page
    @Test
    public void testProceedToCheckout() {
        productPage.addProductByName("Sauce Labs Backpack");
        productPage.clickCartIcon();

        cartPage.clickCheckout();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"),
                "Did not navigate to checkout page!");
    }
}
