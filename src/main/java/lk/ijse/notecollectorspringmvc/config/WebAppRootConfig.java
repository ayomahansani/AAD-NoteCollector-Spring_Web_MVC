package lk.ijse.notecollectorspringmvc.config;

import jakarta.persistence.EntityManagerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "lk.ijse.notecollectorspringmvc")
@EnableJpaRepositories(basePackages = "lk.ijse.notecollectorspringmvc.dao") // jpa project ekaka full power eka (true power) eka ganna
@EnableTransactionManagement // jpa project ekaka transaction hariyata kkaranna
public class WebAppRootConfig {

    // convert dto to entity and entity to dto
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    // Database connectivity eka hada ganna
    @Bean
    public DataSource dataSource() {

        ////// database eka connect karanna adala configuration eka //////

        // dan data source eka haraha database ekata connect karanna hakiyava thiyenava

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/note_collector?createDatabaseIfNotExist=true&useSSL=false");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("Ijse@123");

        return driverManagerDataSource;
    }


    // ORM tool eka config kara ganna
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        ////// Hibernate valata adala configurations tika ////// ( kalin api property file athule dalane hibernate config karanne. but methana ehema oon na )

        // methana api use karanne Hibernate JPA ( eka hoya ganne bootstrapping eken . methana bootstrapping eka EntityManagerFactory )

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter); // Hibernate Core dependency eka da gathma meka vada karanva
        factory.setPackagesToScan("lk.ijse.notecollectorspringmvc.entity.impl");
        factory.setDataSource(dataSource()); // uda hadala thiyena dataSource() method ekata call venava
        return factory;
    }


    // Transaction manage karanna
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

}
