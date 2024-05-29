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

    private EmbeddedDatabase dataSource;
    private JdbcClient jdbcClient;
    private RecipeRepository repository;

    @BeforeEach
    public void setUp() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setName(UUID.randomUUID() + ";sql.syntax_pgs=true")
                .addDefaultScripts()
                .build();
        jdbcClient = JdbcClient.create(dataSource);
        repository = new RecipeRepository(jdbcClient);
    }

    @Test
    public void shouldReturnAllRecipes() {
        var recipes = repository.fetchRecipes();
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), recipes.size());
    }

    @Test
    public void shouldInsertNewRecipes() {
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), 4);
        repository.insertRecipe("Taco");
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), 5);
        var recipes = repository.fetchRecipes();
        var taco = recipes.stream().filter(r -> r.name().equals("Taco")).findFirst();
        assertTrue(taco.isPresent());
        assertNotNull(taco.get().id());
    }

    @AfterEach
    public void tearDown() {
        dataSource.shutdown();
    }

}