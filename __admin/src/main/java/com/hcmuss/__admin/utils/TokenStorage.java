package com.hcmuss.__admin.utils;

import java.util.prefs.Preferences;

public class TokenStorage {
    private static final Preferences prefs = Preferences.userNodeForPackage(TokenStorage.class);

    public static void saveToken(String token) {
        prefs.put("auth_token", token);
    }

    public static String getToken() {
        return prefs.get("auth_token", null);
    }

    public static void removeToken() {
        prefs.remove("auth_token");
    }
}
