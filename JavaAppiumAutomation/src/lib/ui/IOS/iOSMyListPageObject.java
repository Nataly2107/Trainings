package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class iOSMyListPageObject extends MyListPageObject {
    static{
        MY_LIST = "id:Saved";
        ELEMENT_BY_TEXT = "id:{SUBSTRING}";
    }
    public iOSMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
