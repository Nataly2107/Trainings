import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Test\\learning\\Trainings\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void Ex2() {
        waitElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find Search Wikipedia field",
                5);
        assertElementHasText(waitElement(By.id("org.wikipedia:id/search_src_text"),"Can not find Search field",5),"Search…");
    }

    @Test
    public void Ex3() {
        String searchText = "Android";
        waitElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find Search Wikipedia field",
                5);
        searchText(searchText);
        Assert.assertTrue(getCountOfArticles() > 0); //validate count of article
        validateSearchWordInArticle(1, searchText);
        waitElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can not find X to cancel button",
                5);
        Assert.assertTrue(elementIsAbsent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']")));
    }

    @Test
    public void Ex4() {
        String searchText = "Android";
        waitElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find Search Wikipedia field",
                5);
        searchText(searchText);
        int i = getCountOfArticles();
        for (int n = 1; n <= i; n++) {
            validateSearchWordInArticle(n, searchText);
        }
    }

    private void waitElementAndClick(By by, String errorMessage, long timeout) {
        waitElement(by, errorMessage, timeout).click();
    }

    private WebElement waitElement(By by, String errorMessage, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void assertElementHasText(WebElement element, String text) {
        Assert.assertEquals(element.getText(), text);
    }

    private void searchText(String text) {
        waitElement(By.id("org.wikipedia:id/search_src_text"),
                "Can not find Search field",
                10)
                .clear();
        driver.findElement(By.id("org.wikipedia:id/search_src_text")).sendKeys(text);
    }

    public int getCountOfArticles() {
        waitElement(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                "Can not find articles",
                15);
        int n = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']")).size();
        return n;
    }

    public boolean elementIsAbsent(By by) {
        return driver.findElements(by).isEmpty();
    }

    private void validateSearchWordInArticle(int indexArticle, String text) {
        String xpath = "//*[@resource-id='org.wikipedia:id/page_list_item_container']" + "[" + indexArticle + "]" + "//*";
        String title = "";
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        for (WebElement el : elements
        ) {
            System.out.println(el.getText());
            title += el.getText();
        }
        Assert.assertTrue(title.contains(text));

    }
}
