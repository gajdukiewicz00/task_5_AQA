package com.inmotion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComparePage extends BasePage {

    private final By heading = By.tagName("h1");

    public ComparePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return waits.urlContains("/compare") && waits.visibilityOfElementLocated(heading).isDisplayed();
    }

    public String getHeadingText() {
        return driver.findElement(heading).getText();
    }
}
