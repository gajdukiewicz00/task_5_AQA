package com.inmotion.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsUtils {

    private final JavascriptExecutor js;

    public JsUtils(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void click(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
}
