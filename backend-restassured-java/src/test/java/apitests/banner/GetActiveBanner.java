package apitests.banner;

import api.services.ProductService;
import api.utils.LoginAs;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GetActiveBanner {    ProductService productService;

    @BeforeMethod
    public void setUp() {
        this.productService = new ProductService();
    }

    public void validateResponse(Response response) {
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");

        List<Map<String, Object>> bannerItems = response.jsonPath().getList("data");
        Assert.assertNotNull(bannerItems);
        Assert.assertFalse(bannerItems.isEmpty());

        for (Map<String, Object> banner : bannerItems) {
            Assert.assertNotNull(banner.get("id"));
            Assert.assertNotNull(banner.get("publicId"));

            Assert.assertNotNull(banner.get("title"));
            Assert.assertNotNull(banner.get("subtitle"));

            String imageUrl = (String) banner.get("imageUrl");
            Assert.assertNotNull(imageUrl);
            Assert.assertTrue(imageUrl.startsWith("http://") ||
                    imageUrl.startsWith("https://") ||
                    imageUrl.startsWith("/"));

            String link = (String) banner.get("link");
            Assert.assertNotNull(link);
            Assert.assertTrue(link.startsWith("/"));

            Assert.assertNotNull(banner.get("isActive"));
            Assert.assertTrue((Boolean) banner.get("isActive"));

            Assert.assertNotNull(banner.get("order"));
            int displayOrder = ((Number) banner.get("order")).intValue();
            Assert.assertTrue(displayOrder >= 0);
            Assert.assertNotNull(banner.get("createdAt"));
            Assert.assertNotNull(banner.get("updatedAt"));
        }
    }

    @Test
    public void getActiveBannerAsAnAdmin(){
        Response response = productService.getActiveBanners(LoginAs.ADMIN);
        validateResponse(response);
    }

    @Test
    public void getActiveBannerAsCustomer(){
        Response response = productService.getActiveBanners(LoginAs.CUSTOMER);
        validateResponse(response);
    }

    @Test
    public void getActiveBannerAsGuest(){
        Response response = productService.getActiveBanners(LoginAs.NONE);
        validateResponse(response);
    }

}
