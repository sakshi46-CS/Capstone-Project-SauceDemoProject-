package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class UITest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;

    @BeforeMethod
    public void setUpTest() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);

        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        productPage = new ProductPage(driver);
    }

    // TC026: Verify presence of burger menu
    @Test
    public void testBurgerMenuPresence() {
        Assert.assertTrue(productPage.isBurgerMenuVisible(), "Burger menu is not visible!");
    }

    // TC027: Logout from burger menu
    @Test
    public void testLogoutFromBurgerMenu() {
        productPage.clickLogout();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
                "User was not redirected to login page!");
    }

    // TC028: Navigate to About from burger menu
    @Test
    public void testNavigateToAbout() {
        productPage.clickAbout();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucelabs.com"),
                "About link did not redirect to Sauce Labs site!");
    }

    // TC029: Reset app state from burger menu
    @Test
    public void testResetAppState() {
        productPage.addProductByName("Sauce Labs Backpack");
        productPage.clickCartIcon();
        Assert.assertTrue(driver.getPageSource().contains("Sauce Labs Backpack"),
                "Product not added to cart!");

        driver.navigate().back(); // back to products
        productPage.clickResetAppState();
        productPage.clickCartIcon();

        Assert.assertFalse(driver.getPageSource().contains("Sauce Labs Backpack"),
                "Cart is not empty after Reset App State!");
    }

    // TC030: Verify footer links and icons
    @Test
    public void testFooterLinksAndIcons() {
        Assert.assertTrue(productPage.isTwitterIconVisible(), "Twitter icon not visible!");
        Assert.assertTrue(productPage.isFacebookIconVisible(), "Facebook icon not visible!");
        Assert.assertTrue(productPage.isLinkedInIconVisible(), "LinkedIn icon not visible!");
        Assert.assertTrue(productPage.getFooterText().contains("Sauce Labs"),
                "Footer text is incorrect!");
    }
}
