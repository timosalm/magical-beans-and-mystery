package org.example.recipefinder;

import org.example.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit and Integration Testing with Spring Boot
@SpringBootTest
class RecipeFinderApplicationTests {

	@Test
	void contextLoads(@Autowired RecipeService recipeService) {
		var recipes = recipeService.fetchRecipes();
		assertEquals(4, recipes.size());
	}

}

