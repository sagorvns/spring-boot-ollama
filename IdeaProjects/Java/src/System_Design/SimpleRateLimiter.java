package System_Design;

import java.util.*;

public class SimpleRateLimiter {
    private Map<String, Integer> requestCount = new HashMap<>();
    private Map<String, Long> timestamp = new HashMap<>();
    private final int LIMIT = 3;          // Max requests
    private final long WINDOW = 5000;     // In milliseconds

    public boolean allow(String userId) {
        long now = System.currentTimeMillis();
        timestamp.putIfAbsent(userId, now);
        requestCount.putIfAbsent(userId, 0);

        if (now - timestamp.get(userId) > WINDOW) {
            requestCount.put(userId, 1);
            timestamp.put(userId, now);
            return true;
        }

        if (requestCount.get(userId) < LIMIT) {
            requestCount.put(userId, requestCount.get(userId) + 1);
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleRateLimiter limiter = new SimpleRateLimiter();

        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.allow("user123");
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(1000);
        }
    }
}

