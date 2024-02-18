package edu.java.bot.utilities;

import edu.java.bot.models.SupportsDomains;

import java.net.*;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LinkParse {
    private LinkParse() {
    }

    private static final int OK_CODE = 200;

    public static boolean isURL(String text) {
        try {
            new URI(text).toURL();
            return true;
        } catch (Exception e) {
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
            log.debug("Ссылка " + urlDomain + " " + SupportsDomains.STRING_SET.contains(urlDomain));
            return SupportsDomains.STRING_SET.contains(urlDomain);
        } catch (Exception ignore) {
            return false;
        }
    }

    public static boolean isResourceAvailable(String url) {

        try {
            URL link = new URI(url).toURL();
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setRequestMethod("GET");
            int statusCode = connection.getResponseCode();
            connection.disconnect();
            return statusCode == OK_CODE;
        } catch (Exception e) {
            return false;
        }
    }
}
