package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    List<Recipe> recipes() {
        return Collections.singletonList(new Recipe("Burger"));
    }
}
