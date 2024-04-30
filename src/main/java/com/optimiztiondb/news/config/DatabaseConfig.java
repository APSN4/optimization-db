package com.optimiztiondb.news.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import redis.clients.jedis.*;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:.env")
public class DatabaseConfig {

    @Value("${use.postgresql.username}")
    private String postgresqlUsername;

    @Value("${use.postgresql.password}")
    private String postgresqlPass;

    @Value("${use.postgresql.driver}")
    private String postgresqlDriver;

    @Value("${use.postgresql.url}")
    private String postgresqlUrl;

    @Primary
    @Bean
    public DataSource dataSource() {
        return postgresDataSource();
    }

    private DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(postgresqlDriver);
        dataSource.setUrl(postgresqlUrl);
        dataSource.setUsername(postgresqlUsername);
        dataSource.setPassword(postgresqlPass);
        return dataSource;
    }
}
