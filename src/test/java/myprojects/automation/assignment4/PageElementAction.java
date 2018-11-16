package myprojects.automation.assignment4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageElementAction {

    private static WebDriver driver;
    static WebDriverWait wait;
    Element element;

    static void setDriverConfig(WebDriver webDriver) {
        driver = webDriver;
        wait = new WebDriverWait(webDriver, 30);
    }

    public static void openUrl(String url) {
        driver.get(url);
    }

    public static Element getElement(String locator) {
        return (Element) driver.findElement(By.cssSelector(locator));
    }

    public class Element extends PageElementAction {

        public void click() {
            wait.until(ExpectedConditions.elementToBeClickable((WebElement)element));
            element.click();
        }

        void hover() {
            wait.until(ExpectedConditions.elementToBeClickable((WebElement)element));
            Actions action = new Actions(driver);
            action.moveToElement((WebElement)element).build().perform();
        }

        void set(String value) {
            wait.until(ExpectedConditions.visibilityOf((WebElement)element));
            ((WebElement)element).sendKeys(value);
        }

        void verifyText(String text) {
            String elementText = ((WebElement)element).getText();
            if (!elementText.contains(text)) {
                throw new RuntimeException("Текст \"%s\" не найден");
            }
        }
    }

}