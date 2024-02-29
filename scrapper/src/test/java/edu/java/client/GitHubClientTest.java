package edu.java.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class GitHubClientTest {
    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
        wireMockServer.stubFor(WireMock.get(urlEqualTo("/repos/hello/world/commits"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody("Hello from WireMock")));
    }

    @AfterAll
    public static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void testApiClient() {
        GitHubClient client = new GitHubClient("http://localhost:" + wireMockServer.port());

        String testResponse = client.getRepository("hello/world/commits").block();

        System.out.println(testResponse);
        Assertions.assertEquals(testResponse, "Hello from WireMock");
    }
}
