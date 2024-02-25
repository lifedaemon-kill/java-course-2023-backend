package edu.java.bot.utility;

import org.springframework.stereotype.Component;

@Component
public class StringCommand {
    public static final String START = "/start";
    public static final String TRACK = "/track";
    public static final String UNTRACK = "/untrack";
    public static final String HELP = "/help";
    public static final String LIST = "/list";

    private StringCommand() {
    }
}
