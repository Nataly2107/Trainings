package lib.Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {
    @Test
    public void Ex10(){
        if(Platform.getInstance().isAndroid()){
            return;
        }
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
