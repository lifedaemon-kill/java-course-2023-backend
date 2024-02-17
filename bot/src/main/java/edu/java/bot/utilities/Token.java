package edu.java.bot.utilities;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Token {
    public static String getTelegramToken() throws Exception {
        try {
            return Files.readString(Paths.get("bot/src/main/resources/token"));
        } catch (Exception e) {
            throw new Exception("Не удалось получить токен");
        }
    }

    private Token() {
    }
}
