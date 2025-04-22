package org.example.recipefinder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes")
class RecipeResource  {

    private final RecipeService recipeService;

    RecipeResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    ResponseEntity<List<Recipe>> fetchRecipes() {
        var recipes = recipeService.fetchRecipes();
        return ResponseEntity.ok(recipes);
    }

    @PostMapping
    ResponseEntity<Void> addRecipes(@RequestBody List<Recipe> newRecipes) {
        recipeService.addRecipes(newRecipes);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
