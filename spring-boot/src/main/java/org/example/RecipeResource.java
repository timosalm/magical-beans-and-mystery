package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeResource  {

    private final RecipeService recipeService;

    public RecipeResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/api/v1/recipes")
    public ResponseEntity<List<Recipe>> fetchRecipes() {
        var recipes = recipeService.fetchRecipes();
        return ResponseEntity.ok(recipes);
    }
}
