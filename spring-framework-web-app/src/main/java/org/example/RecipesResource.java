package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipesResource {

    private final RecipeService recipeService;

    public RecipesResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/api/v1/recipes")
    public ResponseEntity<List<Recipe>> fetchRecipes() {
        var recipes = recipeService.fetchRecipes();
        return ResponseEntity.ok(recipes);
    }
}