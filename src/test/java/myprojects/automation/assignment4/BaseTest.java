package myprojects.automation.assignment4;

import myprojects.automation.assignment4.utils.logging.EventHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected EventFiringWebDriver driver;

    @BeforeClass
    // TODO use parameters from pom.xml to pass required browser type
    // public void setUpBrowser(String browser) {
    public void setUpBrowser() {
        driver = new EventFiringWebDriver(getDriver("firefox"));
        driver.register(new EventHandler());

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        PageElementAction.setDriverConfig(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver getDriver(String browser) {
        switch (browser) {
            case "firefox":
                System.setProperty(
                        "webdriver.gecko.driver",
                        getResource("/geckodriver"));
                return new FirefoxDriver();
            case "ie":
            case "internet explorer":
                System.setProperty(
                        "webdriver.ie.driver",
                        getResource("/IEDriverServer.exe"));
                return new InternetExplorerDriver();
            case "chrome":
            default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        getResource("/chromedriver.exe"));
                return new ChromeDriver();
        }
    }

    private String getResource(String resourceName) {
        try {
           return Paths.get(BaseTest.class.getResource(resourceName).toURI()).toFile().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resourceName;
    }

    @DataProvider(name = "loginData")
    protected Object[][] loginData() {
        return new Object[][]{{"login", "webinar.test@gmail.com"}, {"password", "Xcg7299bnSmMuRLp9ITw"}};
    }
}
