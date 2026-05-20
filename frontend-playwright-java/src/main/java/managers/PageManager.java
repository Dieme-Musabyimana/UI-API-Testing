package managers;

import com.microsoft.playwright.Page;
import pages.HomePage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

public class PageManager {
    private final Page page;
    private MainPage mainPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private RegisterPage registerPage;

    public PageManager(Page page) {
        this.page = page;
    }

    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new MainPage(page);
        }
        return mainPage;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(page);
        }
        return loginPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(page);
        }
        return homePage;
    }

    public RegisterPage getRegisterPage() {
        if (registerPage == null) {
            registerPage = new RegisterPage(page);
        }
        return registerPage;
    }

}