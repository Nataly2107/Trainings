package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.NavigableMap;

public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            MORE_OPTIONS = "//android.widget.ImageView[@content-desc='More options']",
            ADD_TO_READING_LIST = "//*[@text='Add to reading list']",
            GOT_IT = "org.wikipedia:id/onboarding_button",
            INPUT_LIST_NAME = "org.wikipedia:id/text_input",
            ELEMENT_BY_TEXT = "//*[@text='{SUBSTRING}']",
            NAVIGATE_UP = "//android.widget.ImageButton[@content-desc='Navigate up']",
            ARTICLE_TITLE = "//*[@resource-id='org.wikipedia:id/view_page_title_text']";


    private String getElementByText(String substring) {
        return ELEMENT_BY_TEXT.replace("{SUBSTRING}", substring);
    }

    public void validateTitleInArticleIsPresent() {
        this.assertElementPresent(By.xpath(ARTICLE_TITLE),
                "Title is absent");
    }

    public void clickOnMoreOptions() {
        this.waitElementAndClick(By.xpath(MORE_OPTIONS),
                "Can not click on More Options",
                15);
    }

    public void clickOnAddReadingList() {
        this.waitElementAndClick(By.xpath(ADD_TO_READING_LIST),
                "Can not click on Add to reading list",
                15);
    }

    public void clickOnGotIt() {
        this.waitElementAndClick(By.xpath(GOT_IT),
                "Can not click on Got it",
                15);
    }

    public void typeNameForList(String name) {
        this.waitElementAndSendKey(By.id(INPUT_LIST_NAME),
                name,
                "Can not input name for list",
                15);
    }

    public void clickOnOK() {
        this.waitElementAndClick(By.xpath(getElementByText("OK")),
                "Can not click OK",
                15);
    }

    public void clickOnNavigateUp() {
        this.waitElementAndClick(By.xpath(NAVIGATE_UP),
                "Can not click on Navigate up",
                15);
    }

    public void selectFolderForSavingArticle(String nameFolder) {
        this.waitElementAndClick(By.xpath(getElementByText(nameFolder)),
                "Can not click on folder name " + nameFolder,
                15);
    }


}
