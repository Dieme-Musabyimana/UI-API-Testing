
package api.utils;

import api.POJOs.requestPOJO.LoginPOJO;
import api.base.BaseService;
import api.payloads.RequestPayloads;
import api.routes.Routes;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static api.utils.LoginAs.ADMIN;

public class TokenManager {

    private static final Map<LoginAs, String> tokenCache = new ConcurrentHashMap<>();
    private static final Map<LoginAs, String> refreshTokenCache = new ConcurrentHashMap<>();

    public TokenManager() {
    }

    public static synchronized String getToken() {
        return getToken(ADMIN);
    }

    public static synchronized String getToken(LoginAs context) {
        if (!tokenCache.containsKey(context)) {
            System.out.println("[AUTH] Token not found in memory for " + context + ". Authenticating via API...");

            LoginPOJO loginData = RequestPayloads.getCredentials(context);

            var response = new BaseService().sendPost(Routes.LOGIN, loginData, null, null);

            String token = response.jsonPath().getString("data.token");
            String refreshToken = response.jsonPath().getString("data.refreshToken");

            if (token == null) {
                throw new RuntimeException("Failed to retrieve authentication tokens for role: " + context);
            }

            tokenCache.put(context, token);
            refreshTokenCache.put(context, refreshToken);
        }
        return tokenCache.get(context);
    }

    public static synchronized String getRefreshToken() {
        return getRefreshToken(ADMIN);
    }

    public static synchronized String getRefreshToken(LoginAs context) {
        if (!refreshTokenCache.containsKey(context)) {
            getToken(context);
        }
        return refreshTokenCache.get(context);
    }

    public static void clearCache() {
        tokenCache.clear();
        refreshTokenCache.clear();
    }

    public List<String> getAllTokens(){
        Map<String, Object> body = new RequestPayloads().login2Body();
        Response response = new BaseService().sendPost(Routes.LOGIN, body, null, null);
        String token = response.jsonPath().getString("data.token");
        String refreshToken = response.jsonPath().getString("data.refreshToken");
        List<String> allTokens = new ArrayList<>();
        allTokens.add(token);
        allTokens.add(refreshToken);
        return allTokens;
    }
}