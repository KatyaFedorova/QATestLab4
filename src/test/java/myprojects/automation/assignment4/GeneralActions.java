package myprojects.automation.assignment4;

import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;

import static com.sun.scenario.Settings.set;

public class GeneralActions extends PageElementAction {

    public static void login(String login, String password) {
        openUrl(Properties.getBaseAdminUrl());
        getElement("[name = \"email\"]").set(login);
        getElement("[name = \"passwd\"]").set(password);
        getElement("[name = \"submitLogin\"]").click();
    }

    public static void createProduct(ProductData newProduct) {
        getElement("[id = \"subtab-AdminCatalog\"]").hover();
        getElement("[id = \"subtab-AdminProducts\"]").click();
        getElement("[id = \"page-header-desc-configuration-add\"]").click();
        set("[id = \"form_step1_name_1\"]", newProduct.getName());
        getElement("[id = \"tab_step3\"]").click();
        set("[id = \"form_step3_qty_0\"]", String.valueOf(newProduct.getQty()));
        getElement("[id = \"tab_step2\"]").click();
        set("[id = \"form_step2_price\"]", String.valueOf(newProduct.getQty()));
        getElement("[id = \"submit\"]").click();
        getElement(".growl-message").verifyText("Настройки обновлены.");
        getElement(".growl-close").click();
    }

    public static void logout() {
        getElement(".employee_avatar_small").click();
        getElement("[id = \"header_logout\"]").click();
    }
}
