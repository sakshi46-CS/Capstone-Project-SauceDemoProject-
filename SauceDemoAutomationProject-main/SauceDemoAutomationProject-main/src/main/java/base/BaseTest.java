package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    //runs before every test method
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
