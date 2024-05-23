package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

// MockMvc is one option to test Spring MVC applications. Another option is the WebTestClient,
// which is used here as it allows you to work with higher level objects instead of raw data,
// and to run the same tests against a live server.
@WebAppConfiguration
@SpringJUnitConfig(classes = AppConfig.class)
class RecipeResourceTesta {

    @Autowired
    WebApplicationContext applicationContext;

    WebTestClient client;

    @BeforeEach
    void setUp() {
        client = MockMvcWebTestClient.bindToApplicationContext(this.applicationContext).build();
    }

    @Test
    void listOfRecipesIncludesBurger() {
        client.get().uri("/api/v1/recipes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Burger");
    }
}

