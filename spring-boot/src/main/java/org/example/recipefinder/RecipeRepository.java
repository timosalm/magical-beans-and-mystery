package org.example.recipefinder;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

// Spring Boot for Data Access
@Repository
interface RecipeRepository extends ListCrudRepository<Recipe, Long> {

}
