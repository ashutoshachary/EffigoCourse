package com.ashutosh.connect2db.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.ashutosh.connect2db.repository.post",
    entityManagerFactoryRef = "postEntityManagerFactory",
    transactionManagerRef = "postTransactionManager"
)
public class PostDatabaseConfig {

    @Bean(name = "postDataSource")
    public DataSource postDataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://localhost:5432/Post")  // Adjust database name as needed
            .username("postgres")
            .password("ashutosh@16")
            .driverClassName("org.postgresql.Driver")
            .build();
    }

    @Bean(name = "postEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postDataSource") DataSource dataSource) {
        return builder
            .dataSource(dataSource)
            .packages("com.ashutosh.connect2db.model")
            .persistenceUnit("post")
            .build();
    }

    @Bean(name = "postTransactionManager")
    public PlatformTransactionManager postTransactionManager(
            @Qualifier("postEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
