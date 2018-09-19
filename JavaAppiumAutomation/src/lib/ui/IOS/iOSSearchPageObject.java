package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {
    static{
                SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
                SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
                SEARCH_ARTICLES = "xpath://XCUIElementTypeLink";
                SEARCH_RESULT_BY_TITLE_DESCRIPTION = "xpath:xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]/../*[contains(@name,'{DESCRIPTION}')]";
    }
    public iOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
