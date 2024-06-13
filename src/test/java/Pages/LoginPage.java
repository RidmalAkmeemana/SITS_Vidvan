package Pages;

import Infra.LoadEnv;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
    private WebDriver driver;
    @FindBy(id="username")
    WebElement userNameField;
    @FindBy(id="password")
    WebElement passwordField;

    @FindBy(id = "loginbtn")
    WebElement logInButton;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillField(WebElement field, String value)
    {
        field.sendKeys(value);
    }

    public String setEnv()
    {
        LoadEnv loadEnv = new LoadEnv();
        String env = loadEnv.getEnv();
        return env;
    }

    public String setUrl()
    {
        LoadEnv loadEnv = new LoadEnv();
        String url = loadEnv.getUrl();
        return url;
    }

    public void openLoginPage(String url) {
        driver.get(url);
    }

    private void makeElementsVisible() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("$('#login').show();");
    }

    public void fillUsername(String username) throws InterruptedException {
        this.makeElementsVisible();
        this.fillField(userNameField,username);
        Thread.sleep(2000);
    }

    public void fillPassword(String password) throws InterruptedException {
        this.makeElementsVisible();
        this.fillField(passwordField,password);
        Thread.sleep(2000);
    }

    public void clickOnLogIn() throws InterruptedException {
        this.makeElementsVisible();
        logInButton.click();
        Thread.sleep(2000);
    }
}
