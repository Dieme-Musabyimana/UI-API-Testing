package apitests.ordersAPi;

import api.constants.Status;
import api.services.OrderService;
import api.utils.Message;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadPaymentProof {

    @Test
    public void uploadPaymentProofWithMobileMoney() {
        String orderId = new OrderService().getOrderIdForPayment("MOBILE_MONEY");
        Response response = new OrderService().uploadPaymentProof(orderId);
        Assert.assertEquals(response.statusCode(), Status.OK);
    }

    @Test
    public void uploadProofWithBankTransfer() {
        String orderId = new OrderService().getOrderIdForPayment("BANK_TRANSFER");
        Response response = new OrderService().uploadPaymentProof(orderId);
        Assert.assertEquals(response.statusCode(), Status.OK);
    }

    @Test
    public void uploadProofWithCashOnDelivery() {
        String orderId = new OrderService().getOrderIdForPayment("CASH_ON_DELIVERY");
        Response response = new OrderService().uploadPaymentProof(orderId);
        Assert.assertEquals(response.statusCode(), Status.BAD_REQUEST, Message.FAILED_TO_REJECT_COD_PROOF);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));

    }
}