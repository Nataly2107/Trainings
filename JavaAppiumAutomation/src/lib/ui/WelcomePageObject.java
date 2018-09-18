package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
    LEARN_MORE_ABOUT_WIKI="id:Learn more about Wikipedia",
    NEXT="id:Next",
    NEW_WAY_TO_EXPLORE="id:New ways to explore",
    ADD_EDIT_PREFERRED_LANG="id:Add or edit preferred languages",
    LEARN_MORE_ABOUT_DATA_COLLECTED="id:Learn more about data collected",
    GET_STARTED="id:Get started";

    public WelcomePageObject(AppiumDriver driver){
        super(driver);
    }
    public void waitForLearnMoreLink(){
        this.waitElement(LEARN_MORE_ABOUT_WIKI,"Can not find Learn more about Wikipedia link",10);

    }
    public void clickNextBtn(){
        this.waitElementAndClick(NEXT,"Can not find Next button",10);

    }
    public void waitForNewWayToExploreText(){
        this.waitElement(NEW_WAY_TO_EXPLORE,"Can not find New ways to explore ",10);

    }
    public void waitForAddOrEditPreferredLangText(){
        this.waitElement(ADD_EDIT_PREFERRED_LANG,"Can not find dd or edit preferred languages ",10);

    }
    public void waitForLearnMoreAboutDataCollectedText(){
        this.waitElement(LEARN_MORE_ABOUT_DATA_COLLECTED,"Can not find Learn more about data collected",10);

    }
    public void clickGetStartedBtn(){
        this.waitElementAndClick(GET_STARTED,"Can not find Get started button",10);

    }


}
