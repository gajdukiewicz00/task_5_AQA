package com.inmotion.pages.components;

import com.inmotion.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CookieComponent extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CookieComponent.class);

    @FindBy(css = "button#onetrust-accept-btn-handler, button[class*='cookie'][class*='accept'], .cc-btn.cc-dismiss")
    private List<WebElement> acceptButtons;

    public CookieComponent(WebDriver driver) {
        super(driver);
    }

    public void handleCookieBanner() {
        try {
            if (!acceptButtons.isEmpty() && acceptButtons.get(0).isDisplayed()) {
                logger.info("Cookie banner found, clicking accept.");
                acceptButtons.get(0).click();
            }
        } catch (Exception e) {
            logger.debug("No cookie banner handled or error clicking it: {}", e.getMessage());
        }
    }
}
