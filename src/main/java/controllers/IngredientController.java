package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.IngredientService;
import services.MealService;
import services.RequestDietService;
import domain.Customer;
import domain.Diet;
import domain.Ingredient;
import domain.Meal;
import domain.RequestDiet;

@Controller
@RequestMapping("/ingredient")
public class IngredientController extends AbstractController {

	// Services
	// =========================================================

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private MealService mealService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RequestDietService requestDietService;

	// Listing
	// ===========================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int mealId) {
		ModelAndView result;
		Collection<Ingredient> ingredients;

		Meal meal = this.mealService.findOne(mealId);
		Diet diet = meal.getDiet();

		Customer customer = this.customerService.findByPrincipal();
		Boolean var = false;
		if (customer != null) {
			Collection<RequestDiet> requestsDiets = this.requestDietService
					.findAllAcceptedByCustomer(customer);
			Collection<Diet> diets = new ArrayList<Diet>();
			for (RequestDiet r : requestsDiets)
				diets.add(r.getDiet());
			if (diets.contains(diet))
				var = true;
		}

		ingredients = meal.getIngredients();

		result = new ModelAndView("ingredient/list");
		result.addObject("ingredients", ingredients);
		result.addObject("var", var);
		result.addObject("dietId", diet.getId());
		result.addObject("requestURI", "ingredient/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int mealId) {
		ModelAndView result;
		Meal meal = mealService.findOne(mealId);
		Ingredient ingredient = ingredientService.create();
		/*try {
			ingredientService.save(ingredient);
			meal.getIngredients().add(ingredient);
			mealService.save(meal);
			result = new ModelAndView("ingredient/create");
			result.addObject("ingredient", ingredient);
			result.addObject("requestURI", "ingredient/create.do");

		} catch (final Throwable oops) {
			result = new ModelAndView("meal/display");
			result.addObject("meal", meal);
			result.addObject("requestURI", "meal/display.do");
		}
*/
		Ingredient s = ingredientService.save(ingredient);
meal.getIngredients().add(s);
mealService.save(meal);
result = new ModelAndView("ingredient/create");
result.addObject("ingredient", s);
result.addObject("requestURI", "ingredient/create.do");

		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "submit")
	public ModelAndView create(final Ingredient ingredient, final BindingResult bindingResult) {
		ModelAndView result;
		try {

			this.ingredientService.save(ingredient);
			result = new ModelAndView("redirect:/welcome/index.do");

		} catch (final Throwable oops) {
			result = new ModelAndView("ingredient/create");
			result.addObject("ingredient", ingredient);
			result.addObject("requestURI", "ingredient/create.do");
		}
		return result;
	}
	
	

}
