import org.example.AppConfig;
import org.example.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringJUnitConfig(classes = AppConfig.class)
@ActiveProfiles("default")
@ContextConfiguration(classes = AppConfig.class)
@ExtendWith(SpringExtension.class) // SpringExtension integrates the Spring TestContext Framework into JUnit 5's Jupiter programming model
public class MainTests {

    @Test
    void listOfRecipesIncludesBurger(@Autowired List<Recipe> recipes) {
        assertEquals(1, recipes.stream().filter(recipe -> recipe.name().equals("Burger")).count());
    }

    @Nested
    @ActiveProfiles("vegetarian")
    class VegetarianRecipes {

        @Test
        void listOfRecipesIncludesSaladAndNotBurgerForVegetarians(@Autowired List<Recipe> recipes) {
            assertEquals(0, recipes.stream().filter(recipe -> recipe.name().equals("Burger")).count());
            assertEquals(1, recipes.stream().filter(recipe -> recipe.name().equals("Salad")).count());
        }
    }
}
