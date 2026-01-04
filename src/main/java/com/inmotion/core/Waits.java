package com.inmotion.core;

import com.inmotion.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    private final WebDriverWait wait;

    public Waits(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.timeoutSeconds()));
    }

    public WebElement visibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement visibilityOfElementLocated(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement elementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean urlContains(String fraction) {
        return wait.until(ExpectedConditions.urlContains(fraction));
    }

    public void numberOfWindowsToBe(int expectedNumber) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumber));
    }

    public void attributeContains(WebElement element, String attribute, String value) {
        wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public void attributeNotContains(WebElement element, String attribute, String value) {
        wait.until(driver -> {
            String attrValue = element.getAttribute(attribute);
            return attrValue == null || !attrValue.contains(value);
        });
    }

    public <T> T until(java.util.function.Function<WebDriver, T> condition) {
        return wait.until(condition::apply);
    }
}
