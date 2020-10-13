package web.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class InitializingPage {
    public WebDriver driver;
    public Actions actions;

    private static final Logger LOGGER = LogManager.getLogger(InitializingPage.class);

    public InitializingPage(WebDriver driver){
        this.driver = driver;
        actions = new Actions(driver);
        PageFactory.initElements(this.driver, this);
    }
}
