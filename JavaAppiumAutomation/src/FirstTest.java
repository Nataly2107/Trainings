import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.MyListPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

public class FirstTest extends CoreTestCase {

    private MainPageObject mainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testEx2() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        mainPageObject.assertElementHasText(
                mainPageObject.waitElement(
                        By.id("org.wikipedia:id/search_src_text"),
                        "Can not find Search field",
                        5),
                "Search…");
    }

    @Test
    public void testEx3() {
        String searchText = "Android";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.waitForSearchResult(searchText);
    }

    @Test
    public void testEx4() {
        String searchText = "Appium";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        Assert.assertTrue(searchPageObject.validateSearchResult(searchText));

    }

    @Test
    public void testEx5() {
        String searchText = "Appium";
        String nameFolder = "Learning programming";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        MyListPageObject myListPageObject = new MyListPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(searchText);
        searchPageObject.openArticle(searchText);
        articlePageObject.clickOnMoreOptions();
        articlePageObject.clickOnAddReadingList();
        articlePageObject.clickOnGotIt();
        articlePageObject.typeNameForList(nameFolder);
        articlePageObject.clickOnOK();
        articlePageObject.clickOnNavigateUp();

        searchPageObject.initSearchInput();
        searchPageObject.searchText("Java");
        searchPageObject.openArticle("Java (programming language)");
        articlePageObject.clickOnMoreOptions();
        articlePageObject.clickOnAddReadingList();
        articlePageObject.selectFolderForSavingArticle(nameFolder);
        articlePageObject.clickOnNavigateUp();

        myListPageObject.openMyList();
        myListPageObject.openListByName(nameFolder);
        myListPageObject.deleteArticleFromList(searchText);
        myListPageObject.validateArticleIsAbsent(searchText);
        myListPageObject.openListByName("Java (programming language)");

        String title = mainPageObject.waitElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find title article",
                15
        );
        Assert.assertEquals("Article title are not equals", "Java (programming language)", title);


    }

    @Test
    public void testEx6() {
        String searchText = "Android";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchText("Java");
        searchPageObject.openArticle("Java (programming language)");
        articlePageObject.validateTitleInArticleIsPresent();
    }


    @Test
    public void testEx7() {
        String searchText = "Java";
        String description = "Java (programming language)";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchText(searchText);
        searchPageObject.waitForSearchResult(description);
        String title_before_rotation = mainPageObject.waitElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find title article and get text",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = mainPageObject.waitElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find title article and get text",
                15
        );
        Assert.assertEquals("Article title are not equals", title_after_rotation, title_before_rotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = mainPageObject.waitElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can not find title article and get text",
                15
        );
        Assert.assertEquals("Article title are not equals", title_after_second_rotation, title_before_rotation);
    }

    @Test
    public void testEx9() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.searchText("Java");
        searchPageObject.waitForElementByTitleAndDescription(
                "Java (programming language)",
                "Object-oriented programming language");
        searchPageObject.waitForElementByTitleAndDescription(
                "Java",
                "Island of Indonesia");
        searchPageObject.waitForElementByTitleAndDescription(
                "JavaScript",
                "Programming language");
    }
}
