package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {
    public Page page;
    private final Locator usertIcn;
    private final Locator logoutBtn;


    public HomePage(Page page){

        this.page = page;
        this.usertIcn = page.locator(".lucide.lucide-user");
        this.logoutBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign out"));
    }
    public MainPage signOut(){
        usertIcn.click();
        logoutBtn.click();
        return new MainPage(page);
    }

}
