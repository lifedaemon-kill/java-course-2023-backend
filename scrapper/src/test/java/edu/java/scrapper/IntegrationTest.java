package edu.java.scrapper;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;
import java.io.File;
import java.nio.file.Path;

@Testcontainers
public abstract class IntegrationTest {
    public final static PostgreSQLContainer<?> POSTGRES;

    static {
        POSTGRES = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("scrapper")
            .withUsername("postgres")
            .withPassword("postgres");
        POSTGRES.start();

        runMigrations(POSTGRES);
    }

    private static void runMigrations(JdbcDatabaseContainer<?> c) {
        Path migrationsPath = new File("migrations").toPath();

        for (File file : migrationsPath.toFile().listFiles()) {
            if (file.isFile() && file.getName().endsWith(".sql")) {
                MountableFile mountableFile = MountableFile.forHostPath(migrationsPath.resolve(file.getName()).toString());
                c.copyFileToContainer(mountableFile, "/docker-entrypoint-initdb.d/" + file.getName());
            }
        }
    }

    @DynamicPropertySource
    static void jdbcProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }
}
