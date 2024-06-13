package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    private WebDriver driver;

    public DashboardPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle()
    {
        String Title = driver.getTitle();
        return Title;
    }

}
