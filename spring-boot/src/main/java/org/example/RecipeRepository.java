package org.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends ListCrudRepository<Recipe, Long> {

}
