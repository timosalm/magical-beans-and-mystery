package org.example.recipefinder;

import org.example.Recipe;
import org.example.RecipeResource;
import org.example.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

// MockMvc is one option to test Spring MVC applications. Another option is the WebTestClient,
// which is used here as it allows you to work with higher level objects instead of raw data,
// and to run the same tests against a live server.
@ExtendWith(SpringExtension.class)
class RecipeResourceTests {

    @MockBean
    RecipeService recipeServiceMock;
    List<Recipe> recipes;

    WebTestClient client;

    @BeforeEach
    void setUp() {
        recipes = Collections.singletonList(new Recipe(1L, UUID.randomUUID().toString()));
        client = MockMvcWebTestClient.bindToController(new RecipeResource(recipeServiceMock)).build();
    }

    @Test
    void getRecipes() {
        Mockito.when(recipeServiceMock.fetchRecipes()).thenReturn(recipes);
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
        Mockito.doNothing().when(recipeServiceMock).addRecipes(Mockito.any());

        client.post().uri("/api/v1/recipes")
                .bodyValue(List.of(new Recipe(UUID.randomUUID().toString()), new Recipe(UUID.randomUUID().toString())))
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }
}

