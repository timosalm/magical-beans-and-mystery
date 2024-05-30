package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.util.Collections;
import java.util.List;

// Environment Abstraction - Properties
@PropertySource("classpath:application.properties")
// Spring IoC Container - Auto-configuration with stereotyped annotations
@ComponentScan("org.example")
// Spring IoC Container - Configuration with Java
public class AppConfig {

    @Bean
    List<Recipe> recipes() {
        return Collections.singletonList(new Recipe("Burger"));
    }

    // Environment Abstraction - Profiles
    @Profile("production")
    @Primary
    @Bean
    // Environment Abstraction - Properties
    List<Recipe> productionRecipes(@Value("#{'${recipes}'.split(',')}") List<Recipe> recipes) {
        return recipes;
    }
}