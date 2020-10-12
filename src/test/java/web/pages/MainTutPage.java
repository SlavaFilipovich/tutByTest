package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainTutPage extends InitializingPage{

    @FindBy(xpath = "//ul[@class='b-topbar-i']//*[contains(text(), 'Афиша')]")
    private WebElement afishaTab;

    public MainTutPage(WebDriver driver) {
        super(driver);
    }


    public void goToAfisha(){
        actions.moveToElement(afishaTab).click().perform();
    }
}
