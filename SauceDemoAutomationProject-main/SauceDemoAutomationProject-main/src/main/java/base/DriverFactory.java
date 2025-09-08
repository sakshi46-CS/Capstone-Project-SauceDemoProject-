package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        if (driver.get() == null) {
            System.out.println("[DriverFactory] Initializing ChromeDriver for thread: " + Thread.currentThread().getId());

            // Chrome Options
            ChromeOptions options = new ChromeOptions();

            // ✅ Allow popups
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.popups", 1);
            options.setExperimentalOption("prefs", prefs);

            // ✅ Useful arguments
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-infobars");

            // ✅ Experimental options (hide "automation" banner)
            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
            options.setExperimentalOption("useAutomationExtension", false);

            // Init ChromeDriver
            WebDriver drv = new ChromeDriver(options);
            drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.set(drv);
        } else {
            System.out.println("[DriverFactory] Reusing existing driver for thread: " + Thread.currentThread().getId());
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
