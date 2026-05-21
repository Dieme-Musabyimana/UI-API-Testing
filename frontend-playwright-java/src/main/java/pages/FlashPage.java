package pages;

import com.microsoft.playwright.Page;
import managers.PageManager;

public class FlashPage {
    private PageManager pages;

  public FlashPage(Page page){
      this.pages = new PageManager(page);
  }
}
