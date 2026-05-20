package flows;
import managers.PageManager;
import pages.HomePage;
import pages.MainPage;


public class AuthFlow {
    private final PageManager pages;


    public AuthFlow(PageManager pages) {
        this.pages = pages;
    }

    public HomePage login() {
        return pages.getMainPage().goToLoginPage().signIn();
    }

    public MainPage logout(){
        return pages.getHomePage().signOut();
    }

    public HomePage register(){
        return pages.getMainPage().goToRegisterPage().createAccount();

    }
}