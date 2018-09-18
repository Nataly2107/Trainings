package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public String waitElementAndGetAttribute(String locator, String attribute, String error, long timeout) {

        return waitElement(locator, error, timeout).getAttribute(attribute);
    }

    public void waitElementAndClick(String locator, String errorMessage, long timeout) {
        waitElement(locator, errorMessage, timeout).click();
    }

    public void assertElementPresent(String locator, String error) {
        Assert.assertTrue(error, driver.findElements(getLocatorByString(locator)).size() == 1);
    }

    public void waitElementAndSendKey(String locator, String text, String errorMessage, long timeout) {
        waitElement(locator, errorMessage, timeout).clear();
        waitElement(locator, errorMessage, timeout).sendKeys(text);

    }

    public WebElement waitElement(String locator, String errorMessage, long timeout) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void assertElementHasText(WebElement element, String text) {
        Assert.assertEquals(element.getText(), text);
    }

    public void searchText(String text) {
        waitElement("id:org.wikipedia:id/search_src_text",
                "Can not find Search field",
                10)
                .clear();
        driver.findElement(By.id("org.wikipedia:id/search_src_text")).sendKeys(text);
    }

    public int getCountOfArticles() {
        waitElement("xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']",
                "Can not find articles",
                15);
        int n = driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']")).size();
        return n;
    }

    public boolean elementIsAbsent(String locator) {
        By by = this.getLocatorByString(locator);

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

    public void swipeElementToLeft(String locator, String error) {
        WebElement element = waitElement(locator, error, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
         action.press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    protected By getLocatorByString(String locator_by_type) {
        String[] exploded_locator = locator_by_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Unknown type locator");
        }
    }

}
