package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_ARTICLES = "//*[@resource-id='org.wikipedia:id/page_list_item_container']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        this.waitElementAndClick(By.id(SEARCH_INIT_ELEMENT),
                "Can not find and Click Search init element",
                15);
        this.waitElement(By.id(SEARCH_INIT_ELEMENT),
                "Can not find search input after clicking search init element",
                15);
    }

    public void typeSearchLine(String search_line) {
        this.waitElementAndSendKey(By.id(SEARCH_INPUT),
                search_line,
                "Can not click and type search word in input field",
                15);
    }

    public void waitForSearchResult(String substring) {
        String searchResult = getResultSearchElement(substring);
        this.waitElement(By.xpath(SEARCH_RESULT_BY_SUBSTRING_TPL),
                "Can not find search result with substring " + substring,
                15);
    }

    public void openArticle(String article) {
        String searchResult = getResultSearchElement(article);
        this.waitElementAndClick(By.xpath(SEARCH_RESULT_BY_SUBSTRING_TPL),
                "Can not find search result with substring " + article,
                15);
    }

    public boolean validateSearchResult(String searchText) {
        boolean hasWord = true;
        List<WebElement> elements = driver.findElements(By.xpath(SEARCH_ARTICLES));
        for (WebElement article :
                elements) {
            if (!this.validateSearchWordInArticle(article, searchText)) {
                hasWord = false;
                System.out.print(this.validateSearchWordInArticle(article, searchText));
            }
        }
        return hasWord;
    }
}
