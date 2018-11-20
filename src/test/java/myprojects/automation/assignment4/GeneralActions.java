package myprojects.automation.assignment4;

import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GeneralActions {

    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement element;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void login(String login, String password) {
        openUrl(Properties.getBaseAdminUrl());
        set(By.name("email"), login);
        set(By.name("passwd"), password);
        click(By.name("submitLogin"));
    }

    public void createProduct(ProductData newProduct) {
        click(By.cssSelector("[data-submenu=\"9\"]"));
        click(By.id("page-header-desc-configuration-add"));
        set(By.id("form_step1_name_1"), (newProduct.getName()));
        click(By.id("tab_step3"));
        set(By.id("form_step3_qty_0"), String.valueOf(newProduct.getQty()));
        click(By.id("tab_step2"));
        set(By.id("form_step2_price"), String.valueOf(newProduct.getPrice()));
        click(By.cssSelector(".switch-input"));
        checkSuccess();
        click(By.cssSelector(".btn-primary.save.uppercase"));
        checkSuccess();
    }

    private void checkSuccess() {
        element = driver.findElement(By.cssSelector(".growl-message"));
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertEquals(element.getText(), "Настройки обновлены.");
        click(By.cssSelector(".growl-close"));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void checkVisibilityCreatedProduct(String productName) {
        openUrl(Properties.getBaseUrl());
        click(By.cssSelector(".all-product-link.pull-xs-left.pull-md-right.h4"));
        set(By.cssSelector(".ui-autocomplete-input"), productName);
        click(By.cssSelector("button[type = \"submit\"]"));
        String searchResultName = getAttribute(By.cssSelector(".thumbnail-container .product-description a"), "text");
        Assert.assertEquals(searchResultName, productName);
    }

    public void checkProductProperty(ProductData productData) {
        click(By.cssSelector(".thumbnail-container .product-description a"));
        String productName = getText(By.cssSelector(".col-md-6 [itemprop = \"name\"]"));
        Assert.assertEquals(productName, productData.getName().toUpperCase());
        String productPrice = getAttribute(By.cssSelector(".col-md-6 [itemprop = \"price\"]"), "content");
        Assert.assertEquals(productPrice, productData.getPrice());
        String productQty = getText(By.cssSelector(".product-quantities span")).split(" ")[0];
        Assert.assertEquals(productQty, String.valueOf(productData.getQty()));
    }

    //===================================== BaseMethods ================================================================

    private void openUrl(String url) {
        driver.get(url);
    }

    private void findElement(By locator) {
        element = driver.findElement(locator);
    }

    public String getAttribute(By locator, String attributeName) {
        element = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
       return element.getAttribute(attributeName);
    }

    public String getText(By locator) {
        element = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
       return element.getText();
    }

    public void click(By locator) {
        findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void set(By locator, String text) {
        findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
        if (!element.getAttribute("value").isEmpty()) {
            element.clear();
        }
        element.sendKeys(text);
    }
}
