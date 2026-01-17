package com.inmotion.pages.components;

import com.inmotion.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class AccessibilityPanel extends BasePage {

    // By locators with explicit waits avoid stale element caching from @FindBy/PageFactory.
    private final By languageSelector = By.cssSelector(".uwy-language-selector, [aria-label*='Language']");
    private final By closeButton = By.cssSelector("button[aria-label='Close'], .close-btn, [class*='close']");
    private final By virtualKeyboard = By
            .cssSelector(".virtual-keyboard, #virtual-keyboard, [class*='keyboard'], [id*='keyboard'], .uwy-keyboard");
    private final By body = By.tagName("body");

    public AccessibilityPanel(WebDriver driver) {
        super(driver);
    }

    private final By panelContainer = By
            .cssSelector("#userwayAccessibilityIcon[aria-expanded='true'], .uwy-open, .uwy-widget-open");
    private final By widgetLanguageSelector = By
            .cssSelector(".uwy-widget .uwy-language-selector, .uwy-accessibility-widget .uwy-language-selector");

    public boolean isPanelOpen() {
        try {
            List<WebElement> panels = driver.findElements(panelContainer);
            for (WebElement p : panels) {
                if (p.isDisplayed())
                    return true;
            }
            List<WebElement> langSelectors = driver.findElements(widgetLanguageSelector);
            for (WebElement el : langSelectors) {
                if (el.isDisplayed())
                    return true;
            }
            List<WebElement> closeButtons = driver.findElements(By.cssSelector(
                    ".uwy-widget button[aria-label*='Close'], .uwy-accessibility-widget [class*='close']"));
            for (WebElement el : closeButtons) {
                if (el.isDisplayed())
                    return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSelectedLanguage() {
        try {
            WebElement element = waits.visibilityOfElementLocated(languageSelector);
            if (element.getTagName().equalsIgnoreCase("select")) {
                Select select = new Select(element);
                return select.getFirstSelectedOption().getText();
            } else {
                return element.getText();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get selected language", e);
        }
    }

    public void changeLanguage(String language) {
        try {
            WebElement element = waits.visibilityOfElementLocated(languageSelector);
            if (element.getTagName().equalsIgnoreCase("select")) {
                Select select = new Select(element);
                select.selectByVisibleText(language);
            } else {
                element.click();
                waits.elementToBeClickable(By.xpath("//*[contains(text(), '" + language + "')]")).click();
            }
        } catch (Exception e) {
            System.out.println("Could not change language: " + e.getMessage());
        }
    }

    public void toggleContrast() {
        boolean wasEnabled = isContrastEnabled();
        clickAccessibilityOption("Contrast");
        WebElement bodyElement = driver.findElement(body);
        if (wasEnabled) {
            waits.attributeNotContains(bodyElement, "class", "contrast");
        } else {
            waits.attributeContains(bodyElement, "class", "contrast");
        }
    }

    public void resetSettings() {
        clickAccessibilityOption("Reset");
        WebElement bodyElement = driver.findElement(body);
        waits.attributeNotContains(bodyElement, "class", "contrast");
    }

    public void openVirtualKeyboard() {
        clickAccessibilityOption("Keyboard");
    }

    private void clickAccessibilityOption(String optionName) {
        By specificToggle = By
                .cssSelector(".uwy-accessibility-widget [data-uwy-toggle='" + optionName.toLowerCase() + "']");

        List<By> locators = Arrays.asList(
                specificToggle,
                By.cssSelector("button[aria-label*='" + optionName + "']"),
                By.xpath("//div[contains(@class, 'uwy-accessibility-widget')]//*[contains(text(), '" + optionName
                        + "')]")
        );

        WebElement element = null;
        for (By loc : locators) {
            try {
                List<WebElement> elements = driver.findElements(loc);
                for (WebElement el : elements) {
                    if (el.isDisplayed() && el.isEnabled()) {
                        element = el;
                        break;
                    }
                }
            } catch (Exception e) {
            }
            if (element != null)
                break;
        }

        if (element != null) {
            jsUtils.scrollIntoView(element);
            try {
                waits.elementToBeClickable(element).click();
            } catch (Exception e) {
                jsUtils.click(element);
            }
        } else {
            System.out.println("Warning: Could not find accessibility option: " + optionName);
        }
    }

    public boolean isVirtualKeyboardVisible() {
        try {
            waits.visibilityOfElementLocated(virtualKeyboard);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void closeVirtualKeyboard() {
        try {
            By keyboardClose = By.cssSelector(".uwy-keyboard-close, [aria-label*='close keyboard']");
            List<WebElement> closeButtons = driver.findElements(keyboardClose);
            for (WebElement btn : closeButtons) {
                if (btn.isDisplayed()) {
                    btn.click();
                    return;
                }
            }
        } catch (Exception e) {
        }
        openVirtualKeyboard();
    }

    public void closePanel() {
        try {
            waits.elementToBeClickable(closeButton).click();
        } catch (Exception e) {
            try {
                jsUtils.click(driver.findElement(closeButton));
            } catch (Exception ex) {
            }
        }
    }

    public boolean isContrastEnabled() {
        String classes = driver.findElement(body).getAttribute("class");
        return classes != null && classes.contains("contrast");
    }
}
