package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_ARTICLES = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_RESULT_BY_TITLE_DESCRIPTION = "xpath://*[@resource-id='org.wikipedia:id/fragment_search_results']//*[@text='{TITLE}']/../*[@text='{DESCRIPTION}']";
    }
    public AndroidSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
