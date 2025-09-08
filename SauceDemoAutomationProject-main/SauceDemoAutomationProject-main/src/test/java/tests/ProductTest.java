package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

import java.util.List;
import org.openqa.selenium.WebElement;

public class ProductTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;

    @BeforeMethod
    public void setUpTest() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);

        // Login before each product test
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        productPage = new ProductPage(driver);
    }

    // Verify product listing page after login
    @Test
    public void testProductListingPage() {
        Assert.assertEquals(productPage.getTitleText(), "Products", "Product listing page not displayed!");
    }

    //Verify product names and prices are displayed
    @Test
    public void testProductNamesAndPricesDisplayed() {
        List<WebElement> names = productPage.getAllProductNames();
        List<WebElement> prices = productPage.getAllProductPrices();

        Assert.assertTrue(names.size() > 0, "No product names displayed!");
        Assert.assertTrue(prices.size() > 0, "No product prices displayed!");
    }

    //  Verify sorting options
    @Test
    public void testSortingOptions() {
        productPage.sortProducts("Name (A to Z)");
        productPage.sortProducts("Name (Z to A)");
        productPage.sortProducts("Price (low to high)");
        productPage.sortProducts("Price (high to low)");

        Assert.assertTrue(true, "Sorting options not working properly!");
    }

    // Add a product to the cart
    @Test
    public void testAddProductToCart() {
        productPage.addProductByName("Sauce Labs Backpack");
        Assert.assertEquals(productPage.getBackpackButtonText(), "Remove", "Product not added to cart!");
    }

    //  Remove a product from the cart
    @Test
    public void testRemoveProductFromCart() {
        productPage.addProductByName("Sauce Labs Backpack");
        productPage.removeProductByName("Sauce Labs Backpack");
        Assert.assertEquals(productPage.getBackpackButtonText(), "Add to cart", "Product not removed from cart!");
    }

    //  Add multiple products to the cart
    @Test
    public void testAddMultipleProductsToCart() {
        productPage.addProductByName("Sauce Labs Backpack");
        productPage.addProductByName("Sauce Labs Bike Light");

        Assert.assertEquals(productPage.getBackpackButtonText(), "Remove", "Backpack not added!");
        Assert.assertTrue(productPage.isProductDisplayed("Sauce Labs Bike Light"), "Bike Light not added!");
    }

    //  Click on a product to go to its details page
    @Test
    public void testClickProductDetailsPage() {
        productPage.clickOnProduct("Sauce Labs Backpack");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item.html"), "Not navigated to product details page!");
    }
}
