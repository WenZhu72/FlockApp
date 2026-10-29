package com.sparta.FlockApp.config;

public final class TestUrls {
    private TestUrls() {}

    public static String getUrl(String page) {
        String base = System.getProperty("webdriver.base.url", "http://localhost:8080");
        return base.replaceAll("/+$", "") + "/" + page.replaceFirst("^/+", "");
    }
}
