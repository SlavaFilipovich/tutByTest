package tests;

import settings.ScreenConfig;
import settings.ScreenSettings;
import actions.Steps;
import actions.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import settings.BrowserConfig;
import web.driver.Driver;


public class AppleOnlinerTest {
    private WebDriver driver;
    private static String addressSite = "https://onliner.by";
    private static final String USERNAME_XPATH = "//a[@href='https://catalog.onliner.by/']";
    private static final String ELECTRONIC_XPATH = "//li[@data-id='1']";
    private static final String PHONES_MENU_XPATH = "//div[contains(text(), 'Мобильные')]";
    private static final String MOBILE_XPATH = "//div[contains(text(), 'Мобильные')]/following-sibling::div//a[@href='https://catalog.onliner.by/mobile']";
    private static final String CHOSE_APPLE_XPATH = "//div[@class='schema-filter__facet']/ul//span[text()= 'Apple']/parent::*//span[@class='i-checkbox']";
    private static final String PRICE_FIELD_APPLE_XPATH = "//div[@class='schema-product__group'][1]//div[@class='schema-product__price']//span";
    private static final String GOODS_FOUND = "//span[contains(text(),'Найдено')]";


    @BeforeClass
    public void preConditions(){
        Driver.initDriver(BrowserConfig.CHROME);
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        driver.get(addressSite);
        Utils.setTimeOuts(driver);
    }

    @Test
    public void applePriceOnlinerTest() {
        Steps.findElementAndClick(driver,USERNAME_XPATH);
        Steps.findElementAndClick(driver, ELECTRONIC_XPATH);
        Steps.findElementAndClick(driver, PHONES_MENU_XPATH);
        Steps.findElementAndClick(driver, MOBILE_XPATH);
        Steps.scrollToElementAndClick(driver, CHOSE_APPLE_XPATH);
        Utils.waitVisibilityOfElement(driver, GOODS_FOUND);

        String priceFirstMobile = driver.findElements(By.xpath(PRICE_FIELD_APPLE_XPATH)).get(0).getText();
        double currentPrice = Utils.convertStringsToDouble(priceFirstMobile);
        double samplePrice = Utils.convertStringsToDouble("599");
        Assert.assertTrue(String.format("Condition is not satisfied\r\nThe first Apple mobile price is: %.2f BYN. SamplePrice is %.2f BYN",currentPrice,samplePrice),
                currentPrice>samplePrice);
        System.out.println(String.format("The first Apple mobile price is: %.2f BYN. SamplePrice is %.2f BYN",currentPrice,samplePrice));
    }

    @AfterClass
    public static void postCondition() {
        Driver.destroy();
    }
}


