package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.GeneralActions;
import myprojects.automation.assignment4.PageElementAction;
import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import org.testng.annotations.Test;

public class CreateProductTest extends BaseTest {

    @Test
    public void createNewProduct(String login, String password) {
         GeneralActions.login(login, password);
         GeneralActions.createProduct(ProductData.generate());
         GeneralActions.logout();
    }

    @Test
    public void checkNewProduct() {
        GeneralActions.openUrl(Properties.getBaseUrl());
        PageElementAction.getElement(".all-product-link.pull-xs-left.pull-md-right.h4").click();
    }
}
