package apitests.admin;

import api.constants.Status;
import api.services.AdminService;
import api.utils.Expected;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static api.utils.LoginAs.ADMIN;
import static api.utils.LoginAs.CUSTOMER;

public class GetDashboardAnalytics {

    @Test
    public void getAnalyticsAsAdmin() {
        Response response = new AdminService().getDashboardAnalytics(ADMIN);

        Assert.assertEquals(response.statusCode(), Status.OK);
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Success");

        Assert.assertNotNull(response.jsonPath().get("data"));

        Assert.assertTrue(response.jsonPath().getInt("data.stats.users.total") >= 0);
        Assert.assertTrue(response.jsonPath().getInt("data.stats.users.newThisMonth") >= 0);

        Assert.assertTrue(response.jsonPath().getInt("data.stats.orders.total") >= 0);
        Assert.assertTrue(response.jsonPath().getInt("data.stats.orders.thisMonth") >= 0);

        Assert.assertTrue(response.jsonPath().getDouble("data.stats.revenue.thisMonth") >= 0.0);
        Assert.assertEquals(response.jsonPath().getDouble("data.stats.revenue.lastMonth"), 0.0);
        Assert.assertEquals(response.jsonPath().getDouble("data.stats.revenue.growth"), 0.0);

        Assert.assertTrue(response.jsonPath().getInt("data.stats.products.total") >= 0);
        Assert.assertTrue(response.jsonPath().getInt("data.stats.products.lowStock") >= 0);

        Map<String, Integer> ordersByStatus = response.jsonPath().getMap("data.ordersByStatus");
        Assert.assertNotNull(ordersByStatus);
        Assert.assertTrue(ordersByStatus.containsKey("PENDING"));
        Assert.assertTrue(ordersByStatus.containsKey("DELIVERED"));
        Assert.assertTrue(ordersByStatus.get("PENDING") >= 0);

        List<Map<String, Object>> recentOrders = response.jsonPath().getList("data.recentOrders");
        Assert.assertNotNull(recentOrders);
        Assert.assertFalse(recentOrders.isEmpty(), "Recent orders list should not be empty!");

        String firstOrderId = response.jsonPath().getString("data.recentOrders[0].id");
        String firstOrderNo = response.jsonPath().getString("data.recentOrders[0].orderNumber");
        String orderStatus = response.jsonPath().getString("data.recentOrders[0].status");

        Assert.assertNotNull(firstOrderId);
        Assert.assertTrue(firstOrderNo.startsWith("TGI-"));
        Assert.assertEquals(orderStatus, "PENDING");

        Assert.assertNotNull(response.jsonPath().getString("data.recentOrders[0].user.email"));
        Assert.assertNotNull(response.jsonPath().getString("data.recentOrders[0].user.firstName"));

        List<Map<String, Object>> topProducts = response.jsonPath().getList("data.topProducts");
        Assert.assertNotNull(topProducts);
        Assert.assertFalse(topProducts.isEmpty());

        Assert.assertNotNull(response.jsonPath().getString("data.topProducts[0].name"));
        Assert.assertTrue(response.jsonPath().getDouble("data.topProducts[0].price") > 0.0);
        Assert.assertTrue(response.jsonPath().getInt("data.topProducts[0].soldCount") >= 0);

        List<String> productTags = response.jsonPath().getList("data.topProducts[0].tags");
        Assert.assertNotNull(productTags);
        Assert.assertFalse(productTags.isEmpty());

        List<Map<String, Object>> productImages = response.jsonPath().getList("data.topProducts[0].images");
        Assert.assertNotNull(productImages);
        Assert.assertNotNull(productImages.get(0).get("url"));
    }

    @Test
    public void getAnalyticsNotAsAdmin(){
        Response response = new AdminService().getDashboardAnalytics(CUSTOMER);
        Assert.assertEquals(response.statusCode(), Status.FORBIDDEN);
        Assert.assertFalse(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), Expected.PERMISSION_ISSUE);
        Assert.assertNull(response.jsonPath().get("data"));    }


}
