package com.inmotion.pages.components;

import com.inmotion.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class AccessibilityPanel extends BasePage {

    @FindBy(css = ".uwy-language-selector, [aria-label*='Language']")
    private WebElement languageSelector;

    @FindBy(css = "button[aria-label='Close'], .close-btn, [class*='close']")
    private WebElement closeButton;

    @FindBy(css = ".virtual-keyboard, #virtual-keyboard, [class*='keyboard'], [id*='keyboard'], .uwy-keyboard")
    private List<WebElement> virtualKeyboards;

    @FindBy(tagName = "body")
    private WebElement body;

    public AccessibilityPanel(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#userwayAccessibilityIcon[aria-expanded='true'], .uwy-open, .uwy-widget-open")
    private List<WebElement> panelContainers;

    @FindBy(css = ".uwy-widget .uwy-language-selector, .uwy-accessibility-widget .uwy-language-selector")
    private List<WebElement> widgetLanguageSelectors;

    @FindBy(css = ".uwy-widget button[aria-label*='Close'], .uwy-accessibility-widget [class*='close']")
    private List<WebElement> widgetCloseButtons;

    @FindBy(css = ".uwy-keyboard-close, [aria-label*='close keyboard']")
    private List<WebElement> keyboardCloseButtons;

    public boolean isPanelOpen() {
        try {
            for (WebElement p : panelContainers) {
                if (p.isDisplayed())
                    return true;
            }
            for (WebElement el : widgetLanguageSelectors) {
                if (el.isDisplayed())
                    return true;
            }
            for (WebElement el : widgetCloseButtons) {
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
            WebElement element = waits.visibilityOf(languageSelector);
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
            WebElement element = waits.visibilityOf(languageSelector);
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
        WebElement bodyElement = body;
        if (wasEnabled) {
            waits.attributeNotContains(bodyElement, "class", "contrast");
        } else {
            waits.attributeContains(bodyElement, "class", "contrast");
        }
    }

    public void resetSettings() {
        clickAccessibilityOption("Reset");
        WebElement bodyElement = body;
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
            return waits.until(driver -> {
                for (WebElement keyboard : virtualKeyboards) {
                    if (keyboard.isDisplayed()) {
                        return true;
                    }
                }
                return false;
            });
        } catch (Exception e) {
            return false;
        }
    }

    public void closeVirtualKeyboard() {
        try {
            for (WebElement btn : keyboardCloseButtons) {
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
                jsUtils.click(closeButton);
            } catch (Exception ex) {
            }
        }
    }

    public boolean isContrastEnabled() {
        String classes = body.getAttribute("class");
        return classes != null && classes.contains("contrast");
    }
}
