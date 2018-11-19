package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CreateProductTest extends BaseTest {
    ProductData productData = ProductData.generate();

    @Test(dataProvider = "loginData")
    public void createNewProduct(String login, String password) {
        actions.login(login, password);
        actions.createProduct(productData);
        actions.logout();
    }

    @Test(dependsOnMethods = "createNewProduct")
    public void checkNewProduct() {
        driver.get(Properties.getBaseUrl());
        actions.click(By.cssSelector(".all-product-link.pull-xs-left.pull-md-right.h4"));
        actions.set(By.cssSelector(".ui-autocomplete-input"), productData.getName());
        driver.findElement(By.cssSelector(""))

    }
}
