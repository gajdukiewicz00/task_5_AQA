package com.inmotion.core;

import com.inmotion.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    public static WebDriver createDriver() {
        logger.info("Initializing ChromeDriver...");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");

        if (TestConfig.headless()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=" + TestConfig.windowWidth() + "," + TestConfig.windowHeight());
        } else {
            options.addArguments("--start-maximized");
        }

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestConfig.pageLoadSeconds()));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(TestConfig.scriptSeconds()));

        return driver;
    }
}
