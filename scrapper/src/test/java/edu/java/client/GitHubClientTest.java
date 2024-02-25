package edu.java.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;

public class GitHubClientTest {
    static {
        WireMockServer wireMockServer = new WireMockServer(); // Создаем мок-сервер
        wireMockServer.start(); // Запускаем мок-сервер
    }
    /*
    @Test
    void test1(){
        GitHubClient client = new GitHubClient();
        //client.get
    }
    */
}
