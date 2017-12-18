
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.DietService;
import services.NutritionistService;
import services.RequestDietService;
import domain.Customer;
import domain.Diet;
import domain.Nutritionist;
import domain.RequestDiet;

@Controller
@RequestMapping("/nutritionist")
public class NutritionistController extends AbstractController {

	@Autowired
	private NutritionistService	nutritionistService;

	@Autowired
	private DietService			dietService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private RequestDietService	requestDietService;


	// Constructors
	// ====================================================================================

	public NutritionistController() {
		super();
	}

	//Listing
	// ====================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Nutritionist> nutritionists = this.nutritionistService.findAll();

		result = new ModelAndView("nutritionist/list");
		result.addObject("nutritionists", nutritionists);
		result.addObject("requestURI", "nutritionist/list.do");
		return result;

	}

	@RequestMapping(value = "/diets", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int nutritionistId) {
		ModelAndView result;

		Collection<Diet> diets = this.dietService.findDietsByNutritionist(nutritionistId);

		Customer customer = this.customerService.findByPrincipal();
		Collection<RequestDiet> requestsDiets = this.requestDietService.findAllAcceptedByCustomer(customer);
		Collection<Diet> dietsF = new ArrayList<Diet>();

		for (RequestDiet r : requestsDiets)
			dietsF.add(r.getDiet());

		result = new ModelAndView("diet/list");
		result.addObject("diets", diets);
		result.addObject("dietsF", dietsF);
		result.addObject("requestURI", "diet/list.do");
		return result;

	}

}
