package edu.java.bot.utility;

import java.util.HashSet;
import java.util.Set;

public class SupportedDomainsArchive {
    public static final Set<String> STRING_SET = new HashSet<>();

    static {
        STRING_SET.add("stackoverflow.com");
        STRING_SET.add("github.com");
    }

    private SupportedDomainsArchive() {
    }
}
