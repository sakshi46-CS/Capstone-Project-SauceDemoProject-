package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;

public class CheckoutTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUpTest() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        productPage = new ProductPage(driver);
        productPage.addProductByName("Sauce Labs Backpack");
        productPage.clickCartIcon();

        cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        checkoutPage = new CheckoutPage(driver);
    }

    //  Complete checkout with valid data
    @Test
    public void testCompleteCheckoutValidData() {
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("560001");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        Assert.assertTrue(checkoutPage.isOrderComplete(), "Order was not completed!");
    }

    //  Invalid/empty first name
    @Test
    public void testEmptyFirstName() {
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("560001");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getPageSource().contains("Error"), "Error message not displayed for empty first name!");
    }

    // Invalid/empty postal code
    @Test
    public void testEmptyPostalCode() {
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getPageSource().contains("Error"), "Error message not displayed for empty postal code!");
    }


    // Cancel checkout 
    @Test
    public void testCancelCheckout() {
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("560001");
        checkoutPage.clickContinue();

        checkoutPage.clickCancel();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("cart") || currentUrl.contains("inventory"),
                          "Cancel checkout did not return to cart or products page! Actual URL: " + currentUrl);
    }



    // Order summary validation
    @Test
    public void testOrderSummaryValidation() {
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("560001");
        checkoutPage.clickContinue();

        Assert.assertTrue(driver.getPageSource().contains("Sauce Labs Backpack"), "Product missing in order summary!");
        Assert.assertTrue(driver.getPageSource().contains("$"), "Price or tax not displayed!");
    }

    // Order confirmation page validation
    @Test
    public void testOrderConfirmationPage() {
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("560001");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        Assert.assertEquals(checkoutPage.getOrderCompleteText(), "Thank you for your order!");
    }
    
    //Back home from the checkout complete page
    @Test
    public void testBackHomeFromCheckoutComplete() {
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("560001");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        checkoutPage.clickBackHome(); // you need to add this in CheckoutPage
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), 
                          "Back Home did not return to Products page!");
    }
}
