package Infra;

import Pages.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class ExtentReportListener implements ITestListener
{
    private static ExtentReports extent;

    private static String status;

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private WebDriver driver;

    public static void setStatus(String ErrorStatus) {
        status = ErrorStatus;
    }

    public void onStart(ITestContext context) {

        LoginPage loginPage = new LoginPage(driver);
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Environment", "Vidvan");
        extent.setSystemInfo("Description", "Smoke Testing");
        extent.setSystemInfo("Environment", loginPage.setEnv() + " | Vidvan");
        extent.setSystemInfo("APP URL", loginPage.setUrl());
        extent.setSystemInfo("Name of QA", "SITS QA Team");
        extent.setSystemInfo("OS", System.getProperty("os.name"));

        sparkReporter.config().setDocumentTitle("Report");
        sparkReporter.config().setReportName("Email Adding");
        sparkReporter.config().setTheme(Theme.DARK);
        //sparkReporter.config().setTimelineEnabled(true);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public void onTestStart(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String Scenario = String.format(Arrays.toString(result.getParameters()));

        String RemoveOpenBracket = Scenario.replace("[","");
        String RemoveCloseBracket = RemoveOpenBracket.replace("]", "");

        String[] ScenarioParts = RemoveCloseBracket.split(",");

        String ScenarioID = ScenarioParts[0];

        if(Scenario.equals("[]"))
        {
            String testName = methodName;

            ExtentTest extentTest = extent.createTest(testName);
            test.set(extentTest);
        }
        else
        {
            String testName = methodName + " - " +ScenarioID;

            ExtentTest extentTest = extent.createTest(testName);
            test.set(extentTest);
        }
    }

    public void onTestSuccess(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String Scenario = String.format(Arrays.toString(result.getParameters()));

        String Status = status;

        DataList dataList = new DataList();
        String scenario = dataList.setJSON(methodName, Scenario, Status);

        test.get().info("<b>"+methodName+"</b>");
        test.get().info(MarkupHelper.createCodeBlock(scenario, CodeLanguage.JSON));
        test.get().pass(MarkupHelper.createLabel("TEST PASSED", ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String Scenario = String.format(Arrays.toString(result.getParameters()));
        String Status = status;

        DataList dataList = new DataList();
        String scenario = dataList.setJSON(methodName, Scenario, Status);

        test.get().info("<b>"+methodName+"</b>");
        test.get().info(MarkupHelper.createCodeBlock(scenario, CodeLanguage.JSON));
        test.get().fail(MarkupHelper.createLabel("TEST FAILED", ExtentColor.RED));
        test.get().fail(result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        String Scenario = String.format(Arrays.toString(result.getParameters()));
        String Status = status;

        DataList dataList = new DataList();
        String scenario = dataList.setJSON(methodName, Scenario, Status);

        test.get().info("<b>"+methodName+"</b>");
        test.get().info(MarkupHelper.createCodeBlock(scenario, CodeLanguage.JSON));
        test.get().skip(MarkupHelper.createLabel("TEST SKIPPED", ExtentColor.ORANGE));
        test.get().skip(result.getThrowable());
    }
}
