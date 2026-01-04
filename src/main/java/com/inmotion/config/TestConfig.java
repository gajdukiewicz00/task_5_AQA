package com.inmotion.config;

import java.util.ResourceBundle;

public final class TestConfig {
    private static final ResourceBundle cfg = ResourceBundle.getBundle("config");

    public static String baseUrl() {
        return sys("baseUrl", cfg.getString("baseUrl"));
    }

    public static boolean headless() {
        return Boolean.parseBoolean(sys("headless", cfg.getString("headless")));
    }

    public static int timeoutSeconds() {
        return Integer.parseInt(sys("timeoutSeconds", cfg.getString("timeoutSeconds")));
    }

    public static int pageLoadSeconds() {
        return Integer.parseInt(sys("pageLoadSeconds", cfg.getString("pageLoadSeconds")));
    }

    public static int scriptSeconds() {
        return Integer.parseInt(sys("scriptSeconds", cfg.getString("scriptSeconds")));
    }

    public static int windowWidth() {
        return Integer.parseInt(sys("windowWidth", cfg.getString("windowWidth")));
    }

    public static int windowHeight() {
        return Integer.parseInt(sys("windowHeight", cfg.getString("windowHeight")));
    }

    private static String sys(String key, String def) {
        return System.getProperty(key, def);
    }

    private TestConfig() {
    }
}
