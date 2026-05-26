package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import constants.Data;

public class CheckoutPage {
    private final Page page;

    // --- Summary & Universal Controls ---
    private final Locator continueToCheckoutBtn;
    private final Locator grandTotalPrice;
    private final Locator shippingPrice;
    private final Locator subtotalPrice;
    private final Locator orderSummaryContainer;
    private final Locator addNewAddressField;

    // --- Address Form Inputs ---
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator phoneNumberInput;
    private final Locator streetAddressInput;
    private final Locator cityInput;
    private final Locator stateRegionInput;
    private final Locator countryInput;
    private final Locator postalCodeInput;
    private final Locator setAsDefaultCheckbox;
    private final Locator saveAddressBtn;
    private final Locator cancelAddressBtn;

    // --- New: Payment Step Component Locators ---
    private final Locator bankTransferRadioOption;
    private final Locator mobileMoneyRadioOption;
    private final Locator orderNotesTextArea;
    private final Locator reviewOrderBtn;
    private final Locator backBtn;

    private final Locator firstProductName;
    private final Locator firstProductPrice;
    private final Locator placeOrderBtn;

    public CheckoutPage(Page page){
        this.page = page;

        // --- Summary & Universal Initialization ---
        this.continueToCheckoutBtn = page.locator("button.btn-primary:has-text('Payment')");
        this.addNewAddressField = page.locator("button.border-dashed");
        this.orderSummaryContainer = page.locator("div.lg\\:col-span-1");
        this.subtotalPrice = page.locator("div:has-text('Subtotal') > span").last();
        this.shippingPrice = page.locator("div:has-text('Shipping') > span.text-brand-green");
        this.grandTotalPrice = page.locator("div:has-text('Total') > span.text-xl");

        // --- Address Form Initialization ---
        this.firstNameInput = page.getByPlaceholder("First name");
        this.lastNameInput = page.getByPlaceholder("Last name");
        this.phoneNumberInput = page.getByPlaceholder("Phone number");
        this.streetAddressInput = page.getByPlaceholder("Street address");
        this.cityInput = page.getByPlaceholder("City");
        this.stateRegionInput = page.getByPlaceholder("State / Region");
        this.countryInput = page.getByPlaceholder("Country");
        this.postalCodeInput = page.getByPlaceholder("Postal code (optional)");
        this.setAsDefaultCheckbox = page.locator("label:has-text('default address') input[type='checkbox']");
        this.saveAddressBtn = page.locator("form.card button:has-text('Save Address')");
        this.cancelAddressBtn = page.locator("form.card button:has-text('Cancel')");

        // --- New: Payment Step Component Initialization ---
        this.bankTransferRadioOption = page.locator("label.card:has-text('Bank Transfer')");
        this.mobileMoneyRadioOption = page.locator("label.card:has-text('Mobile Money')");
        this.orderNotesTextArea = page.getByPlaceholder("Any special instructions...");
        this.reviewOrderBtn = page.locator("button.btn-primary:has-text('Review Order')");
        this.backBtn = page.locator("button:has-text('Back')");

        this.firstProductName = page.locator("div.flex.items-center.gap-3 p.text-white.text-sm.truncate").first();
        this.firstProductPrice = page.locator("div.flex.items-center.gap-3 p.text-white.text-sm.font-semibold").first();
        this.placeOrderBtn = page.locator("button.btn-primary:has-text('Place Order')");


    }public CheckoutPage selectBankTransfer() {
        this.bankTransferRadioOption.click();
        return this;
    }

    public CheckoutPage selectMobileMoney() {
        this.mobileMoneyRadioOption.click();
        return this;
    }


    public CheckoutPage clickContinueToPayment() {
        this.continueToCheckoutBtn.click();
        return this;
    }

    public CheckoutPage clickReviewOrder() {
        this.reviewOrderBtn.click();
        return this;
    }

    public CheckoutPage clickBack() {
        this.backBtn.click();
        return this;
    }
    public String getGrandTotalText() {
        return this.grandTotalPrice.innerText().trim();
    }

    public String getFirstProductName() {
        return this.firstProductName.innerText().trim();
    }

    public String getFirstProductPrice() {
        return this.firstProductPrice.innerText().trim();
    }

    public CheckoutPage clickPlaceOrder() {
        this.placeOrderBtn.click();
        return new CheckoutPage(page);
    }

    public CheckoutPage addNewAddress(){
        addNewAddressField.click();
        firstNameInput.fill(Data.ADDRESS_FIRST_NAME);
        lastNameInput.fill(Data.ADDRESS_LAST_NAME);
        phoneNumberInput.fill(Data.PHONE_NUMBER);
        streetAddressInput.fill(Data.STREET_ADDRESS);
        cityInput.fill(Data.CITY);
        stateRegionInput.fill(Data.STATE_REGION);
        countryInput.fill(Data.COUNTRY);
        postalCodeInput.fill(Data.POSTAL_CODE);
        setAsDefaultCheckbox.click();
        saveAddressBtn.click();
        return new CheckoutPage(page);
    }
    public CheckoutPage enterOrderNotes() {
        this.orderNotesTextArea.fill(Data.ORDER_NOTES);
        return this;
    }
    public void cancelAddingAddress(){
        cancelAddressBtn.click();
    }
}