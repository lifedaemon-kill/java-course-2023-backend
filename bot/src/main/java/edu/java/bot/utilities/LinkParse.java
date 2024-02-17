package edu.java.bot.utilities;

import edu.java.bot.models.SupportsDomains;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LinkParse {
    public static boolean isURL(String text) {
        try {
            new URI(text);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    public static boolean isURLSupports(String link) {
        if (!isURL(link)) {
            return false;
        }
        URI url;
        try {
            url = new URI(link);
            String urlDomain = url.getHost();
            log.info("Ссылка" + urlDomain + " " + SupportsDomains.list.contains(urlDomain));
            return SupportsDomains.list.contains(urlDomain);
        } catch (Exception ignore) {
            return false;
        }
    }
}
