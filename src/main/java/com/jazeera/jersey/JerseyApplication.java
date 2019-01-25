package com.jazeera.jersey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;


@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
public class JerseyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JerseyApplication.class);
	}
	
	/*
	 * @Bean public HibernateJpaSessionFactoryBean sessionFactory() { return new
	 * HibernateJpaSessionFactoryBean(); }
	 */
}
