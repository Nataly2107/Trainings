import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
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
        assertElementHasText(waitElement(By.id("org.wikipedia:id/search_src_text"), "Can not find Search field", 5), "Search…");
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
        waitElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can not find X to cancel button",
                5);
        Assert.assertTrue(elementIsAbsent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']")));
    }

    @Test
    public void Ex4() {
        String searchText = "Appium";
        boolean hasWord = true;
        waitElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find Search Wikipedia field",
                5);
        searchText(searchText);
        List<WebElement> elements = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"));
        for (WebElement article :
                elements) {
            if (!validateSearchWordInArticle(article, searchText)) {
                hasWord = false;
                System.out.print(validateSearchWordInArticle(article, searchText));
            }
        }
        Assert.assertTrue(hasWord);

    }

    @Test
    public void Ex5() {
        String searchText = "Appium";
        String nameFolder = "Learning programming";
        waitElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find Search Wikipedia field",
                5);
        searchText(searchText);
        waitElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Appium']"),
                "Can not title article",
                25);
        waitElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can not find button to open article options",
                5);
        waitElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find button to add to reading list",
                10);
        waitElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can not find button Got it tip overlay",
                15);
        waitElementAndSendKey(
                By.id("org.wikipedia:id/text_input"),
                nameFolder,
                "Can not find text input for name list",
                15);
        waitElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can not press OK",
                15);
        waitElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not find button X to close article",
                15);
        waitElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find Search Wikipedia field",
                5);
        searchText("Java");
        waitElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Java (programming language)']"),
                "Can not title article",
                25);
        waitElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can not find button to open article options",
                5);
        waitElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find button to add to reading list",
                10);
        waitElementAndClick(
                By.xpath("//*[@text='"+nameFolder+"']"),
                "Can not find button greated folder",
                10);
        waitElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not find button X to close article",
                5);
        waitElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can not find button to My List",
                5);
        waitElementAndClick(
                By.xpath("//*[@text='"+nameFolder+"']"),
                "Can not find created folder",
                5);
        swipeElementToLeft(
                By.xpath("//*[@text='Appium']/.."),
                "Can not find saved article"
        );
        elementIsAbsent( By.xpath("//*[@text='Appium']"));
        waitElement(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can not find second saved article",
                15
        );

    }

    private void waitElementAndClick(By by, String errorMessage, long timeout) {
        waitElement(by, errorMessage, timeout).click();
    }

    private void waitElementAndSendKey(By by, String text, String errorMessage, long timeout) {
        waitElement(by, errorMessage, timeout).clear();
        waitElement(by, errorMessage, timeout).sendKeys(text);

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

    private boolean validateSearchWordInArticle(WebElement article, String text) {
        String title = "";
        List<WebElement> elements = article.findElements(By.xpath("//*"));
        for (WebElement el : elements
        ) {
            System.out.println(el.getText());
            title += el.getText();
        }
        return title.contains(text);

    }
    protected void swipeElementToLeft(By by, String error){
       WebElement element = waitElement(by, error,10);
       int left_x = element.getLocation().getX();
       int right_x = left_x + element.getSize().getWidth();
       int upper_y = element.getLocation().getY();
       int lower_y = upper_y + element.getSize().getHeight();
       int middle_y = (upper_y+lower_y)/2;

       TouchAction action = new TouchAction(driver);
       action
               .press(right_x, middle_y)
               .waitAction(300)
               .moveTo(left_x,middle_y)
               .release()
               .perform();
    }

}
