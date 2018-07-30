package com.test.testproject.configuration;


import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import com.zaxxer.hikari.HikariConfig;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@Profile("prod")
class DatabaseConfig {


    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource dataSource() {
        var config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("db.url"));
        config.setUsername(env.getProperty("db.user"));
        config.setPassword(env.getProperty("db.password"));
        //config.addDataSourceProperty("cachePrepStmts", "true");
        //config.addDataSourceProperty("prepStmtCacheSize", "250");
        //config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        //config.maxLifetime = 1000 * 300
        //config.maximumPoolSize = 50
        //config.leakDetectionThreshold = 10000

        var ds = new HikariDataSource(config);
        return ds;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public TransactionAwareDataSourceProxy transactionAwareDataSource()  {
        return new TransactionAwareDataSourceProxy(dataSource());
    }

    @Bean
    public DataSourceConnectionProvider dataSourceConnectionProvider()  {
        return new DataSourceConnectionProvider(transactionAwareDataSource());
    }

    @Bean
    public DefaultConfiguration defaultConfiguration()  {
        var conf = new DefaultConfiguration();
        conf.setSQLDialect(SQLDialect.POSTGRES);
        conf.setConnectionProvider(dataSourceConnectionProvider());
        conf.setExecuteListenerProvider();
        conf.settings().withReturnAllOnUpdatableRecord(true);
        return conf;
    }

    @Bean
    public DSLContext dslContext() {
        return new DefaultDSLContext(defaultConfiguration());
    }




}
