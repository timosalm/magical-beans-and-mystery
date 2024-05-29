package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// MockMvc is one option to test Spring MVC applications. Another option is the WebTestClient,
// which is used here as it allows you to work with higher level objects instead of raw data,
// and to run the same tests against a live server.
class RecipeResourceTests {

    RecipeService recipeService;
    List<Recipe> recipes;

    WebTestClient client;

    @BeforeEach
    void setUp() {
        recipes = Collections.singletonList(new Recipe("Burger"));
        recipeService = new RecipeService(recipes);
        client = MockMvcWebTestClient.bindToController(new RecipeResource(recipeService)).build();
    }

    @Test
    void getRecipes() {
        client.get().uri("/api/v1/recipes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Recipe.class)
                .hasSize(recipes.size())
                .contains(recipes.get(0));
    }

    @Test
    void addNewRecipes() {
        client.post().uri("/api/v1/recipes")
                .bodyValue(List.of(new Recipe("Salad"), new Recipe("Falafel")))
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();

        var recipes = recipeService.fetchRecipes();
        assertEquals(recipes.size(), 3);
        assertEquals(1, recipes.stream().filter(recipe -> recipe.name().equals("Burger")).count());
        assertEquals(1, recipes.stream().filter(recipe -> recipe.name().equals("Salad")).count());
        assertEquals(1, recipes.stream().filter(recipe -> recipe.name().equals("Falafel")).count());
    }
}

