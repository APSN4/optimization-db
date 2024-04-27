package com.optimiztiondb.news.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${use.database}")
    private String useDatabase;

    @Value("${use.postgresql.username}")
    private String postgresqlUsername;

    @Value("${use.postgresql.password}")
    private String postgresqlPass;

    @Value("${use.postgresql.driver}")
    private String postgresqlDriver;

    @Value("${use.postgresql.url}")
    private String postgresqlUrl;

    @Value("${use.redis.host}")
    private String redisHost;

    @Value("${use.redis.port}")
    private Integer redisPort;

    @Value("${use.redis.password}")
    private String redisPass;

    @Primary
    @Bean
    public DataSource dataSource() {
        if (useDatabase.equals("postgresql")) {
            return postgresDataSource();
        } else if (useDatabase.equals("redis")) {
            return redisDataSource();
        } else {
            throw new IllegalArgumentException("Invalid database type specified");
        }
    }

    private DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(postgresqlDriver);
        dataSource.setUrl(postgresqlUrl);
        dataSource.setUsername(postgresqlUsername);
        dataSource.setPassword(postgresqlPass);
        return dataSource;
    }

    private DataSource redisDataSource() {
         JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
         jedisConnectionFactory.setHostName(redisHost);
         jedisConnectionFactory.setPort(redisPort);
         jedisConnectionFactory.setPassword(redisPass);
         return (DataSource) jedisConnectionFactory;
    }
}
