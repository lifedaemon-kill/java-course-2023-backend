package edu.java.service;

import edu.java.client.Client;
import reactor.core.publisher.Mono;

public abstract class Service {
    protected final Client client;

    Service(Client client) {
        this.client = client;
    }

    public abstract Mono<String> getRepository(String repository);
}
