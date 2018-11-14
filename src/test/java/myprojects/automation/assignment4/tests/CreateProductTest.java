package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import org.testng.annotations.Test;

public class CreateProductTest extends BaseTest {

    @Test
    public void createNewProduct(String login, String password) {
         actions.login(login, password);
         actions.createProduct(ProductData.generate());
         actions.logout();
    }

    @Test
    public void checkNewProduct() {
        actions.openUrl(Properties.getBaseUrl());
    }
}
