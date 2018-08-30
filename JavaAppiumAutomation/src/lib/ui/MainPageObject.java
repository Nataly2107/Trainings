package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public String waitElementAndGetAttribute(By by, String attribute, String error, long timeout) {
        return waitElement(by, error, timeout).getAttribute(attribute);
    }

    public void waitElementAndClick(By by, String errorMessage, long timeout) {
        waitElement(by, errorMessage, timeout).click();
    }

    public void assertElementPresent(By by, String error) {
        Assert.assertTrue(error, driver.findElements(by).size() == 1);
    }

    public void waitElementAndSendKey(By by, String text, String errorMessage, long timeout) {
        waitElement(by, errorMessage, timeout).clear();
        waitElement(by, errorMessage, timeout).sendKeys(text);

    }

    public WebElement waitElement(By by, String errorMessage, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void assertElementHasText(WebElement element, String text) {
        Assert.assertEquals(element.getText(), text);
    }

    public void searchText(String text) {
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

    public boolean validateSearchWordInArticle(WebElement article, String text) {
        String title = "";
        List<WebElement> elements = article.findElements(By.xpath("//*"));
        for (WebElement el : elements
        ) {
            System.out.println(el.getText());
            title += el.getText();
        }
        return title.contains(text);

    }

    public void swipeElementToLeft(By by, String error) {
        WebElement element = waitElement(by, error, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

}
