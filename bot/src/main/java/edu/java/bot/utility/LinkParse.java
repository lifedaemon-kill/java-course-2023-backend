package edu.java.bot.utility;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import lombok.extern.log4j.Log4j2;
import static edu.java.bot.utility.LinkParse.LinkValidState.ACCEPT;
import static edu.java.bot.utility.LinkParse.LinkValidState.NOT_SUPPORTED_RESOURCE;
import static edu.java.bot.utility.LinkParse.LinkValidState.NOT_URL;
import static edu.java.bot.utility.LinkParse.LinkValidState.NO_CONNECTION;

@Log4j2
public class LinkParse {
    private LinkParse() {
    }

    public enum LinkValidState { ACCEPT, NOT_URL, NOT_SUPPORTED_RESOURCE, NO_CONNECTION }

    public static boolean isURL(String text) {
        try {
            URI resp = new URI(text).toURL().toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isURLSupports(String link) {
        try {
            URI url = new URI(link);
            String urlDomain = url.getHost();
            log.debug("Ссылка " + urlDomain + " " + SupportedDomainsArchive.STRING_SET.contains(urlDomain));
            return SupportedDomainsArchive.STRING_SET.contains(urlDomain);
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
            return statusCode == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            return false;
        }
    }

    public static LinkValidState isLinkValidToUse(String link) {
        if (!isURL(link)) {
            return NOT_URL;
        } else if (!isURLSupports(link)) {
            return NOT_SUPPORTED_RESOURCE;
        } else if (!isResourceAvailable(link)) {
            return NO_CONNECTION;
        } else {
            return ACCEPT;
        }
    }
}
