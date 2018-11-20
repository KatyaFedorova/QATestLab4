package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.model.ProductData;
import org.testng.annotations.Test;

public class CreateProductTest extends BaseTest {
    ProductData productData = ProductData.generate();

    @Test(dataProvider = "loginData")
    public void createNewProduct(String login, String password) {
        actions.login(login, password);
        actions.createProduct(productData);
    }

    @Test(dependsOnMethods = "createNewProduct")
    public void checkNewProduct() {
        actions.checkVisibilityCreatedProduct(productData.getName());
        actions.checkProductProperty(productData);
    }

}
