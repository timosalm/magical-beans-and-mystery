package org.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private final List<Recipe> recipes;

    public RecipeService(List<Recipe> recipes) {
        this.recipes = new ArrayList<>(recipes);
    }

    List<Recipe> fetchRecipes() {
        return this.recipes;
    }

    public void addRecipes(List<Recipe> newRecipes) {
        this.recipes.addAll(newRecipes);
    }
}
