package org.example.recipefinder;

import org.example.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RecipeFinderApplicationTests {

	@Test
	void contextLoads(ApplicationContext applicationContext) {
		var recipeService = applicationContext.getBean(RecipeService.class);
		var recipes = recipeService.fetchRecipes();
		assertEquals(4, recipes.size());
	}

}

