package com.inmotion.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComparePage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement heading;

    public ComparePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return waits.urlContains("/compare") && waits.visibilityOf(heading).isDisplayed();
    }

    public String getHeadingText() {
        return waits.visibilityOf(heading).getText();
    }
}
