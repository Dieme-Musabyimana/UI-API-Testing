package api.services;

import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import api.utils.Config;
import api.utils.LoginAs;
import io.restassured.response.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static api.utils.LoginAs.ADMIN;

public class OrderService extends BaseService {
    RequestPayloads requestPayloads;

    public OrderService(){
        this.requestPayloads = new RequestPayloads();
    }

    public Response placeOder(String paymentMethod){
        new CartService().addToCart(ADMIN);
        Object requestBody = requestPayloads.orderPayload(paymentMethod);
        return sendPost(Routes.ORDERS, requestBody, null, ADMIN);
    }

    public Response getUserOder(String status,  int page){
        Map<String, Object> query = Map.of("status", status, "page", page);
        return sendGet(Routes.ORDERS, null, query, ADMIN);
    }

    public Response getSingleOrder(String id, LoginAs loginAs){
        Map<String, Object> param = Map.of("id", id);

        return sendGet(Routes.SINGLE_ODER, param, null, loginAs);
    }

    public Response cancelOrder(String id){
        return sendPatch(Routes.CANCEL_ORDER, null, Map.of("id", id), null, ADMIN);
    }

    public Response getAllOrders(LoginAs loginAs){
        return sendGet(Routes.ALL_ORDES, null, null, loginAs);
    }
    public Response updateOderStatus(Map<String, Object> payload, String id){
        Map<String, Object> param = Map.of("id", id);
        return sendPatch(Routes.UPDATE_ORDER_STATUS, payload, param,    null, ADMIN);

    }
    public Map<String, Object> getBody(String status) {
        Map<String, Object> payload = requestPayloads.updateOrderStatusPayload(status);
        return payload;
    }

    public  Response requestReturn(String status){
        Map<String, Object> reason = requestPayloads.returnReason();
        Response response = getAllOrders(ADMIN);
        String currentStatus = response.jsonPath().getString("data[0].status");
        String id = response.jsonPath().getString("data[0].id");
        Map<String, Object> body = Map.of("id", id);

        if(!currentStatus.equals(status)){
            Map<String, Object> payload = requestPayloads.updateOrderStatusPayload(status);
            updateOderStatus(payload, id);
        }
      return sendPatch(Routes.ORDER_RETURN, reason, body, null, ADMIN);
    }


    public String getOrderIdForPayment(String paymentMethod) {
        Response response = getAllOrders(ADMIN);
        List<Map<String, Object>> allOrders = response.jsonPath().getList("data");

        if (allOrders != null) {
            for (Map<String, Object> order : allOrders) {
                if (order.get("paymentMethod") != null) {
                    String actualPaymentMethod = order.get("paymentMethod").toString();

                    if (actualPaymentMethod.equalsIgnoreCase(paymentMethod)) {
                        return order.get("id").toString();

                    }
                }
            }
        }
        System.out.println("No order found with payment method: " + paymentMethod + ". Creating a new one...");
        Response createResponse = placeOder(paymentMethod);
        return createResponse.jsonPath().getString("data.id");
    }

    public Response uploadPaymentProof(String orderId) {
        Map<String, Object> param = Map.of("id", orderId);
        File file = new RequestPayloads().createFile(Config.getFilePath());
        return sendPostMultipartWithAuth(Routes.PAYMENT_PROOF, file, "proof", param, ADMIN);
    }
}
