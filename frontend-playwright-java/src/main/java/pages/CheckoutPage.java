package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import constants.Data;

public class CheckoutPage {
    private final Page page;

    private final Locator continueToCheckoutBtn;
    private final Locator grandTotalPrice;
    private final Locator shippingPrice;
    private final Locator subtotalPrice;
    private final Locator orderSummaryContainer;
    private final Locator addNewAddressField;

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

    private final Locator bankTransferRadioOption;
    private final Locator mobileMoneyRadioOption;
    private final Locator orderNotesTextArea;
    private final Locator reviewOrderBtn;
    private final Locator backBtn;

    private final Locator firstProductName;
    private final Locator firstProductPrice;
    private final Locator placeOrderBtn;
    private final Locator orderSuccessMessage;
    public CheckoutPage(Page page) {
        this.page = page;

        this.continueToCheckoutBtn = page.locator("button.btn-primary:has-text('Payment')");
        this.addNewAddressField = page.locator("button.border-dashed");
        this.orderSummaryContainer = page.locator("div.lg\\:col-span-1");
        this.subtotalPrice = page.locator("div:has-text('Subtotal') > span").last();
        this.shippingPrice = page.locator("div:has-text('Shipping') > span.text-brand-green");
        this.grandTotalPrice = page.locator("div:has-text('Total') > span.text-xl");

        this.firstNameInput = page.getByPlaceholder("First name");
        this.lastNameInput = page.getByPlaceholder("Last name");
        this.phoneNumberInput = page.getByPlaceholder("Phone number");
        this.streetAddressInput = page.getByPlaceholder("Street address");
        this.cityInput = page.getByPlaceholder("City");
        this.stateRegionInput = page.getByPlaceholder("State / Region");
        this.countryInput = page.getByPlaceholder("Country");
        this.postalCodeInput = page.getByPlaceholder("Postal code (optional)");
        this.setAsDefaultCheckbox = page.locator("label:has-text('default address') input[type='checkbox']");
        this.saveAddressBtn = page.locator("button:has-text('Save Address')");
        this.cancelAddressBtn = page.locator("form.card button:has-text('Cancel')");

        this.bankTransferRadioOption = page.locator("label.card:has-text('Bank Transfer')");
        this.mobileMoneyRadioOption = page.locator("label.card:has-text('Mobile Money')");
        this.orderNotesTextArea = page.getByPlaceholder("Any special instructions...");
        this.reviewOrderBtn = page.locator("button.btn-primary:has-text('Review Order')");
        this.backBtn = page.locator("button:has-text('Back')");

        this.firstProductName = page.locator("div.flex.items-center.gap-3 p.text-white.text-sm.truncate").first();
        this.firstProductPrice = page.locator("div.flex.items-center.gap-3 p.text-white.text-sm.font-semibold").first();
        this.placeOrderBtn = page.locator("button.btn-primary:has-text('Place Order')");
        this.orderSuccessMessage = page.locator("text=Order placed successfully");

    }

    public CheckoutPage selectBankTransfer() {
        this.bankTransferRadioOption.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        this.bankTransferRadioOption.click();
        return this;
    }

    public CheckoutPage selectMobileMoney() {
        this.mobileMoneyRadioOption.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        this.mobileMoneyRadioOption.click();
        return this;
    }

    public CheckoutPage clickContinueToPayment() {
        this.continueToCheckoutBtn.last().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        this.continueToCheckoutBtn.last().click();
        return this;
    }

    public CheckoutPage clickReviewOrder() {
        this.reviewOrderBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
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

    public CheckoutPage addNewAddress() {
        addNewAddressField.click();
        firstNameInput.fill(Data.ADDRESS_FIRST_NAME);
        lastNameInput.fill(Data.ADDRESS_LAST_NAME);
        phoneNumberInput.fill(Data.PHONE_NUMBER);
        streetAddressInput.fill(Data.STREET_ADDRESS);
        cityInput.fill(Data.CITY);
        stateRegionInput.fill(Data.STATE_REGION);
        countryInput.fill(Data.COUNTRY);
        postalCodeInput.fill(Data.POSTAL_CODE);
        saveAddressBtn.click();

        String combinedNameText = Data.ADDRESS_FIRST_NAME + " " + Data.ADDRESS_LAST_NAME;
        page.locator("text=" + combinedNameText).first().waitFor();

        return this;
    }
    public CheckoutPage addNewAddressWithEmptyFields() {
        addNewAddressField.click();
        firstNameInput.fill(" ");
        lastNameInput.fill(" ");
        phoneNumberInput.fill(Data.PHONE_NUMBER);
        streetAddressInput.fill(Data.STREET_ADDRESS);
        cityInput.fill(Data.CITY);
        stateRegionInput.fill(Data.STATE_REGION);
        countryInput.fill(Data.COUNTRY);
        postalCodeInput.fill(Data.POSTAL_CODE);
        saveAddressBtn.click();

        String combinedNameText = Data.ADDRESS_FIRST_NAME + " " + Data.ADDRESS_LAST_NAME;
        page.locator("text=" + combinedNameText).first().waitFor();

        return this;
    }



    public CheckoutPage fillOrderNotes(String notes) {
        this.orderNotesTextArea.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        this.orderNotesTextArea.fill(notes);
        return this;
    }

    public void cancelAddingAddress() {
        cancelAddressBtn.click();
    }
    public String getOrderSuccessMesage(){
        orderSuccessMessage.waitFor();
        return orderSuccessMessage.textContent();
    }
}