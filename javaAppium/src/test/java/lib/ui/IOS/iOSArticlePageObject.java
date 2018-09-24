package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        ADD_TO_READING_LIST = "id:Save for later";
        GOT_IT = "id:org.wikipedia:id/onboarding_button";
        INPUT_LIST_NAME = "id:org.wikipedia:id/text_input";
        ELEMENT_BY_TEXT = "id:'{SUBSTRING}'";
        CLOSE_BTN ="id:places auth close";
        NAVIGATE_UP = "id:Back";
    }
    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
