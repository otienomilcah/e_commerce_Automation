package pages;

import com.microsoft.playwright.Page;
import java.util.ArrayList;
import java.util.List;

// Page Object Model for products (inventory) page
 
public class ProductsPage {

    private Page page;

    public ProductsPage(Page page) {
        this.page = page;
    }

   //Get Products page title
    
    public String getTitle() {
        return page.locator(".title").innerText();
    }

   // Sort products by Price: Low → High
     
    public void sortProductsLowToHigh() {
        page.locator(".product_sort_container").selectOption("lohi");
    }

    // Get all product prices as double list
     
    public List<Double> getAllProductPrices() {
        List<String> priceStrings = page.locator(".inventory_item_price").allInnerTexts();
        List<Double> prices = new ArrayList<>();
        for (String s : priceStrings) {
            prices.add(Double.parseDouble(s.replace("$", "")));
        }
        return prices;
    }

    // Get first product price
     
    public double getFirstProductPrice() {
        String priceText = page.locator(".inventory_item_price").first().innerText();
        return Double.parseDouble(priceText.replace("$", ""));
    }

    // Add first N products to cart
     
    public void addFirstNProductsToCart(int n) {
        for (int i = 0; i < n; i++) {
            page.locator(".inventory_item button").nth(i).click();
        }
    }

   // Get cart badge count
    
    public int getCartBadgeCount() {
        String badge = page.locator(".shopping_cart_badge").innerText();
        return badge.isEmpty() ? 0 : Integer.parseInt(badge);
    }

    //Open cart page
   
    public void openCart() {
        page.locator(".shopping_cart_link").click();
    }
}