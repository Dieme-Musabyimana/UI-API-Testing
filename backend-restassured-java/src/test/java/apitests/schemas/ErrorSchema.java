package apitests.schemas;

import api.services.OrderService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorSchema {

    @Test
    public void validateActiveOrdersErrorSchemaForCustomer() {

        Response response =
                new OrderService().getAllOrders(LoginAs.CUSTOMER);

        Assert.assertTrue(response.getStatusCode() == 401 ||
                response.getStatusCode() == 403);

        SchemaValidation.validate(
                response,
                "ErrorSchema",
                null
        );

        Assert.assertFalse(response.jsonPath().getBoolean("success"));
    }
}