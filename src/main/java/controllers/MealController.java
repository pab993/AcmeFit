
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MealService;
import domain.Meal;

@Controller
@RequestMapping("/meal")
public class MealController extends AbstractController {

	@Autowired
	private MealService			mealService;




	// Constructors
	// ====================================================================================

	public MealController() {
		super();
	}

	// Display Trainer
	// ====================================================================================
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int mealId) {

		final Meal meal= this.mealService.findOne(mealId);

		final ModelAndView resul = new ModelAndView("meal/display");
		resul.addObject("meal", meal);
		resul.addObject("ingredients", meal.getIngredients());
		resul.addObject("requestURI", "meal/display.do");

		return resul;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int mealId) {

		final Meal meal= this.mealService.findOne(mealId);

		final ModelAndView resul = new ModelAndView("meal/edit");
		resul.addObject("meal", meal);
		resul.addObject("requestURI", "meal/edit.do");

		return resul;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "submit")
	public ModelAndView edit(final Meal meal, final BindingResult binding) {

		ModelAndView result;

		try {
			if (binding.hasErrors()) {
				result = new ModelAndView("meal/edit");
				result.addObject("meal", meal);
				result.addObject("requestURI", "meal/edit.do");
			} else {

				this.mealService.save(meal);

				result = new ModelAndView("redirect:/diet/display.do?dietId="+meal.getId());
			}
		} catch (final Throwable oops) {
			result = new ModelAndView("meal/edit");
			result.addObject("meal", meal);
			result.addObject("requestURI", "meal/edit.do");
		}
		
		return result;
	}

}
