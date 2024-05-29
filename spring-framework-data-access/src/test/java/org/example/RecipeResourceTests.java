package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

import java.util.Collections;
import java.util.List;

// MockMvc is one option to test Spring MVC applications. Another option is the WebTestClient,
// which is used here as it allows you to work with higher level objects instead of raw data,
// and to run the same tests against a live server.
@ExtendWith(MockitoExtension.class)
class RecipeResourceTests {

    @Mock
    RecipeService recipeServiceMock;
    List<Recipe> recipes;

    WebTestClient client;

    @BeforeEach
    void setUp() {
        recipes = Collections.singletonList(new Recipe(1L, "Burger"));

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
                .bodyValue(List.of(new Recipe("Salad"), new Recipe("Falafel")))
                .exchange()
                .expectStatus().isCreated()
                .expectBody().isEmpty();
    }
}

