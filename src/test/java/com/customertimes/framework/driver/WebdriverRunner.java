package com.customertimes.framework.driver;

import com.customertimes.framework.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebdriverRunner {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebdriverRunner() {
    }

    public static WebDriver getWebDriver() {
        if (driver.get() == null) {
            switch (TestConfig.CONFIG.browser()) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                }
                case "opera": {
                    WebDriverManager.operadriver().setup();
                    driver.set(new OperaDriver());
                    break;
                    }
                case "edge": {
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;
                }
                case  "safari": {
                    driver.set(new SafariDriver());
                    break;
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                }
            }
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void closeWebDriver() {
        if (driver.get() != null) {
            driver.get().close();
            driver.remove();
        }
    }
}
