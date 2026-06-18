package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MainPage {
    private Page page;
    private final Locator signInNavLink;
    private final Locator startShoppingBtn;
    private final Locator creatAccountBtn;
    private final Locator getStartedBtn;
    public MainPage(Page page){
        this.page = page;
        this.signInNavLink = page.locator(".btn-ghost.text-sm.py-2.px-4");
        this.startShoppingBtn = page.locator(".btn-primary.text-base.py-4.px-8.flex.items-center.gap-2.glow-red");
        this.creatAccountBtn = page.locator(".btn-ghost.text-base.py-4.px-8");
        this.getStartedBtn = page.locator(".btn-primary.text-sm.py-2.px-4");

    }
    public LoginPage goToLoginPage(){
        signInNavLink.click();
        return new LoginPage(page);
    }

    public HomePage goToHomepage(){
       startShoppingBtn.click();
        return new HomePage(page);
    }

    public RegisterPage goToRegisterPage(){
        creatAccountBtn.click();
        return new RegisterPage(page);
    }

}
