package edu.java.scrapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class SimpleContainerTest extends IntegrationTest {
    @Test
    void test1() {
        assertThat(POSTGRES.isRunning()).isTrue();
    }
}
