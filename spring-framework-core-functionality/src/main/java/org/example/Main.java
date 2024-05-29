package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        // Spring IoC Container
        var xmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        var xmlRecipeService = xmlApplicationContext.getBean(RecipeService.class);
        System.out.println("ClassPathXmlApplicationContext: " + xmlRecipeService.fetchRecipes());

        var applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        var recipeService = applicationContext.getBean(RecipeService.class);
        System.out.println("\nAnnotationConfigApplicationContext: " + recipeService.fetchRecipes());

        // Environment Abstraction - Profiles
        var env =  applicationContext.getEnvironment().getActiveProfiles();
        System.out.println("\nActive profiles: " + Arrays.asList(env));

        // Environment Abstraction - Properties
        var propertyValue = applicationContext.getEnvironment().getProperty("recipes");
        System.out.println("\n'recipes' property value: " + propertyValue);

        // Resources
        System.out.println("\nResource types:");
        System.out.println(applicationContext.getResources("application.properties")[0].getClass().getSimpleName());
        System.out.println(applicationContext.getResources("file:application.properties")[0].getClass().getSimpleName());
    }
}
