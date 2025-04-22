package org.example.recipefinder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class RecipeService {

    private final RecipeRepository repository;

    RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }
    List<Recipe> fetchRecipes() {
        return repository.findAll();
    }

    @Transactional
    void addRecipes(List<Recipe> recipes) {
        repository.saveAll(recipes);
    }
}
