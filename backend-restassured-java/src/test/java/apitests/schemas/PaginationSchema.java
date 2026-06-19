package apitests.schemas;

import api.services.ProductService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class PaginationSchema {

    @Test
    public void paginationSchemaValidation(){
        Response response= new ProductService().fullTextSearch("clothing", 1,
                10, null,
                null, null,
                null, LoginAs.ADMIN);
        String pagination = "pagination";
        SchemaValidation.validate(response, "paginationSchema", "pagination");
    }
}
