package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import managers.PageManager;

public class FlashPage {
    private PageManager pages;
    private final Locator flashPageHeading;

  public FlashPage(Page page){
      this.pages = new PageManager(page);
      this.flashPageHeading = page.locator("h1[class='font-display font-bold text-2xl md:text-3xl text-white']");
  }

  public String getFlashPageHeading(){
      return flashPageHeading.textContent();
  }
}