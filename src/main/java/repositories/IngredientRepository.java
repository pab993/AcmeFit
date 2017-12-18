
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

	@Query("select m.ingredients from Meal m where m.id = ?1")
	Collection<Ingredient> findByMeal(int mealId);

}
