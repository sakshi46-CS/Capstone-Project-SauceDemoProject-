package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;

    @BeforeMethod
    public void setUpTest() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    //  Login with valid credentials (standard_user)
    @Test
    public void testValidLoginStandardUser() {
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getTitleText(), "Products");
    }

    //  Login with locked-out user
    @Test
    public void testLockedOutUser() {
        loginPage.setUsername("locked_out_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorText().contains("locked out"));
    }

    //  Login with problem user
    @Test
    public void testProblemUserLogin() {
        loginPage.setUsername("problem_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getTitleText(), "Products");
        // (Known bug: some product images may be broken)
    }

    // Login with performance glitch user
    @Test
    public void testPerformanceGlitchUserLogin() {
        loginPage.setUsername("performance_glitch_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getTitleText(), "Products");
    }

    //  Login with invalid credentials
    @Test
    public void testInvalidCredentials() {
        loginPage.setUsername("invalid_user");
        loginPage.setPassword("wrong_pass");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorText().contains("do not match"));
    }

    //  Login with blank username and password
    @Test
    public void testEmptyCredentials() {
        loginPage.setUsername("");
        loginPage.setPassword("");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorText().contains("Username is required"));
    }

    //  Verify error message for failed login
    @Test
    public void testErrorMessageText() {
        loginPage.setUsername("standard_user");
        loginPage.setPassword("wrong_pass");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorText().contains("Epic sadface"));
    }

    //Verify logout functionality
    @Test
    public void testLogoutFunctionality() {
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        productPage = new ProductPage(driver);
        productPage.clickLogout();

        //Verify that login button is displayed again
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
    }

}
