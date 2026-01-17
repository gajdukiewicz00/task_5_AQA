package com.inmotion.pages.components;

import com.inmotion.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterComponent extends BasePage {

    // By locators with explicit waits avoid stale element caching from @FindBy/PageFactory.
    private final By facebookLink = By.cssSelector("a[href*='facebook.com']");
    private final By accessibilityTrigger = By.cssSelector("a[class*='accessibility'], button[class*='accessibility']");
    private final By accessibilityLinkText = By.partialLinkText("Accessibility");

    private final By footerContainer = By.cssSelector("footer, .footer, #footer, [role='contentinfo']");

    public FooterComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement getFacebookLink() {
        return waits.visibilityOfElementLocated(facebookLink);
    }

    public void openAccessibilityPanel() {
        jsUtils.scrollToBottom();
        try {
            waits.elementToBeClickable(accessibilityLinkText).click();
        } catch (Exception e) {
            waits.elementToBeClickable(accessibilityTrigger).click();
        }
    }

    public boolean isVisible() {
        try {
            return driver.findElement(footerContainer).isDisplayed();
        } catch (Exception e) {
            try {
                return getFacebookLink().isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }
}
