package lib.ui;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container",
            SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_ARTICLES = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_RESULT_BY_TITLE_DESCRIPTION = "xpath://*[@resource-id='org.wikipedia:id/fragment_search_results']//*[@text='{TITLE}']/../*[@text='{DESCRIPTION}']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    //TEMPLATE METHODS
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_DESCRIPTION.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    //
    public void initSearchInput() {
        this.waitElementAndClick(SEARCH_INIT_ELEMENT,
                "Can not find and Click Search init element",
                15);
        this.waitElement(SEARCH_INIT_ELEMENT,
                "Can not find search input after clicking search init element",
                15);
    }

    public void typeSearchLine(String search_line) {
        this.waitElementAndSendKey(SEARCH_INPUT,
                search_line,
                "Can not click and type search word in input field",
                15);
    }

    public void waitForSearchResult(String substring) {
        String searchResult = getResultSearchElement(substring);
        this.waitElement(SEARCH_RESULT_BY_SUBSTRING_TPL,
                "Can not find search result with substring " + substring,
                15);
    }

    public void openArticle(String article) {
        String searchResult = getResultSearchElement(article);
        this.waitElementAndClick(SEARCH_RESULT_BY_SUBSTRING_TPL,
                "Can not find search result with substring " + article,
                15);
    }

    public boolean validateSearchResult(String searchText) {
        boolean hasWord = true;
        List<WebElement> elements = driver.findElements(getLocatorByString(SEARCH_ARTICLES));
        for (WebElement article :
                elements) {
            if (!this.validateSearchWordInArticle(article, searchText)) {
                hasWord = false;
                System.out.print(this.validateSearchWordInArticle(article, searchText));
            }
        }
        return hasWord;
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String s = getResultSearchElementByTitleDescription(title, description);
        this.waitElement(getResultSearchElementByTitleDescription(title, description),
                "Can not find article with title " + title + " and decription " + description,
                25);

    }
}
