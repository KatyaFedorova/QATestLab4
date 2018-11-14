package myprojects.automation.assignment4;

import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions extends PageElementAction {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void login(String login, String password) {
        openUrl(Properties.getBaseAdminUrl());
        set("[name = \"email\"]", login);
        set("[name = \"passwd\"]", password);
        click("[name = \"submitLogin\"]");
    }

    public void createProduct(ProductData newProduct) {
        hover("[id = \"subtab-AdminCatalog\"]");
        click("[id = \"subtab-AdminProducts\"]");
        click("[id = \"page-header-desc-configuration-add\"]");
        set("[id = \"form_step1_name_1\"]", newProduct.getName());
        click("[id = \"tab_step3\"]");
        set("[id = \"form_step3_qty_0\"]", String.valueOf(newProduct.getQty()));
        click("[id = \"tab_step2\"]");
        set("[id = \"form_step2_price\"]", String.valueOf(newProduct.getQty()));
        click("[id = \"submit\"]");
        verifyElementWithTextPresent(".growl-message", "Настройки обновлены.");
        click(".growl-close");
    }

    public void logout() {
        click(".employee_avatar_small");
        click("[id = \"header_logout\"]");
    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad() {
        // TODO implement generic method to wait until page content is loaded

        // wait.until(...);
        // ...
    }
}
