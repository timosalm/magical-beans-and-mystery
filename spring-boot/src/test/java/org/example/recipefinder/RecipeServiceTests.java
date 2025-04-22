package org.example.recipefinder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RecipeServiceTests {

    @Autowired
    JdbcClient jdbcClient;

    @Autowired
    RecipeService service;

    @Test
    void shouldRollBackTransactionAfterException() {
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), 4);
        service.addRecipes(List.of(new Recipe(UUID.randomUUID().toString()), new Recipe(UUID.randomUUID().toString())));
        assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), 6);

        assertThrows(DbActionExecutionException.class, () -> {
            service.addRecipes(List.of(new Recipe(UUID.randomUUID().toString()), new Recipe(null)));
            assertEquals(JdbcTestUtils.countRowsInTable(jdbcClient, "recipe"), 6);
        });
    }
}