package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        var xmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        var xmlRecipeService = xmlApplicationContext.getBean(RecipeService.class);
        System.out.println(xmlRecipeService.fetchRecipes());

        var applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        List<Recipe> recipes = applicationContext.getBean("recipes", List.class);
        System.out.println(recipes.stream().map(Recipe::name).toList());

        var recipeService = applicationContext.getBean(RecipeService.class);
        System.out.println(recipeService.fetchRecipes());

        System.out.println(applicationContext.getResources("application.properties")[0].getClass().getSimpleName());
        System.out.println(applicationContext.getResources("file:application.properties")[0].getClass().getSimpleName());
    }
}
