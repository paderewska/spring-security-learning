package com.security.learning;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringSecurityLearningApplication {

    //---InMemoryAuthentication Case---
//    @Bean
//    UserDetailsManager memory() {
//        return new InMemoryUserDetailsManager();
//    }

    //---JDBC-based Authentication Case---
    @Bean
    UserDetailsManager memory(DataSource ds) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(ds);
        return jdbcUserDetailsManager;
    }

    @Bean
    InitializingBean initializer(UserDetailsManager manager) {
        return () -> {
            UserDetails iza = User.withDefaultPasswordEncoder().username("iza").password("password").roles("USER").build();
            manager.createUser(iza);
            UserDetails jurand = User.withUserDetails(iza).username("jurand").build();
            manager.createUser(jurand);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityLearningApplication.class, args);
    }

}
