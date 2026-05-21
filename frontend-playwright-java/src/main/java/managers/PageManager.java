package managers;

import com.microsoft.playwright.Page;
import pages.*;

public class PageManager {
    private final Page page;
    private MainPage mainPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private RegisterPage registerPage;
    private ProductPage productPage;
    private FlashPage flashPage;
    private FeaturePage featurePage;
    private WishListPage wishListPage;

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

    public ProductPage getProductPage() {
        if (productPage == null){
            productPage = new ProductPage(page);
        }
        return productPage;
    }
    public FlashPage getFlashPage(){
        if (flashPage == null){
            flashPage = new FlashPage(page);
        }
        return new FlashPage(page);
    }

    public FeaturePage getFeaturePage(){
        if (featurePage == null){
            featurePage = new FeaturePage(page);
        }
        return featurePage;
    }

    public WishListPage getWishListPage(){
        if (wishListPage == null){
            wishListPage = new WishListPage(page);
        }
        return wishListPage;
    }
}