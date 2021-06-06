package com.templatestack.util.container;

import org.testcontainers.containers.JdbcDatabaseContainer;

public class ContainerFactory {

    public static JdbcDatabaseContainer getInstance() {
        return PostgreSQLContainerSingleton.getInstance();
    }

}
