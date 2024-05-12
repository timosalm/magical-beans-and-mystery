package org.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeRepository {

    private final JdbcClient jdbcClient;

    public RecipeRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Recipe> fetchRecipes() {
        return jdbcClient.sql("SELECT * FROM RECIPE").query(
                (rs, rowNum) -> new Recipe(rs.getInt("id"), rs.getString("name"))
        ).list();
    }

    public void insertRecipe(String name) {
        jdbcClient.sql("INSERT INTO recipe (name) VALUES (:name)").param("name", name).update();
    }
}
