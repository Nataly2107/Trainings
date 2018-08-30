package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {
    private static final String
            MY_LIST = "//android.widget.FrameLayout[@content-desc='My lists']",
            ELEMENT_BY_TEXT = "//*[@text='{SUBSTRING}']";

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    private String getElementByText(String substring) {
        return ELEMENT_BY_TEXT.replace("{SUBSTRING}", substring);
    }

    public void openMyList() {
        this.waitElementAndClick(By.xpath(MY_LIST),
                "Can not open My List",
                15);
    }

    public void openListByName(String name) {
        this.waitElementAndClick(By.xpath(getElementByText(name)),
                "Can not open list with name " + name,
                15);
    }

    public void deleteArticleFromList(String name) {
        this.swipeElementToLeft(By.xpath(getElementByText(name) + "/.."),
                "Can not delete article from list");
    }

    public void validateArticleIsAbsent(String titleArticle) {
        this.elementIsAbsent(By.xpath(getElementByText(titleArticle)));
    }

    public void openSavedArticle(String articleTitle) {
        this.waitElementAndClick(By.xpath(getElementByText(articleTitle)),
                "Can not open saved article",
                15);
    }
}
