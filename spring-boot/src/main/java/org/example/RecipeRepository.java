package org.example;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

// Spring Boot for Data Access
@Repository
public interface RecipeRepository extends ListCrudRepository<Recipe, Long> {

}
