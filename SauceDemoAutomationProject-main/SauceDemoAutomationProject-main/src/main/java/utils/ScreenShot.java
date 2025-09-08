package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

public class ScreenShot {
    public static void main(String args[]){
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            

            // perform login
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            
         // Login Page
            takeScreenshot(driver, "LoginPage");

            //  Product Page
            takeScreenshot(driver, "ProductPage");

         // Add product to cart 
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

            // Navigate to Cart Page
            driver.findElement(By.className("shopping_cart_link")).click();
            takeScreenshot(driver, "CartPage");

            // Wait for Checkout button & click
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
            driver.findElement(By.id("checkout")).click();
            takeScreenshot(driver, "CheckoutPage");

        } finally {
            driver.quit();
        }
    }

    // Reusable method
    public static void takeScreenshot(WebDriver driver, String pageName) {
        try {
            String folderPath = System.getProperty("user.dir") + "/screenshots/";
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(folderPath + pageName + "_" + timestamp + ".png");
            FileHandler.copy(src, dest);

            System.out.println("Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }
}
