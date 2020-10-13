package web.pages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AfishaPage extends InitializingPage{

    private String genreXpath = "//div[@id='tab-films']//select/option[text()= %s]";

    @FindBy(xpath = "//*[contains(text(), 'Онлайн-кинотеатры')]")
    private WebElement onlineCinemas;

    @FindBy(xpath = "//div[@id='tab-films']//*[contains(text(), 'Жанры')]")
    private WebElement genresOption;

    @FindBy(xpath = "//input[@type = 'email']")
    private WebElement loginField;



    private static final Logger LOGGER = LogManager.getLogger(AfishaPage.class);

    public AfishaPage(WebDriver driver) {
        super(driver);
    }

    public void goToAfishaTutBy(){
        LOGGER.debug("I go to afisha.tut.by");
        driver.get("https://afisha.tut.by/");
    }

    public void goToOnlineCinemas(){
        LOGGER.debug("Go to afisha.tut.by/online-cinema");
        actions.moveToElement(onlineCinemas).click().perform();
    }

    public void goToGenres(){
        LOGGER.debug("Click menu Genres");
        actions.moveToElement(genresOption).click().perform();
    }

    public void chooseParticularGenre(String word){
        driver.findElement(By.xpath(String.format(genreXpath, word))).click();
    }

    public boolean elementIsDisplayed(String xpath, String word){
        WebElement element = driver.findElement(By.xpath(xpath));
        LOGGER.info(element.getText());
        return element.getText().contains(word);
    }


}
