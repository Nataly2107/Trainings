package iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {
    @Test
    public void Ex10(){
        WelcomePageObject WelcomePage=new WelcomePageObject(driver);
        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextBtn();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextBtn();

        WelcomePage.waitForAddOrEditPreferredLangText();
        WelcomePage.clickNextBtn();

        WelcomePage.waitForLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedBtn();
    }
}
