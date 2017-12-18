
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.IngredientRepository;
import domain.Ingredient;
import domain.Meal;
import domain.Nutritionist;

@Transactional
@Service
public class IngredientService {

	//Repository
	//======================================================================

	@Autowired
	private IngredientRepository	ingredientRepository;

	//Services
	// ======================================================================

	@Autowired
	private ActorService			actorService;

	@Autowired
	private NutritionistService		nutritionistService;

	@Autowired
	private MealService				mealService;


	//CRUD methods
	//=======================================================================

	public Ingredient findOne(final int id) {
		Ingredient ingredient;

		ingredient = this.ingredientRepository.findOne(id);
		Assert.notNull(ingredient);

		return ingredient;
	}

	public Ingredient create() {
		final Ingredient ingredient = new Ingredient();
		Assert.isInstanceOf(Nutritionist.class, this.actorService.findByPrincipal());
		ingredient.setUnit("unit");
		ingredient.setMultiplicity(1.0);
		ingredient.setName("name");
		return ingredient;
	}

	public Ingredient save(final Ingredient ingredient) {
		Assert.notNull(ingredient);
		Assert.isInstanceOf(Nutritionist.class, this.actorService.findByPrincipal());
		final Ingredient ingredientRes = this.ingredientRepository.save(ingredient);

		return ingredientRes;
	}

	public void delete(final Ingredient ingredient) {

		Assert.notNull(ingredient);
		this.checkPrincipal(ingredient);

		this.ingredientRepository.delete(ingredient);
	}

	public Collection<Ingredient> findAll() {

		Collection<Ingredient> ingredients;

		ingredients = this.ingredientRepository.findAll();
		Assert.notNull(ingredients);

		return ingredients;
	}

	//Other bussiness methods
	// ===============================================================

	private void checkPrincipal(Ingredient ingredient) {
		final Nutritionist nutritionist = this.nutritionistService.findByPrincipal();
		Meal meal = this.mealService.findMealByIngredient(ingredient.getId());
		Assert.isTrue(meal.getDiet().getNutritionist().equals(nutritionist));
	}
}
