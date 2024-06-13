package Pages;

import Infra.LoadEnv;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;

public class CoursePage {

    private WebDriver driver;

    @FindBy(css=".notification_icon > .flaticon-settings")
    WebElement NavigationBtn;

    @FindBy(linkText = "Participants")
    WebElement ParticipantsBtn;

    private String formatBigDecimal(String value) {
        return new BigDecimal(value).stripTrailingZeros().toPlainString();
    }

    public CoursePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String setUrl()
    {
        LoadEnv loadEnv = new LoadEnv();
        String url = loadEnv.getUrl();
        return url;
    }

    public void goToCoursePage(String url, String courseID) {

        String baseURL = url.substring(0, url.indexOf("/login/"));
        String courseURL = baseURL+"/course/view.php?id="+formatBigDecimal(courseID);
        driver.get(courseURL);
    }

    public void clickOnNavigationBtn() throws InterruptedException {

        NavigationBtn.click();
        Thread.sleep(2000);
    }

    public void clickOnParticipantBtn() throws InterruptedException {
        ParticipantsBtn.click();
        Thread.sleep(2000);
    }

}
