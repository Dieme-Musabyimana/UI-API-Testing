package api.utils;


public class TokenManager {
    private static final ThreadLocal<String> tokenHolder = new ThreadLocal<>();

    public static void setToken(String token) {
        tokenHolder.set(token);
    }

    public static String getToken() {
        String token = tokenHolder.get();
        if (token == null) {
            throw new RuntimeException("CRITICAL: Application token is empty! You must successfully login via AuthService first.");
        }
        return token;
    }

    public static void clearToken() {
        tokenHolder.remove();
    }
}