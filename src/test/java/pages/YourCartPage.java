package pages;

import com.microsoft.playwright.Page;
import java.util.List;

// Page Object Model for cart page
 
public class YourCartPage {

    private Page page;

    public YourCartPage(Page page) {
        this.page = page;
    }

    // Get all item names in cart
     
    public List<String> getCartItemNames() {
        return page.locator(".inventory_item_name").allInnerTexts();
    }

   // Click checkout
   
    public void clickCheckout() {
        page.locator("#checkout").click();
    }
}