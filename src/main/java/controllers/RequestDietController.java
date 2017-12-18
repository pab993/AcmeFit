
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.NutritionistService;
import services.RequestDietService;
import domain.Customer;
import domain.Nutritionist;
import domain.RequestDiet;
import forms.RequestDietForm;

@Controller
@RequestMapping("/requestDiet")
public class RequestDietController extends AbstractController {

	//Services
	//=======================================================

	@Autowired
	private RequestDietService	requestDietService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private NutritionistService	nutritionistService;


	// Listing 
	// ====================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<RequestDiet> requestDiets;

		requestDiets = this.requestDietService.findRequestByNutritionist();

		result = new ModelAndView("requestDiet/list");
		result.addObject("requestsDiets", requestDiets);
		result.addObject("requestURI", "requestDiet/list.do");

		return result;
	}

	// Listing RequestDiet with status PENDING
	// ====================================================================================

	@RequestMapping(value = "/myListPending", method = RequestMethod.GET)
	public ModelAndView listRequestDietsPending() {
		ModelAndView result;
		Collection<RequestDiet> requestDiets;
		Customer customer;
		Boolean owner;
		Boolean owner2;

		customer = this.customerService.findByPrincipal();
		requestDiets = this.requestDietService.findAllPendingByCustomer(customer);
		owner = true;
		owner2 = true;

		result = new ModelAndView("requestDiet/myList");
		result.addObject("requestDiets", requestDiets);
		result.addObject("owner", owner);
		result.addObject("owner2", owner2);
		result.addObject("requestURI", "requestDiet/list.do");

		return result;
	}

	// Listing 
	// ====================================================================================

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView listRequestDiets() {
		ModelAndView result;
		Collection<RequestDiet> requestDiets;
		Customer customer;
		Boolean owner;

		customer = this.customerService.findByPrincipal();
		requestDiets = this.requestDietService.findAllByCustomer(customer);
		owner = true;

		result = new ModelAndView("requestDiet/myList");
		result.addObject("requestDiets", requestDiets);
		result.addObject("owner", owner);
		result.addObject("requestURI", "requestDiet/list.do");

		return result;
	}

	@RequestMapping(value = "/cancelled", method = RequestMethod.GET)
	public ModelAndView cancelled(@RequestParam int requestDietId) {
		ModelAndView result;

		this.requestDietService.cancelled(requestDietId);
		result = this.listRequestDietsPending();

		return result;
	}

	//Edit application
	// ======================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int requestDietId) {

		ModelAndView result;
		Nutritionist nutritionist = this.nutritionistService.findByPrincipal();

		RequestDiet requestDiet = this.requestDietService.findOne(requestDietId);
		Assert.isTrue(requestDiet.getDiet().getNutritionist().equals(nutritionist));
		result = new ModelAndView("requestDiet/edit");
		RequestDietForm requestDietForm = this.requestDietService.reconstructForm(requestDiet);
		result.addObject("requestDietForm", requestDietForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(RequestDietForm requestDietForm, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(requestDietForm);
		else
			try {
				RequestDiet requestDiet = this.requestDietService.reconstructByNutritionist(requestDietForm, binding);
				this.requestDietService.save(requestDiet);
				result = new ModelAndView("redirect:/requestDiet/list.do");

			} catch (Throwable oops) {
				result = this.createEditModelAndView(requestDietForm, "error.commit.request");
			}
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(RequestDietForm requestDietForm, BindingResult binding) {
		ModelAndView result;

		RequestDiet requestDiet = this.requestDietService.findOne(requestDietForm.getRequestDietId());

		try {
			this.requestDietService.delete(requestDiet);
			result = new ModelAndView("redirect:/requestDiet/list.do");
		} catch (Throwable oops) {
			result = this.createEditModelAndView(requestDietForm, "error.commit.request");
		}
		return result;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(final RequestDietForm requestDietForm) {
		ModelAndView result;

		result = this.createEditModelAndView(requestDietForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final RequestDietForm requestDietForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("requestDiet/display");

		result.addObject("nutritionistId", requestDietForm.getDiet().getNutritionist().getId());
		result.addObject("requestDietForm", requestDietForm);
		result.addObject("message", message);

		return result;
	}
}
