package com.templatestack.util.container;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLContainerSingleton extends PostgreSQLContainer<PostgreSQLContainerSingleton> {

    private static final String IMAGE_VERSION = "postgres:12.3-alpine";
    private static final String DATABASE_NAME = "db-it-tests";
    private static final String POSTGRES_USER = "user";
    private static final String POSTGRES_PASSWORD = "password";

    private static PostgreSQLContainerSingleton container;

    private PostgreSQLContainerSingleton() {
        super(IMAGE_VERSION);
    }

    public static PostgreSQLContainerSingleton getInstance() {
        if (container == null) {
            container = new PostgreSQLContainerSingleton()
                .withDatabaseName(DATABASE_NAME)
                .withUsername(POSTGRES_USER)
                .withPassword(POSTGRES_PASSWORD)
                .withEnv("TZ", "GMT");
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
