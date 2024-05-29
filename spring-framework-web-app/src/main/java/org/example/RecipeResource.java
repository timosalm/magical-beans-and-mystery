package org.example;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

// Web Applications
@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeResource {

    private final RecipeService recipeService;

    public RecipeResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> fetchRecipes() {
        var recipes = recipeService.fetchRecipes();
        return ResponseEntity.ok(recipes);
    }

    @PostMapping
    public ResponseEntity<List<Recipe>> addRecipes(@RequestBody List<Recipe> newRecipes) {
        recipeService.addRecipes(newRecipes);
        return ResponseEntity.noContent().build();
    }
}
