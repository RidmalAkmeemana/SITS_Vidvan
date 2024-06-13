package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParticipantPage {

    private WebDriver driver;

    @FindBy(id="region-main-settings-menu")
    WebElement PageSettingBtn;

    @FindBy(linkText = "Enrolment methods")
    WebElement EnrolmentMethods;

    @FindBy(css = ".fa-user-plus")
    WebElement AddBtn;

    public ParticipantPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnPageSettingBtn() throws InterruptedException {
        PageSettingBtn.click();
        Thread.sleep(2000);
    }

    public void clickOnEnrolmentMethods() throws InterruptedException {
        EnrolmentMethods.click();
        Thread.sleep(2000);
    }

    public void clickOnAddButton() throws InterruptedException {
        AddBtn.click();
        Thread.sleep(2000);
    }

}
