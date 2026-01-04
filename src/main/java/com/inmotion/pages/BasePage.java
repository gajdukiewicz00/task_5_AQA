package com.inmotion.pages;

import com.inmotion.core.JsUtils;
import com.inmotion.core.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected WebDriver driver;
    protected Waits waits;
    protected JsUtils jsUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.jsUtils = new JsUtils(driver);
        PageFactory.initElements(driver, this);
    }
}
