
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MealRepository;
import domain.Meal;
import domain.Nutritionist;

@Transactional
@Service
public class MealService {

	//Repository
	//======================================================================

	@Autowired
	private MealRepository		mealRepository;

	//Services
	// ======================================================================

	@Autowired
	private ActorService		actorService;

	@Autowired
	private NutritionistService	nutritionistService;


	//CRUD methods
	//=======================================================================

	public Meal findOne(final int id) {
		Meal meal;

		meal = this.mealRepository.findOne(id);
		Assert.notNull(meal);

		return meal;
	}

	public Meal create() {
		final Meal meal = new Meal();
		Assert.isInstanceOf(Nutritionist.class, this.actorService.findByPrincipal());
		return meal;
	}

	//	public Diet save(final Meal meal) {
	//		Assert.notNull(meal);
	//		Assert.isInstanceOf(Nutritionist.class, this.actorService.findByPrincipal());
	//		final Nutritionist nutritionist = this.nutritionistService.findByPrincipal();
	//		diet.setNutritionist(nutritionist);
	//		final Diet dietRes = this.dietRepository.save(diet);
	//
	//		return dietRes;
	//	}
	//
	//	public void delete(final Diet diet) {
	//
	//		Assert.notNull(diet);
	//		this.checkPrincipal(diet);
	//
	//		this.save(diet);
	//
	//		this.dietRepository.delete(diet);
	//	}

	public Collection<Meal> findAll() {

		Collection<Meal> meals;

		meals = this.mealRepository.findAll();
		Assert.notNull(meals);

		return meals;
	}

	//Other bussiness methods
	// ==================================================================

	private void checkPrincipal(final Meal meal) {
		final Nutritionist nutritionist = this.nutritionistService.findByPrincipal();
		Assert.isTrue(meal.getDiet().getNutritionist().equals(nutritionist));
	}

	public Collection<Meal> findMealsByDiet(int dietId) {
		Collection<Meal> meals;

		meals = this.mealRepository.findMealsByDiet(dietId);
		Assert.notNull(meals);

		return meals;

	}

	public Meal findMealByIngredient(int ingredientId) {
		Meal meal;

		meal = this.mealRepository.findMealByIngredient(ingredientId);
		Assert.notNull(meal);
		return meal;
	}

	public Meal save(Meal meal) {
		Assert.notNull(meal);
		return this.mealRepository.save(meal);
	}
}
