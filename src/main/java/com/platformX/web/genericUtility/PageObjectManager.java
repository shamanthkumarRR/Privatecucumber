package com.platformX.web.genericUtility;

import com.platformX.web.pageObjects.*;
import com.platformX.web.pageObjects.pageCuration.PageCuration;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    public WebDriver driver;
    public LoginPage loginPageObj;
    public MyDashboardPage dashboardPageObj;
    public PollContentPage pollPageObj;
    public ArticleContentPage articleContentPageObj;
    public QuizContentPage quizContentPageObj;
    public UserRegistrationPage userRegistrationPageObj;
    public PageCuration pageObj;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPageObj() {
        loginPageObj = new LoginPage(driver);
        return loginPageObj;
    }

    public MyDashboardPage getMyDashboardPageObj() {
        dashboardPageObj = new MyDashboardPage(driver);
        return dashboardPageObj;
    }

    public ArticleContentPage getArticlePageObj() {
        articleContentPageObj = new ArticleContentPage(driver);
        return articleContentPageObj;
    }

    public QuizContentPage getQuizPageObj() {
        quizContentPageObj = new QuizContentPage(driver);
        return quizContentPageObj;
    }

    public PollContentPage getPollPageObj() {
        pollPageObj = new PollContentPage(driver);
        return pollPageObj;
    }

    public UserRegistrationPage getUserRegistrationPageObj() {
        userRegistrationPageObj = new UserRegistrationPage(driver);
        return userRegistrationPageObj;
    }

    public PageCuration getPageCurationObj() {
        pageObj = new PageCuration(driver);
        return  pageObj;
    }


}
