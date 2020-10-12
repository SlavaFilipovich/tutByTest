package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import settings.ScreenConfig;
import settings.ScreenSettings;
import actions.Steps;
import actions.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import settings.BrowserConfig;
import web.driver.Driver;
import web.pages.AfishaPage;
import web.pages.MainTutPage;


public class TutbyTest {
    private WebDriver driver;
    private static String tutAddress = "https://tut.by";
    private static AfishaPage afishaPage;
    private static MainTutPage mainTutPage;
    WebDriverWait wait;

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
        afishaPage = new AfishaPage(driver);
        mainTutPage = new MainTutPage(driver);
        driver.get(tutAddress);
        Utils.setTimeOuts(driver);
        wait = new WebDriverWait (driver, 15);
    }

    @Test
    public void Scenario1() {
        mainTutPage.goToAfisha();



        wait.until(ExpectedConditions.titleIs("Software Testing Material - A site for Software Testers"));

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


