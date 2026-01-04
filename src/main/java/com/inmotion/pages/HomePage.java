package com.inmotion.pages;

import com.inmotion.pages.components.AccessibilityPanel;
import com.inmotion.pages.components.CookieComponent;
import com.inmotion.pages.components.FooterComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {

    private final CookieComponent cookieComponent;
    private final FooterComponent footerComponent;
    private final AccessibilityPanel accessibilityPanel;

    private final By resourcesMenu = By.xpath("//*[contains(text(), 'Resources')]");
    private final By compareLink = By.cssSelector("a[href*='compare']");

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
            actions.moveToElement(waits.visibilityOfElementLocated(resourcesMenu)).perform();
            waits.visibilityOfElementLocated(compareLink);
            waits.elementToBeClickable(compareLink).click();
        } catch (Exception e) {
            try {
                WebElement link = driver.findElement(compareLink);
                jsUtils.click(link);
            } catch (Exception ex) {
                throw e;
            }
        }
        return new ComparePage(driver);
    }
}
