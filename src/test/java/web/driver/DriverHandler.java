package web.driver;

import settings.BrowserConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;


public class DriverHandler {

    public static WebDriver getDriver(BrowserConfig browserConfig) throws Exception {
    switch (browserConfig) {
        case CHROME:
            return getChromeDriver();
        case IE:
            return getIEDriver();
        case EDGE:
            return getEDGEDriver();
        case OPERA:
            return getOperaDriver();
        case FIREFOX:
           return getFirefoxDriver();
        default:
            throw new Exception("Unexpected Configuration of Driver");
    }
    }

    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty("webdriver.chrome.driver", "src/test/java/executable_files/chromedriver.exe");
        return new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.silentOutput", "true");
        System.setProperty("webdriver.gecko.driver", "src/test/java/executable_files/geckodriver.exe");
        return new FirefoxDriver();
    }

    private static WebDriver getOperaDriver() {
        System.setProperty("webdriver.opera.silentOutput", "true");
        System.setProperty("webdriver.opera.driver", "src/test/java/executable_files/operadriver.exe");
        return new OperaDriver();
    }

    private static WebDriver getEDGEDriver() {
        //System.setProperty("webdriver.chrome.silentOutput", "true");
        //System.setProperty("webdriver.chrome.driver", "src/test/java/executable_files/chromedriver.exe");
        return new ChromeDriver();
    }

    private static WebDriver getIEDriver() {
        //System.setProperty("webdriver.chrome.silentOutput", "true");
        //System.setProperty("webdriver.chrome.driver", "src/test/java/executable_files/chromedriver.exe");
        return new ChromeDriver();
    }

}
