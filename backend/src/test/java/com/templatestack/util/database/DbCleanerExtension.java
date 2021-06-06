package com.templatestack.util.database;

import static org.springframework.test.context.junit.jupiter.SpringExtension.getApplicationContext;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbCleanerExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(final ExtensionContext extensionContext) throws Exception {
        DbCleaner dbCleaner = getApplicationContext(extensionContext).getBean(DbCleanerPostgreSQL.class);
        JdbcTemplate jdbcTemplate = getApplicationContext(extensionContext).getBean(JdbcTemplate.class);
        dbCleaner.truncateAllTables(jdbcTemplate);
    }
}
