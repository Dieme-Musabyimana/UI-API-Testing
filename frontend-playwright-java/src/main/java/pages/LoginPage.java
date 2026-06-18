package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import constants.Data;

public class LoginPage {
    private final Page page;
    private final Locator emailField;
    private final Locator passwordField;
    private final Locator singInBtn;


    public LoginPage(Page page){
        this.page = page;
        this.emailField = page.locator("input[placeholder='you@example.com']");
        this.passwordField = page.locator("input[placeholder='••••••••']");
        this.singInBtn = page.locator("button[type='submit']");

    }
    public HomePage signIn(){
        emailField.fill(Data.loginEmail);
        passwordField.fill(Data.loginPassword);
        singInBtn.click();
        page.waitForURL("**/home");
        return new HomePage(page);
    }
}