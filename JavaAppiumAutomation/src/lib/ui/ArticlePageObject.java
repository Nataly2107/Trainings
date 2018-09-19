package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;


public abstract class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    protected static String
            MORE_OPTIONS,
            ADD_TO_READING_LIST,
            GOT_IT,
            INPUT_LIST_NAME,
            ELEMENT_BY_TEXT,
            NAVIGATE_UP,
            ARTICLE_TITLE,
            CLOSE_BTN;


    private String getElementByText(String substring) {
        return ELEMENT_BY_TEXT.replace("{SUBSTRING}", substring);
    }

    public void validateTitleInArticleIsPresent() {
        this.assertElementPresent(ARTICLE_TITLE,
                "Title is absent");
    }

    public void clickOnMoreOptions() {
        if(Platform.getInstance().isIOS()) {return;}
        this.waitElementAndClick(MORE_OPTIONS,
                "Can not click on More Options",
                15);
    }

    public void clickOnAddReadingList() {
        this.waitElementAndClick(ADD_TO_READING_LIST,
                "Can not click on Add to reading list",
                15);
    }

    public void clickOnGotIt() {
        if(Platform.getInstance().isIOS()) {
           // this.waitElementAndClick(CLOSE_BTN, "Can not click on Close btn",15);
            return;}

        this.waitElementAndClick(GOT_IT,
                "Can not click on Got it",
                15);
    }

    public void typeNameForList(String name) {
        if(Platform.getInstance().isIOS()) {return;}
        this.waitElementAndSendKey(INPUT_LIST_NAME,
                name,
                "Can not input name for list",
                15);
    }

    public void clickOnOK() {
        if(Platform.getInstance().isIOS()) {return;}
        this.waitElementAndClick(getElementByText("OK"),
                "Can not click OK",
                15);
    }

    public void clickOnNavigateUp() {

        this.waitElementAndClick(NAVIGATE_UP,
                "Can not click on Navigate up",
                15);
    }

    public void selectFolderForSavingArticle(String nameFolder) {
        if(Platform.getInstance().isIOS()) {return;}
        this.waitElementAndClick(getElementByText(nameFolder),
                "Can not click on folder name " + nameFolder,
                15);
    }


}
