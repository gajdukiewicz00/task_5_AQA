# InMotion Hosting Selenium Automation

This project contains E2E tests for the InMotion Hosting website using Selenium WebDriver, JUnit 5, and Java. The framework follows Page Object Model (POM) pattern with component-based architecture.

## Tech Stack

- **Java 17** - Programming language
- **Selenium 4.16.1** - Web automation framework
- **JUnit 5.10.1** - Testing framework
- **WebDriverManager 5.6.3** - Automatic driver management
- **Maven** - Build and dependency management
- **AssertJ 3.24.2** - Fluent assertions library
- **SLF4J 2.0.9** - Logging framework
- **Lombok 1.18.30** - Code generation library

## Project Structure

```
src/
├── main/java/com/inmotion/
│   ├── config/
│   │   └── TestConfig.java          # Configuration loader
│   ├── core/
│   │   ├── DriverFactory.java       # WebDriver initialization
│   │   ├── Waits.java               # Explicit wait utilities
│   │   ├── JsUtils.java             # JavaScript utilities
│   │   └── WindowUtils.java         # Window/tab management
│   └── pages/
│       ├── BasePage.java            # Base page class
│       ├── HomePage.java            # Home page object
│       ├── ComparePage.java         # Compare page object
│       └── components/
│           ├── AccessibilityPanel.java  # Accessibility widget component
│           ├── CookieComponent.java      # Cookie banner component
│           └── FooterComponent.java     # Footer component
└── test/java/com/inmotion/
    ├── core/
    │   └── BaseTest.java            # Base test class with setup/teardown
    ├── tests/
    │   ├── AccessibilityTest.java   # Accessibility panel tests
    │   ├── ResourcesCompareTest.java # Navigation tests
    │   └── SocialMediaTest.java     # Social media links tests
    └── utils/
        └── TestListener.java        # Test execution listener
```

## Test Cases

### 1. AccessibilityTest (`testAccessibilityPanel`)
Tests the accessibility panel functionality:
- Verifies default language is English
- Changes language to Spanish and verifies the change
- Verifies contrast is disabled by default
- Toggles contrast mode and verifies it's enabled
- Resets settings and verifies contrast is disabled
- Tests virtual keyboard functionality
- Verifies settings reset after page refresh

### 2. ResourcesCompareTest (`testResourcesCompareNavigation`)
Tests navigation functionality:
- Navigates to Compare page via Resources menu
- Verifies Compare page loads correctly
- Navigates back to Home page
- Verifies correct URL after navigation

### 3. SocialMediaTest (`testFacebookLinkInFooter`)
Tests social media links in footer:
- Scrolls to footer
- Clicks Facebook link
- Verifies new tab opens with Facebook URL
- Closes tab and returns to original window
- Verifies footer is still visible

## Prerequisites

- **Java 17+** installed and configured
- **Maven 3.6+** installed
- **Chrome browser** installed (for ChromeDriver)

## Configuration

Configuration is managed through `src/test/resources/config.properties`:

```properties
baseUrl=https://www.inmotionhosting.com/
browser=chrome
headless=true
timeoutSeconds=10
pageLoadSeconds=30
scriptSeconds=30
windowWidth=1920
windowHeight=1080
```

### Configuration Options

- **baseUrl**: Base URL of the application under test
- **browser**: Browser to use (chrome, firefox, etc.)
- **headless**: Run browser in headless mode (true/false)
- **timeoutSeconds**: Default timeout for explicit waits
- **pageLoadSeconds**: Page load timeout
- **scriptSeconds**: Script execution timeout
- **windowWidth/Height**: Browser window dimensions

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Class
```bash
mvn clean test -Dtest=AccessibilityTest
mvn clean test -Dtest=ResourcesCompareTest
mvn clean test -Dtest=SocialMediaTest
```

### Run in Headed Mode (Non-Headless)
```bash
mvn clean test -Dheadless=false
```

### Run with Custom Configuration
You can override configuration properties via system properties:
```bash
mvn clean test -Dheadless=false -DtimeoutSeconds=15
```

## Reports

### Test Reports
- **Surefire Reports**: Available in `target/surefire-reports/`
  - XML reports: `TEST-*.xml`
  - Text reports: `*.txt`

### Screenshots
- Screenshots on test failure are automatically saved to `screenshots/` directory
- Screenshot naming format: `{testMethodName}___{timestamp}.png`

## Key Features

### Page Object Model
- Clean separation of test logic and page interactions
- Reusable page objects and components
- Easy maintenance and updates

### Component-Based Architecture
- Reusable UI components (AccessibilityPanel, FooterComponent, CookieComponent)
- Components can be composed within pages

### Robust Waits
- Explicit waits for element visibility, clickability, and attribute changes
- Prevents flaky tests due to timing issues
- Custom wait utilities for common scenarios

### Error Handling
- Automatic screenshot capture on test failures
- Comprehensive logging throughout test execution
- Graceful handling of optional elements

### Best Practices
- Proper assertions after state changes
- Waits for DOM updates after actions
- Scoped locators to avoid false matches
- Single instance pattern for utility classes

## Framework Components

### Waits
Provides explicit wait utilities:
- `visibilityOf()` / `visibilityOfElementLocated()`
- `elementToBeClickable()`
- `urlContains()`
- `attributeContains()` / `attributeNotContains()`
- `numberOfWindowsToBe()`

### JsUtils
JavaScript utilities for:
- Scrolling operations
- Clicking elements via JavaScript
- Executing custom JavaScript

### WindowUtils
Window and tab management:
- `switchToNewTab()`
- `closeTabAndReturn()`
- `getCurrentWindowHandle()`



### Screenshots Not Generated
- Ensure `screenshots/` directory exists
- Check file permissions
- Review test listener configuration





