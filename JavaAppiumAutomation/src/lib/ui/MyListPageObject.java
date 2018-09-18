package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListPageObject extends MainPageObject {
    private static final String
            MY_LIST = "xpath://android.widget.FrameLayout[@content-desc='My lists']",
            ELEMENT_BY_TEXT = "xpath://*[@text='{SUBSTRING}']";

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    private String getElementByText(String substring) {
        return ELEMENT_BY_TEXT.replace("{SUBSTRING}", substring);
    }

    public void openMyList() {
        this.waitElementAndClick(MY_LIST,
                "Can not open My List",
                15);
    }

    public void openListByName(String name) {
        this.waitElementAndClick(getElementByText(name),
                "Can not open list with name " + name,
                15);
    }

    public void deleteArticleFromList(String name) {
        this.swipeElementToLeft(getElementByText(name) + "/..",
                "Can not delete article from list");
    }

    public void validateArticleIsAbsent(String titleArticle) {
        this.elementIsAbsent(getElementByText(titleArticle));
    }

    public void openSavedArticle(String articleTitle) {
        this.waitElementAndClick((getElementByText(articleTitle)),
                "Can not open saved article",
                15);
    }
}
