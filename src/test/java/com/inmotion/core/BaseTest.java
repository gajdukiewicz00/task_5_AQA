package com.inmotion.core;

import com.inmotion.config.TestConfig;
import com.inmotion.utils.TestListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(TestListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.get(TestConfig.baseUrl());
    }

    public WebDriver getDriver() {
        return driver;
    }
}
