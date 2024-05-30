package org.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }
    public List<Recipe> fetchRecipes() {
        return repository.findAll();
    }

    @Transactional
    public void addRecipes(List<Recipe> recipes) {
        repository.saveAll(recipes);
    }
}
