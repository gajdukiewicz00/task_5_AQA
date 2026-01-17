package com.inmotion.pages;

import com.inmotion.core.JsUtils;
import com.inmotion.core.Waits;
import org.openqa.selenium.WebDriver;

// Note: page objects use By + explicit waits instead of @FindBy/PageFactory to keep lookups fresh.
public abstract class BasePage {

    protected WebDriver driver;
    protected Waits waits;
    protected JsUtils jsUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.jsUtils = new JsUtils(driver);
    }
}
