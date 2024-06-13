package ErrorValidations;

import Infra.BasePage;
import Pages.DashboardPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardErrorValidation extends DashboardPage {

    public static Logger logger = LogManager.getLogger(BasePage.class);

    private WebDriver driver;

    public DashboardErrorValidation(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
    }

    public void DashboardRedirectionValidation()
    {
        DashboardPage dashboardPage = new DashboardPage(driver);
        String Actual_Title = dashboardPage.getTitle();
        String Expected_Title = "Dashboard";

        if(Actual_Title.equals(Expected_Title))
        {
            logger.info("USER LOG IN SUCCESSFULLY\n");
        }
        else
        {
            logger.info("USER LOGIN FAILED\n");
        }
        Assert.assertEquals(Actual_Title, Expected_Title, "USER LOGIN FAILED");
    }

}
