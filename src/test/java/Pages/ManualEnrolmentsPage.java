package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManualEnrolmentsPage {

    private WebDriver driver;
    @FindBy(id="addselect_searchtext")
    WebElement selectUser;

    @FindBy(id="addselect")
    WebElement selectBox;

    @FindBy(xpath = "//optgroup[contains(@label, 'Matching not enrolled users')]")
    WebElement resultsCount;

    @FindBy(id = "addselect_clearbutton")
    WebElement clearBtn;

    private static String extractNumberFromString(String input) {
        // Regular expression to match the number within parentheses
        Pattern pattern = Pattern.compile("\\((\\d+)\\)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "No number found";
        }
    }

    public ManualEnrolmentsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillField(WebElement field, String value)
    {
        field.sendKeys(value);
    }

    public void fillEmail(String email) throws InterruptedException {
        this.fillField(selectUser,email);
        Thread.sleep(2000);
    }

    public String getUserCount() throws InterruptedException {

        try {
            selectBox.click();
            Thread.sleep(2000);

            String optgroupLabel = resultsCount.getAttribute("label");
            String numericValue = extractNumberFromString(optgroupLabel);
            return numericValue;

        } catch (NoSuchElementException e) {
            return "0";
        }

    }

    public void clickOnDownBtn() throws InterruptedException {
        selectBox.sendKeys(Keys.DOWN);
        Thread.sleep(2000);
    }

    public void clickOnEnterBtn() throws InterruptedException {
        selectBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    public void clickOnClearBtn() throws InterruptedException {
        clearBtn.click();
        Thread.sleep(2000);
    }

}
