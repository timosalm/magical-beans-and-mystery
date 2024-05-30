package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("default")
//@SpringJUnitConfig(classes = AppConfig.class)
@ContextConfiguration(classes = AppConfig.class)
@ExtendWith(SpringExtension.class) // SpringExtension integrates the Spring TestContext Framework into JUnit 5's Jupiter programming model
class RecipeServiceTests {

    @Test
    void listOfRecipesIncludesBurger(@Autowired RecipeService recipeService) {
        var recipes = recipeService.fetchRecipes();
        assertEquals(recipes.size(), 1);
        assertEquals(1, recipes.stream().filter(recipe -> recipe.name().equals("Burger")).count());
    }

    @Nested
    @ActiveProfiles("production")
    @TestPropertySource(properties = """
            recipes = Burger,Salad
            """)
    class ProductionRecipes {

        @Test
        void listOfRecipesIncludesSaladAndBurgerForProduction(@Autowired RecipeService recipeService) {
            var recipes = recipeService.fetchRecipes();
            assertEquals(recipes.size(), 2);
            assertEquals(1, recipes.stream().filter(recipe -> recipe.name().equals("Burger")).count());
            assertEquals(1, recipes.stream().filter(recipe -> recipe.name().equals("Salad")).count());
        }
    }
}