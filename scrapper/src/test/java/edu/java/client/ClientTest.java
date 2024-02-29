package edu.java.client;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class ClientTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule();
    private final int port = wireMockRule.port();
    @Test
    public void testApiClient() {
        stubFor(get(urlEqualTo("/repos/commits"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody("Hello from WireMock")));

        WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:" + port)
            .build();

        String testResponse = webClient.get()
            .uri("/repos/commits")
            .retrieve()
            .bodyToMono(String.class)
            .block();

        System.out.println(testResponse);
        Assertions.assertEquals(testResponse, "Hello from WireMock");
    }
}
