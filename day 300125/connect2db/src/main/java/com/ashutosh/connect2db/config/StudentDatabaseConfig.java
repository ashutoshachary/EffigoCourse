package com.ashutosh.connect2db.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.ashutosh.connect2db.repository.student", 
    entityManagerFactoryRef = "studentEntityManagerFactory",
    transactionManagerRef = "studentTransactionManager"
)
public class StudentDatabaseConfig {

    @Primary
    @Bean(name = "studentDataSource")
    public DataSource studentDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/Student")
                .username("postgres")
                .password("ashutosh@16")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Primary
    @Bean(name = "studentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean studentEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("studentDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.ashutosh.connect2db.model")
                .persistenceUnit("student")
                .build();
    }

    @Primary
    @Bean(name = "studentTransactionManager")
    public PlatformTransactionManager studentTransactionManager(
            @Qualifier("studentEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}


