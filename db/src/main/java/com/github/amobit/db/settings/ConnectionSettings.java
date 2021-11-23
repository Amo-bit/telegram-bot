package com.github.amobit.db.settings;

import org.springframework.stereotype.Component;

import static com.github.amobit.db.settings.ConfigConstants.*;


@Component
public class ConnectionSettings {

    private static final int DEFAULT_MAX_POOL_SIZE = 5;

    private String jdbcDriver = DB_DRIVER;
    private String jdbcString = DB_CONNECTION;
    private String jdbcUser = DB_USER;
    private String jdbcPassword = DB_PASSWORD;
    private int jdbcMaxPoolSize = DEFAULT_MAX_POOL_SIZE;

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcString() {
        return jdbcString;
    }

    public void setJdbcString(String jdbcString) {
        this.jdbcString = jdbcString;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public int getJdbcMaxPoolSize() {
        return jdbcMaxPoolSize;
    }

    public void setJdbcMaxPoolSize(int jdbcMaxPoolSize) {
        this.jdbcMaxPoolSize = jdbcMaxPoolSize;
    }
}
