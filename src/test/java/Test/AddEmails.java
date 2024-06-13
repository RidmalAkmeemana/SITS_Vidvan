package Test;

import ErrorValidations.CoursePageErrorValidation;
import ErrorValidations.DashboardErrorValidation;
import Infra.BasePage;
import Infra.DataProviders;
import Pages.CoursePage;
import Pages.LoginPage;
import Pages.ManualEnrolmentsPage;
import Pages.ParticipantPage;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class AddEmails extends BasePage {

    @Test(priority = 1, description = "Verifying Credentials", dataProvider = "loginCredentials", dataProviderClass = DataProviders.class)
    public void logIntoSystem(String username, String password) throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("LOG IN TO SYSTEM");
        logger.info("-------------------------------------------------------\n");

        LoginPage loginPage = new LoginPage(driver);
        DashboardErrorValidation dashboardErrorValidation = new DashboardErrorValidation(driver);

        loginPage.openLoginPage(loginPage.setUrl());
        logger.info("REDIRECT TO: " +loginPage.setUrl());

        logger.info("ENTER USERNAME: " +username);
        loginPage.fillUsername(username);

        logger.info("ENTER PASSWORD: " +password);
        loginPage.fillPassword(password);

        logger.info("CLICK ON LOG IN BUTTON \n");
        loginPage.clickOnLogIn();

        dashboardErrorValidation.DashboardRedirectionValidation();

        logger.info("-------------------------------------------------------");
        logger.info("LOG IN PROCESSED");
        logger.info("-------------------------------------------------------\n");
    }

    @Test(priority = 2, description = "Get CourseID", dataProvider = "getCourseID", dataProviderClass = DataProviders.class)
    public void GoToCoursePage(String courseID) throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("GO TO COURSE PAGE");
        logger.info("-------------------------------------------------------\n");

        CoursePage coursePage = new CoursePage(driver);
        CoursePageErrorValidation coursePageErrorValidation = new CoursePageErrorValidation(driver);

        coursePage.goToCoursePage(coursePage.setUrl(), courseID);
        logger.info("COURSE ID: " +courseID);

        coursePageErrorValidation.CoursePageRedirection();

        logger.info("-------------------------------------------------------");
        logger.info("COURSE PAGE PROCESSED");
        logger.info("-------------------------------------------------------\n");

        logger.info("-------------------------------------------------------");
        logger.info("GO TO PARTICIPANTS PAGE");
        logger.info("-------------------------------------------------------\n");

        logger.info("CLICK ON NAVIGATION BUTTON");
        coursePage.clickOnNavigationBtn();

        logger.info("CLICK ON PARTICIPATION'S BUTTON");
        coursePage.clickOnParticipantBtn();

        logger.info("-------------------------------------------------------");
        logger.info("PARTICIPANTS PAGE PROCESSED");
        logger.info("-------------------------------------------------------\n");

        ParticipantPage participantPage = new ParticipantPage(driver);

        logger.info("CLICK ON PAGE SETTING BUTTON");
        participantPage.clickOnPageSettingBtn();

        logger.info("CLICK ON ENROLLEMTS METHODS");
        participantPage.clickOnEnrolmentMethods();

        logger.info("CLICK ON ADD BUTTON");
        participantPage.clickOnAddButton();
    }

    @Test(priority = 3, description = "Add Emails", dataProvider = "getEmails", dataProviderClass = DataProviders.class)
    public void AddEmails(String ID, String Email) throws InterruptedException {

        logger.info("-------------------------------------------------------");
        logger.info("START TO ADD EMAILS");
        logger.info("-------------------------------------------------------\n");

        ManualEnrolmentsPage manualEnrolmentsPage = new ManualEnrolmentsPage(driver);

        logger.info("ID: " +ID);
        logger.info("ENTER EMAIL: " +Email);
        manualEnrolmentsPage.fillEmail(Email);

        String userCountStr = manualEnrolmentsPage.getUserCount();
        int userCount = Integer.parseInt(userCountStr);

        logger.info("USER COUNT: " +userCount);

        if (userCount == 0) {

            logger.info("CLEAR THE EMAIL FIELD");
            manualEnrolmentsPage.clickOnClearBtn();

            logger.info("NO USER AVAILABLE");
            throw new SkipException("No User Results");
        }

        for (int i = 0; i < userCount; i++) {
            manualEnrolmentsPage.clickOnDownBtn();
        }

        logger.info("ADD THE USER");
        manualEnrolmentsPage.clickOnEnterBtn();

        logger.info("CLEAR THE EMAIL FIELD");
        manualEnrolmentsPage.clickOnClearBtn();

        logger.info("-------------------------------------------------------");
        logger.info("EMAIL ADDING COMPLETED");
        logger.info("-------------------------------------------------------\n");
    }

}
