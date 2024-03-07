package edu.java.bot.utility;

import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Token {
    public static String getFromFileTelegramToken() {
        try {
            return Files.readString(Paths.get("bot/src/main/resources/token"));
        } catch (Exception e) {
            log.error("Не удалось получить токен");
            return null;
        }
    }

    private Token() {
    }
}
