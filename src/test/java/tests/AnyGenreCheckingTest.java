package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import web.settings.ScreenConfig;
import web.settings.ScreenSettings;
import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import web.settings.BrowserConfig;
import web.driver.Driver;
import web.pages.AfishaPage;
import web.pages.MainTutPage;


public class AnyGenreCheckingTest {
    private WebDriver driver;
    private static String tutAddress = "https://tut.by";
    private static String requiredGenre = "Комедия";
    private static String checkSelection_Xpath = "//ul[@class='check-list']/li";
    private static String listOfFilms = "//div[@id='online-cinema']";
    private static String filmDescription = "//div[@id='online-cinema']//ul[1]/li[1]/div/p";
    private static AfishaPage afishaPage;
    private static MainTutPage mainTutPage;

    private static final Logger LOGGER = LogManager.getLogger(AnyGenreCheckingTest.class);


    @BeforeClass
    public void preConditions(){
        LOGGER.info("Initializing driver...");
        Driver.initDriver(BrowserConfig.CHROME);
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        afishaPage = new AfishaPage(driver);
        mainTutPage = new MainTutPage(driver);
        driver.get(tutAddress);
        Utils.setTimeOuts(driver);
    }

    @Test
    public void Scenario1() {
        mainTutPage.goToAfisha();
        afishaPage.goToOnlineCinemas();
        WebElement list = driver.findElement(By.xpath(listOfFilms));
        afishaPage.chooseGenresInFilms(requiredGenre);
        (new WebDriverWait (driver, 15)).until(ExpectedConditions.refreshed((ExpectedConditions.visibilityOf(list)))); //.numberOfElementsToBeMoreThan(By.xpath(checkSelection_Xpath), 0));
        Assert.assertTrue(afishaPage.elementIsDisplayed(filmDescription, requiredGenre));

    }

    @AfterClass
    public static void postCondition() {
        LOGGER.info("Destroying driver...");
        Driver.destroy();
    }
}


