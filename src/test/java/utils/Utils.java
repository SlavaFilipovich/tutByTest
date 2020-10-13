package utils;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static void setTimeOuts(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
    }

}
