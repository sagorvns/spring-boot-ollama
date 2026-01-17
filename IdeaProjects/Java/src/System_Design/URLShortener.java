package System_Design;

import java.util.*;

class URLShortener {
    private static final String BASE_HOST = "http://short.ly/";
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    private Map<String, String> shortToLong = new HashMap<>();
    private Map<String, String> longToShort = new HashMap<>();
    private Random random = new Random();

    public String shortenURL(String longURL) {
        if (longToShort.containsKey(longURL)) {
            return BASE_HOST + longToShort.get(longURL);
        }

        String shortURL;
        do {
            shortURL = generateShortKey();
        } while (shortToLong.containsKey(shortURL));

        shortToLong.put(shortURL, longURL);
        longToShort.put(longURL, shortURL);
        return BASE_HOST + shortURL;
    }

    public String getLongURL(String shortURL) {
        String key = shortURL.replace(BASE_HOST, "");
        return shortToLong.getOrDefault(key, "URL Not Found");
    }

    private String generateShortKey() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            sb.append(CHARSET.charAt(random.nextInt(CHARSET.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();
        String shortURL = urlShortener.shortenURL("https://www.example.com/long-url");
        System.out.println("Shortened: " + shortURL);
        System.out.println("Original: " + urlShortener.getLongURL(shortURL));
    }
}
