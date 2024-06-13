package ErrorValidations;

import Infra.BasePage;
import Infra.ExtentReportListener;
import Pages.CoursePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CoursePageErrorValidation extends CoursePage {

    public static Logger logger = LogManager.getLogger(BasePage.class);

    private WebDriver driver;
    public CoursePageErrorValidation(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void CoursePageRedirection()
    {
        logger.info("COURSE PAGE LOADED SUCCESSFULLY\n");
        ExtentReportListener.setStatus("Page Load Success");
    }

}
