package com.inmotion.core;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowUtils {

    private final WebDriver driver;
    private final Waits waits;

    public WindowUtils(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        waits.numberOfWindowsToBe(2);

        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public void closeTabAndReturn(String originalHandle) {
        driver.close();
        driver.switchTo().window(originalHandle);
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }
}
