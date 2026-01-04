package com.inmotion.tests;

import com.inmotion.core.BaseTest;
import com.inmotion.core.JsUtils;
import com.inmotion.core.Waits;
import com.inmotion.core.WindowUtils;
import com.inmotion.pages.HomePage;
import com.inmotion.pages.components.FooterComponent;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SocialMediaTest extends BaseTest {

    @Test
    public void testFacebookLinkInFooter() {
        HomePage homePage = new HomePage(driver);
        homePage.handleCookies();
        JsUtils jsUtils = new JsUtils(driver);
        WindowUtils windowUtils = new WindowUtils(driver);

        logger.info("Scrolling to bottom");
        jsUtils.scrollToBottom();

        FooterComponent footer = homePage.getFooter();
        assertThat(footer.isVisible()).as("Footer should be visible").isTrue();

        String originalWindow = windowUtils.getCurrentWindowHandle();

        logger.info("Clicking Facebook link");
        footer.getFacebookLink().click();

        logger.info("Switching to new tab");
        windowUtils.switchToNewTab();

        logger.info("Verifying URL contains facebook.com");
        Waits waits = new Waits(driver);
        waits.urlContains("facebook.com");

        assertThat(driver.getCurrentUrl())
                .as("New tab URL should contain facebook.com")
                .contains("facebook.com");

        logger.info("Closing tab and returning");
        windowUtils.closeTabAndReturn(originalWindow);

        logger.info("Verifying we are still at the bottom (footer visible)");
        assertThat(footer.isVisible()).as("Footer should still be visible").isTrue();
    }
}
