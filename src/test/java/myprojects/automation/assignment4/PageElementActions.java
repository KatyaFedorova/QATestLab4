package myprojects.automation.assignment4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class PageElementAction {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public WebElement getElement(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public void click(String locator) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        getElement(locator).click();
    }

    public void hover(String locator) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
        Actions action = new Actions(driver);
        action.moveToElement(getElement(locator)).build().perform();
    }

    public void set(String locator, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
        getElement(locator).sendKeys(value);
    }

    public void verifyElementWithTextPresent(String locator, String text) {
        String elementText = getElement(locator).getText();
        if (!elementText.contains(text)) {
            throw new RuntimeException("Текст \"%s\" не найден");
        }
    }
}