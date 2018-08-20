import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\Test\\learning\\Trainings\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testValidateSearch()
    {
        waitElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can not find Search Wikipedia field",
                5);
        validateSearchTextInField();
    }

    private void waitElementAndClick(By by, String errorMessage, long timeout){
        waitElement(by, errorMessage, timeout);
        driver.findElement(by).click();
    }
    private WebElement waitElement(By by, String errorMessage, long timeout){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.withMessage(errorMessage+"\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private void validateSearchTextInField(){
        Assert.assertEquals(waitElement(By.id("org.wikipedia:id/search_src_text"),
                "Can not find Search field",
                10)
                .getText(),"Search…");
    }
}
