package com.inmotion.tests;

import com.inmotion.core.BaseTest;
import com.inmotion.pages.ComparePage;
import com.inmotion.pages.HomePage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResourcesCompareTest extends BaseTest {

    @Test
    public void testResourcesCompareNavigation() {
        HomePage homePage = new HomePage(driver);
        homePage.handleCookies();

        logger.info("Navigating to Compare Page via Resources menu");
        ComparePage comparePage = homePage.navigateToComparePage();

        logger.info("Verifying Compare Page is loaded");
        assertThat(comparePage.isLoaded())
                .as("Compare page should be loaded with correct URL and heading")
                .isTrue();

        logger.info("Navigating back to Home Page");
        driver.navigate().back();

        logger.info("Verifying we are back on Home Page");
        assertThat(driver.getCurrentUrl())
                .as("Should be back on home page")
                .startsWith("https://www.inmotionhosting.com");
    }
}
