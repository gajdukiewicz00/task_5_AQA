package com.inmotion.pages.components;

import com.inmotion.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FooterComponent extends BasePage {

    @FindBy(css = "a[href*='facebook.com']")
    private WebElement facebookLink;

    @FindBy(css = "a[class*='accessibility'], button[class*='accessibility']")
    private WebElement accessibilityTrigger;

    @FindBy(partialLinkText = "Accessibility")
    private WebElement accessibilityLinkText;

    @FindBy(css = "footer, .footer, #footer, [role='contentinfo']")
    private WebElement footerContainer;

    public FooterComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement getFacebookLink() {
        return waits.visibilityOf(facebookLink);
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
            return footerContainer.isDisplayed();
        } catch (Exception e) {
            try {
                return getFacebookLink().isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }
}
