package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceTests {

    EmbeddedDatabase dataSource;
    JdbcClient jdbcClient;
    RecipeService service;

    @BeforeEach
    void setUp() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setName(UUID.randomUUID() + ";sql.syntax_pgs=true")
                .addDefaultScripts()
                .build();
        jdbcClient = JdbcClient.create(dataSource);
        var repository = new RecipeRepository(jdbcClient);
        service = new RecipeService(repository);
    }

    @Test
    void shouldInsertNewRecipes() {
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), 4);
        service.addRecipes(List.of(new Recipe("Salad"), new Recipe("Tacos")));
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), 6);
    }

    @Test
    void shouldRollBackTransactionAfterException() {
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), 4);
        assertThrows(DataAccessException.class, () -> {
            service.addRecipes(List.of(new Recipe("Salad"), new Recipe(null)));
            assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), 4);
        });
    }

    @AfterEach
    public void tearDown() {
        dataSource.shutdown();
    }
}