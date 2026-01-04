package com.inmotion.utils;

import com.inmotion.core.BaseTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements AfterTestExecutionCallback {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void afterTestExecution(ExtensionContext context) {
        Object instance = context.getRequiredTestInstance();
        if (instance instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) instance;
            WebDriver driver = baseTest.getDriver();

            if (context.getExecutionException().isPresent()) {
                takeScreenshot(driver, context.getDisplayName());
            }

            if (driver != null) {
                driver.quit();
            }
        }
    }

    private void takeScreenshot(WebDriver driver, String testName) {
        if (driver instanceof TakesScreenshot) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String cleanTestName = testName.replaceAll("[^a-zA-Z0-9._-]", "_");
                Path dest = Paths.get("screenshots", cleanTestName + "_" + timestamp + ".png");
                Files.createDirectories(dest.getParent());
                Files.copy(screenshot.toPath(), dest);
                logger.info("Screenshot saved to {}", dest.toAbsolutePath());
            } catch (Exception e) {
                logger.error("Failed to save screenshot", e);
            }
        }
    }
}
