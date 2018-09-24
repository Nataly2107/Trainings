package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject {
    static {
        MY_LIST = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
        ELEMENT_BY_TEXT = "xpath://*[@text='{SUBSTRING}']";
    }

    public AndroidMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
