package apitests.schemas;

import api.services.OrderService;
import api.utils.LoginAs;
import api.utils.Message;
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
        Assert.assertFalse(response.jsonPath().getBoolean("success"));

        SchemaValidation.validate(
                response,
                "ErrorSchema",
                null,
                Message.MISSING_ERROR_FIELD
        );
    }
}