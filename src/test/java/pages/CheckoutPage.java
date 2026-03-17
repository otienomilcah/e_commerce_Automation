package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage {

    private Page page;

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void fillCheckoutForm(String firstName, String lastName, String zip) {
        page.locator("#first-name").fill(firstName);
        page.locator("#last-name").fill(lastName);
        page.locator("#postal-code").fill(zip);
    }

    public void clickContinue() {
        page.locator("#continue").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.locator("#continue").click();
    }

    public List<Double> getItemPrices() {
        List<String> priceStrings = page.locator(".inventory_item_price").allInnerTexts();
        List<Double> prices = new ArrayList<>();
        for (String s : priceStrings) {
            prices.add(Double.parseDouble(s.replace("$", "")));
        }
        return prices;
    }

    public double getDisplayedTotal() {
        page.locator(".summary_total_label")
            .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        String totalText = page.locator(".summary_total_label").innerText(); // e.g., "Total: $43.18"
        return Double.parseDouble(totalText.split("\\$")[1]);
    }

    public void clickFinish() {
        page.locator("#finish")
            .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.locator("#finish").click();
    }

    public String getConfirmationMessage() {
        page.locator(".complete-header")
            .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return page.locator(".complete-header").innerText();
    }
}