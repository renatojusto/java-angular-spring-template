package com.templatestack.util.container;

import org.testcontainers.containers.MySQLContainer;

public class MySQLContainerSingleton extends MySQLContainer<MySQLContainerSingleton> {

    private static final String IMAGE_VERSION = "mysql:8.0.19";
    private static final String DATABASE_NAME = "db-it-tests";
    private static final String USERNAME = "root";

    private static MySQLContainerSingleton container;

    private MySQLContainerSingleton() {
        super(IMAGE_VERSION);
    }

    public static MySQLContainerSingleton getInstance() {
        if (container == null) {
            container = new MySQLContainerSingleton()
                .withDatabaseName(DATABASE_NAME)
                .withUsername(USERNAME)
                .withPassword("")
                .withCommand("mysqld --lower_case_table_names=1 --skip-ssl --character_set_server=utf8mb4 --explicit_defaults_for_timestamp");
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
