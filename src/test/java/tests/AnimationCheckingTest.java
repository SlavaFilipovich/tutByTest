package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import web.driver.Driver;
import web.pages.AfishaPage;
import web.settings.BrowserConfig;
import web.settings.ScreenConfig;
import web.settings.ScreenSettings;

public class AnimationCheckingTest {
    private WebDriver driver;
    private static String afishaTutAddress = "https://afisha.tut.by/online-cinema/";
    private static String requiredGenre = "Комедия";
    private static String checkGenres_Xpath = "//div[@id='online-cinema']//ul[1]/li[1]/div//*[contains(text(), %s)]";
    private static String filmDescription = "//div[@id='online-cinema']//ul[1]/li[1]/div";
    private static AfishaPage afishaPage;
    WebDriverWait wait;

    private static final Logger LOGGER = LogManager.getLogger(AnimationCheckingTest.class);


    @BeforeClass
    public void preConditions(){
        LOGGER.info("Initializing driver...");
        Driver.initDriver(BrowserConfig.CHROME);
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        afishaPage = new AfishaPage(driver);
        driver.get(afishaTutAddress);
        Utils.setTimeOuts(driver);
        wait = new WebDriverWait (driver, 15);
    }

    @Test
    public void Scenario3() {
        afishaPage.switchToAnimationTab();
        afishaPage.chooseGenresInAnimation(requiredGenre);
        wait.until(ExpectedConditions.elementToBeSelected(
                By.xpath(String.format("//div[@id='tab-animation']//select/option[text()= %s]", requiredGenre))));
        Assert.assertTrue(afishaPage.elementIsDisplayed(filmDescription, requiredGenre));

    }

    @AfterClass
    public static void postCondition() {
        LOGGER.info("Destroying driver...");
        Driver.destroy();
    }
}
