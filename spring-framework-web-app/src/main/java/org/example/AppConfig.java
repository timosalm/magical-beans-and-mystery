package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

// Web Applications
@EnableWebMvc
@PropertySource("classpath:application.properties")
@ComponentScan("org.example")
public class AppConfig {

    @Bean
    List<Recipe> recipes(@Value("#{'${recipes}'.split(',')}") List<Recipe> recipes) {
        return recipes;
    }
}
