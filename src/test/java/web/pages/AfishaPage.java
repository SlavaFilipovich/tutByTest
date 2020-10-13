package web.pages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AfishaPage extends InitializingPage{

    private static String filmGenre_Xpath = "//div[@id='tab-films']//ul//*[contains(text(),'%s')]";
    private static String serialGenre_Xpath = "//div[@id='tab-tv-series']//ul//*[contains(text(),'%s')]";
    private static String animationGenre_Xpath = "//div[@id='tab-animation']//ul//*[contains(text(),'%s')]";

    @FindBy(xpath = "//*[contains(text(), 'Онлайн-кинотеатры')]")
    private WebElement onlineCinemas;

    @FindBy(xpath = "//div[@id='tab-films']//*[contains(text(), 'Жанры')]")
    private WebElement genresOption1;

    @FindBy(xpath = "//div[@id='tab-tv-series']//*[contains(text(), 'Жанры')]")
    private WebElement genresOption2;

    @FindBy(xpath = "//div[@id='tab-animation']//*[contains(text(), 'Жанры')]")
    private WebElement genresOption3;

    @FindBy(xpath = "//a[@data-show='serial']")
    private WebElement serialsTab;

    @FindBy(xpath = "//a[@data-show='animation']")
    private WebElement animationTab;


    private static final Logger LOGGER = LogManager.getLogger(AfishaPage.class);

    public AfishaPage(WebDriver driver) {
        super(driver);
    }

    public void goToOnlineCinemas(){
        LOGGER.debug("Go to afisha.tut.by/online-cinema");
        actions.moveToElement(onlineCinemas).click().perform();
    }

    public void switchToSerialsTab(){
        LOGGER.debug("Switch to serials tab");
        actions.moveToElement(serialsTab).click().build().perform();
    }

    public void switchToAnimationTab(){
        LOGGER.debug("Switch to animation tab");
        actions.moveToElement(animationTab).click().perform();
    }

    public void chooseGenresInFilms(String word){
        LOGGER.debug("Click menu Genres");
        actions.moveToElement(genresOption1).click().perform();
        LOGGER.debug("Choosing genre "+word);
        driver.findElement(By.xpath(String.format(filmGenre_Xpath, word))).click();
    }

    public void chooseGenresInSerials(String word){
        LOGGER.debug("Click menu Genres");
        actions.moveToElement(genresOption2).click().perform();
        LOGGER.debug("Choosing genre "+word);
        driver.findElements(By.xpath(String.format(serialGenre_Xpath, word))).get(0).click();
    }

    public void chooseGenresInAnimation(String word){
        LOGGER.debug("Click menu Genres");
        actions.moveToElement(genresOption3).click().perform();
        LOGGER.debug("Choosing genre "+word);
        driver.findElement(By.xpath(String.format(animationGenre_Xpath, word))).click();
    }

    public boolean elementIsDisplayed(String xpath, String word){
        LOGGER.info("Checking element is Displayed...");
        WebElement element = driver.findElement(By.xpath(xpath));
        return element.getText().contains(word);
    }

}
