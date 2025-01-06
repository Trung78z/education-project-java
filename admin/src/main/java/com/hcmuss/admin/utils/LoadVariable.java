package com.hcmuss.admin.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class LoadVariable {
    private static Dotenv dotenv;

    static {
        dotenv = Dotenv.configure()
                .filename(".env") // Tên tệp .env
                .directory("src/main/resources") // Thư mục chứa .env
                .load();
    }

    public static String get(String key) {
        return dotenv.get(key);
    }
}
