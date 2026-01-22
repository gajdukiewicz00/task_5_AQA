package com.inmotion.pages;

import com.inmotion.pages.components.AccessibilityPanel;
import com.inmotion.pages.components.CookieComponent;
import com.inmotion.pages.components.FooterComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private final CookieComponent cookieComponent;
    private final FooterComponent footerComponent;
    private final AccessibilityPanel accessibilityPanel;

    @FindBy(xpath = "//*[contains(text(), 'Resources')]")
    private WebElement resourcesMenu;

    @FindBy(css = "a[href*='compare']")
    private WebElement compareLink;

    public HomePage(WebDriver driver) {
        super(driver);
        this.cookieComponent = new CookieComponent(driver);
        this.footerComponent = new FooterComponent(driver);
        this.accessibilityPanel = new AccessibilityPanel(driver);
    }

    public void handleCookies() {
        cookieComponent.handleCookieBanner();
    }

    public FooterComponent getFooter() {
        return footerComponent;
    }

    public AccessibilityPanel getAccessibilityPanel() {
        return accessibilityPanel;
    }

    public ComparePage navigateToComparePage() {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(waits.visibilityOf(resourcesMenu)).perform();
            waits.visibilityOf(compareLink);
            waits.elementToBeClickable(compareLink).click();
        } catch (Exception e) {
            try {
                jsUtils.click(compareLink);
            } catch (Exception ex) {
                throw e;
            }
        }
        return new ComparePage(driver);
    }
}
