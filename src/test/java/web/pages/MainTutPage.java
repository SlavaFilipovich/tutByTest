package web.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainTutPage extends InitializingPage{

    @FindBy(xpath = "//ul[@class='b-topbar-i']//*[contains(text(), 'Афиша')]")
    private WebElement afishaTab;

    private static final Logger LOGGER = LogManager.getLogger(MainTutPage.class);

    public MainTutPage(WebDriver driver) {
        super(driver);
    }


    public void goToAfisha(){
        LOGGER.debug("Go to afisha.tut.by");
        actions.moveToElement(afishaTab).click().perform();
    }
}
