
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DietRepository;
import domain.Customer;
import domain.Diet;
import domain.Ingredient;
import domain.Meal;
import domain.Nutritionist;

@Transactional
@Service
public class DietService {

	//Repository
	//======================================================================

	@Autowired
	private DietRepository		dietRepository;

	//Services
	// ======================================================================

	@Autowired
	private ActorService		actorService;

	@Autowired
	private NutritionistService	nutritionistService;

	@Autowired
	private CustomerService		customerService;
	
	@Autowired
	private MealService mealService;


	//CRUD methods
	//=======================================================================

	public Diet findOne(final int id) {
		Diet diet;

		diet = this.dietRepository.findOne(id);
		Assert.notNull(diet);

		return diet;
	}

	public Diet create() {
		final Diet diet = new Diet();
		Assert.isInstanceOf(Nutritionist.class, this.actorService.findByPrincipal());
		
		return diet;
	}

	public Diet save(final Diet diet) {
		Assert.notNull(diet);
		Assert.isInstanceOf(Nutritionist.class, this.actorService.findByPrincipal());
		final Nutritionist nutritionist = this.nutritionistService.findByPrincipal();
		diet.setNutritionist(nutritionist);
		Meal desayuno = mealService.create();
		desayuno.setDiet(diet);
		desayuno.setIngredients(new HashSet<Ingredient>());
		desayuno.setName("Desayuno");
		desayuno.setNutritionalValue("nutritional value");
		desayuno.setSchedule(new Date());
		Meal almuerzo= mealService.create();
		almuerzo.setNutritionalValue("nutritional value");
		almuerzo.setDiet(diet);
		almuerzo.setIngredients(new HashSet<Ingredient>());
		almuerzo.setName("Almuerzo");
		almuerzo.setSchedule(new Date());
		Meal cena= mealService.create();
		cena.setSchedule(new Date());
		cena.setNutritionalValue("nutritional value");
		cena.setDiet(diet);
		cena.setIngredients(new ArrayList<Ingredient>());
		cena.setName("Cena");
		Collection<Meal> meals = new ArrayList<Meal>();
		meals.add(desayuno);
		meals.add(almuerzo);
		meals.add(cena);
		diet.setMeals(meals);
		
		
		
		final Diet dietRes = this.dietRepository.save(diet);

		return dietRes;
	}

	public void delete(final Diet diet) {

		Assert.notNull(diet);
		this.checkPrincipal(diet);

		this.save(diet);

		this.dietRepository.delete(diet);
	}

	public Collection<Diet> findAll() {

		Collection<Diet> diets;

		diets = this.dietRepository.findAll();
		Assert.notNull(diets);

		return diets;
	}

	//Other bussiness methods
	// ==================================================================

	private void checkPrincipal(final Diet diet) {
		// comprueba qe el curriculum pertence al usuario
		final Nutritionist nutritionist = this.nutritionistService.findByPrincipal();
		Assert.isTrue(diet.getNutritionist().equals(nutritionist));
	}

	public Collection<Diet> findDietsByNutritionist(final int nutritionistId) {
		Collection<Diet> diets;

		diets = this.dietRepository.findDietsByNutritionist(nutritionistId);
		Assert.notNull(diets);

		return diets;

	}

	public Collection<Diet> findMyDiets() {
		Collection<Diet> diets;

		final Customer customer = this.customerService.findByPrincipal();

		diets = this.dietRepository.findMyDiets(customer.getId());
		Assert.notNull(diets);

		return diets;
	}

	public Collection<Diet> findMyDietsId(final int customerId) {
		Collection<Diet> diets;

		diets = this.dietRepository.findMyDiets(customerId);
		Assert.notNull(diets);

		return diets;
	}
}
