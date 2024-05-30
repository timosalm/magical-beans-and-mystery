package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RecipeRepositoryTests {

    EmbeddedDatabase dataSource;
    JdbcClient jdbcClient;
    RecipeRepository repository;

    @BeforeEach
    void setUp() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setName(UUID.randomUUID() + ";sql.syntax_pgs=true")
                .addDefaultScripts()
                .build();
        jdbcClient = JdbcClient.create(dataSource);
        repository = new RecipeRepository(jdbcClient);
    }

    @Test
    void shouldReturnAllRecipes() {
        var recipes = repository.fetchRecipes();
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), recipes.size());
    }

    @Test
    void shouldInsertNewRecipes() {
        var newRecipeName = UUID.randomUUID().toString();
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), 4);
        repository.insertRecipe(newRecipeName);
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), 5);
        var recipes = repository.fetchRecipes();
        var newRecipe = recipes.stream().filter(r -> r.name().equals(newRecipeName)).findFirst();
        assertTrue(newRecipe.isPresent());
        assertNotNull(newRecipe.get().id());
    }

    @AfterEach
    void tearDown() {
        dataSource.shutdown();
    }

}