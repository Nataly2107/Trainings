package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static{
        MORE_OPTIONS = "xpath://android.widget.ImageView[@content-desc='More options']";
                ADD_TO_READING_LIST = "xpath://*[@text='Add to reading list']";
                GOT_IT = "id:org.wikipedia:id/onboarding_button";
                INPUT_LIST_NAME = "id:org.wikipedia:id/text_input";
                ELEMENT_BY_TEXT = "xpath://*[@text='{SUBSTRING}']";
                NAVIGATE_UP = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                ARTICLE_TITLE = "xpath://*[@resource-id='org.wikipedia:id/view_page_title_text']";
    }
    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
