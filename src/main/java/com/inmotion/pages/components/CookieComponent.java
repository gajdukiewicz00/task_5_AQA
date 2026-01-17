package com.inmotion.pages.components;

import com.inmotion.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CookieComponent extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CookieComponent.class);

    // By locators with explicit waits avoid stale element caching from @FindBy/PageFactory.
    private final By acceptButton = By.cssSelector(
            "button#onetrust-accept-btn-handler, button[class*='cookie'][class*='accept'], .cc-btn.cc-dismiss");

    public CookieComponent(WebDriver driver) {
        super(driver);
    }

    public void handleCookieBanner() {
        try {
            List<WebElement> buttons = driver.findElements(acceptButton);
            if (!buttons.isEmpty() && buttons.get(0).isDisplayed()) {
                logger.info("Cookie banner found, clicking accept.");
                buttons.get(0).click();
            }
        } catch (Exception e) {
            logger.debug("No cookie banner handled or error clicking it: {}", e.getMessage());
        }
    }
}
