package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import constants.Data;

public class RegisterPage {
    private Page page;
    public final Locator firstNameFld;
    public final Locator secondNameFld;
    public final Locator emailFld;
    public final Locator passwordFld;
    public final Locator registerBtn;


    public RegisterPage(Page page){
        this.page = page;
        this.firstNameFld = page.locator("input[placeholder='John']");
        this.secondNameFld = page.locator("input[placeholder='Doe']");
        this.emailFld = page.locator("input[placeholder='you@example.com']");
        this.passwordFld = page.locator("input[placeholder='Min. 8 characters']");
        this.registerBtn = page.locator("button[type='submit']");

        }

    public HomePage createAccount(){
        firstNameFld.fill(Data.FirstName);
        secondNameFld.fill(Data.LastName);
        emailFld.fill(Data.EMAIL);
        passwordFld.fill(Data.PASSWORD);
        registerBtn.click();
        page.waitForURL("**/home");
        return new HomePage(page);
    }

    public void registerWithEmptyFld(){
        firstNameFld.fill("");
        secondNameFld.fill(Data.LastName);
        emailFld.fill(Data.EMAIL);
        passwordFld.fill(Data.PASSWORD);
        registerBtn.click();
        page.waitForTimeout(500);
    }

    public void registerWithInvEml(){
        firstNameFld.fill(Data.FirstName);
        secondNameFld.fill(Data.LastName);
        emailFld.fill(Data.invEmail);
        passwordFld.fill(Data.PASSWORD);
        registerBtn.click();
        page.waitForTimeout(500);
    }

    public String getErrorMsg(){
        return firstNameFld.evaluate("el => el.validationMessage").toString();
    }
}
