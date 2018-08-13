package JunitTest;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@SuiteClasses({AppTest.class})
@RunWith(Categories.class)
@IncludeCategory(MyCategories.PositiveTests.class)
public class PositiveSuite {

}
