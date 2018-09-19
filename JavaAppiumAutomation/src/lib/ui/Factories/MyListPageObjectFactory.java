package lib.ui.Factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidMyListPageObject;
import lib.ui.IOS.iOSMyListPageObject;
import lib.ui.MyListPageObject;

public class MyListPageObjectFactory {
    public static MyListPageObject get(AppiumDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        }else {
            return new iOSMyListPageObject(driver);
        }
    }
}
