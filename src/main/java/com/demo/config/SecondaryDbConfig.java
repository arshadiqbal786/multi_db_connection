package com.demo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration          //so that we can create bean
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondEntityManagerFactoryBean",
        basePackages = {"com.demo.repository.secondary"},
        transactionManagerRef = "secondTransactionManager"
)
public class SecondaryDbConfig {

    @Autowired
    private Environment environment;
    //create three Bean

    //1.datasource
    @Bean(name = "secondDataSource")
    @Primary
    public DataSource dataSource(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("spring.datasource.secondary.url"));
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.secondary.driver-class-name"));
        dataSource.setUsername(environment.getProperty("spring.datasource.secondary.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.secondary.password"));
        return dataSource;
    }

    //2.entityManagerFactoryBean

    @Bean(name = "secondEntityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(adapter);

        Map<String, String> property = new HashMap<>();
        property.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        property.put("hibernate.show-sql","true");
        property.put("hibernate.hbm2ddl.auto","update");
        bean.setJpaPropertyMap(property);
        bean.setPackagesToScan("com.demo.entity.secondary");
        return bean;
    }
    //3.platformTransactionManager

    @Bean(name = "secondTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager manager =new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return manager;
    }
}