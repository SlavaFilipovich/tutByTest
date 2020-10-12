package web.pages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AfishaPage extends InitializingPage{

    @FindBy(xpath = "//*[contains(text(), 'Онлайн-кинотеатры')]")
    private WebElement onlineCinemas;

    @FindBy(xpath = "//button[@type = 'reset' and @opacity]")
    private WebElement buttonSearch;

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
        actions.moveToElement(onlineCinemas).click().perform();
    }


    public void searchFilms(String word) throws InterruptedException {
        LOGGER.debug("Searching films by name...");
        actions.moveToElement(resetButtons.get(1))
                .moveToElement(searchingField)
                .click()
                .sendKeys(word)
                .sendKeys(Keys.ENTER)
                .build().perform();
        Thread.sleep(3000);
    }


}
