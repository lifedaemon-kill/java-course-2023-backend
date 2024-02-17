package edu.java.bot.models;

import java.util.HashSet;
import java.util.Set;

public class SupportsDomains {
    public static final Set<String> list = new HashSet<>();

    static {
        list.add("stackoverflow.com");
        list.add("github.com");
    }

    private SupportsDomains() {
    }
}
