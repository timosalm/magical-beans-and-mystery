package org.example;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RecipeService {

    List<Recipe> fetchRecipes() {
        return Collections.singletonList(new Recipe("Burger"));
    }
}
