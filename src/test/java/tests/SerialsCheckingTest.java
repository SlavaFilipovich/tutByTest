package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Utils;
import web.driver.Driver;
import web.pages.AfishaPage;
import web.settings.BrowserConfig;
import web.settings.ScreenConfig;
import web.settings.ScreenSettings;

public class SerialsCheckingTest {
    private WebDriver driver;
    private static String afishaTutAddress = "https://afisha.tut.by/online-cinema/";
    private static String requiredGenre = "Детектив";
    private static String listOfFilms = "//div[@id='online-cinema']";
    private static String checkSelection_Xpath = "//ul[@class='check-list']/li";
    private static String filmDescription = "//div[@id='online-cinema']//ul[1]/li[1]/div/p";
    private static AfishaPage afishaPage;

    private static final Logger LOGGER = LogManager.getLogger(SerialsCheckingTest.class);


    @BeforeClass
    public void preConditions(){
        LOGGER.info("Initializing driver...");
        Driver.initDriver(BrowserConfig.CHROME);
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        afishaPage = new AfishaPage(driver);
        driver.get(afishaTutAddress);
        Utils.setTimeOuts(driver);
    }

    @Test
    public void Scenario2() {
        afishaPage.switchToSerialsTab();
        WebElement list = driver.findElement(By.xpath(listOfFilms));
        afishaPage.chooseGenresInSerials(requiredGenre);
        (new WebDriverWait (driver, 15)).until(ExpectedConditions.refreshed((ExpectedConditions.visibilityOf(list))));
        LOGGER.info("Number of elements = "+driver.findElements(By.xpath(checkSelection_Xpath)).size());
        Assert.assertTrue(afishaPage.elementIsDisplayed(filmDescription, requiredGenre));

    }

    @AfterClass
    public static void postCondition() {
        LOGGER.info("Destroying driver...");
        Driver.destroy();
    }
}
