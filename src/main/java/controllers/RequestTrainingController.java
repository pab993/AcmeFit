
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
import services.RequestTrainingService;
import services.TrainerService;
import domain.Customer;
import domain.RequestTraining;
import domain.Trainer;
import forms.RequestTrainingForm;

@Controller
@RequestMapping("/requestTraining")
public class RequestTrainingController extends AbstractController {

	//Services
	//=======================================================

	@Autowired
	private RequestTrainingService	requestTrainingService;

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private TrainerService			trainerService;


	// Listing RequestTraining with status PENDING
	// ====================================================================================

	@RequestMapping(value = "/myListPending", method = RequestMethod.GET)
	public ModelAndView listRequestTrainingsPending() {
		ModelAndView result;
		Collection<RequestTraining> requestTrainings;
		Customer customer;
		Boolean owner;
		Boolean owner2;

		customer = this.customerService.findByPrincipal();
		requestTrainings = this.requestTrainingService.findAllPendingByCustomer(customer);
		owner = true;
		owner2 = true;

		result = new ModelAndView("requestTraining/list");
		result.addObject("requestTrainings", requestTrainings);
		result.addObject("owner", owner);
		result.addObject("owner2", owner2);
		result.addObject("requestURI", "requestTraining/list.do");

		return result;
	}

	// Listing
	// ====================================================================================

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView listRequestTrainings() {
		ModelAndView result;
		Collection<RequestTraining> requestTrainings;
		Customer customer;
		Boolean owner;

		customer = this.customerService.findByPrincipal();
		requestTrainings = this.requestTrainingService.findAllByCustomer(customer);
		owner = true;

		result = new ModelAndView("requestTraining/list");
		result.addObject("requestTrainings", requestTrainings);
		result.addObject("owner", owner);
		result.addObject("requestURI", "requestTraining/list.do");

		return result;
	}

	@RequestMapping(value = "/myPTList", method = RequestMethod.GET)
	public ModelAndView listPT() {
		ModelAndView result;
		Collection<RequestTraining> requestTrainings;
		Trainer trainer;

		trainer = this.trainerService.findByPrincipal();
		requestTrainings = this.requestTrainingService.findAllByTrainer(trainer.getId());

		result = new ModelAndView("requestTraining/myList");
		result.addObject("requestTrainings", requestTrainings);
		result.addObject("requestURI", "requestTraining/myPTList.do");

		return result;
	}

	@RequestMapping(value = "/cancelled", method = RequestMethod.GET)
	public ModelAndView cancelled(@RequestParam int requestTrainingId) {
		ModelAndView result;

		this.requestTrainingService.cancelled(requestTrainingId);
		result = this.listRequestTrainingsPending();

		return result;
	}

	//Edit application
	// ======================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int requestTrainingId) {

		ModelAndView result;
		Trainer trainer = this.trainerService.findByPrincipal();

		RequestTraining requestTraining = this.requestTrainingService.findOne(requestTrainingId);
		Assert.isTrue(requestTraining.getPersonalTraining().getTrainer().equals(trainer));
		result = new ModelAndView("requestTraining/edit");
		RequestTrainingForm requestTrainingForm = this.requestTrainingService.reconstructForm(requestTraining);
		result.addObject("requestTrainingForm", requestTrainingForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(RequestTrainingForm requestTrainingForm, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(requestTrainingForm);
		else
			try {
				RequestTraining requestTraining = this.requestTrainingService.reconstructByTrainer(requestTrainingForm, binding);
				this.requestTrainingService.save(requestTraining);
				result = new ModelAndView("redirect:/requestTraining/myPTList.do");

			} catch (Throwable oops) {
				result = this.createEditModelAndView(requestTrainingForm, "error.commit.request");
			}
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(RequestTrainingForm requestTrainingForm, BindingResult binding) {
		ModelAndView result;

		RequestTraining requestTraining = this.requestTrainingService.findOne(requestTrainingForm.getRequestTrainingId());

		try {
			this.requestTrainingService.delete(requestTraining);
			result = new ModelAndView("redirect:/requestTraining/myPTList.do");
		} catch (Throwable oops) {
			result = this.createEditModelAndView(requestTrainingForm, "error.commit.request");
		}
		return result;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(final RequestTrainingForm requestTrainingForm) {
		ModelAndView result;

		result = this.createEditModelAndView(requestTrainingForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final RequestTrainingForm requestTrainingForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("requestTraining/display");

		result.addObject("trainerId", requestTrainingForm.getPersonalTraining().getTrainer().getId());
		result.addObject("requestTrainingForm", requestTrainingForm);
		result.addObject("message", message);

		return result;
	}
}
