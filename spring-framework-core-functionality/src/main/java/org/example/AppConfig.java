package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.util.Collections;
import java.util.List;

@PropertySource("classpath:application.properties")
@ComponentScan("org.example")
@Configuration
public class AppConfig {

    @Profile("!vegetarian")
    @Bean
    List<Recipe> recipes() {
        return Collections.singletonList(new Recipe("Burger"));
    }

    //  export spring_profiles_active=vegetarian
    @Profile("vegetarian")
    @Bean(name = "recipes")
    List<Recipe> vegetarianRecipes() {
        return Collections.singletonList(new Recipe("Salad"));
    }
}