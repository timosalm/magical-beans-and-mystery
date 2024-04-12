package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        List<Recipe> recipes = applicationContext.getBean("recipes", List.class);
        System.out.println(recipes.stream().map(Recipe::name).toList());

        var recipeService = applicationContext.getBean("recipeService", RecipeService.class);
        System.out.println(recipeService.fetchRecipes());
    }
}
