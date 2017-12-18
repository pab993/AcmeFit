
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CreditCardService;
import services.CustomerService;
import services.PersonalTrainingService;
import services.ReportService;
import services.RequestTrainingService;
import services.TrainerService;
import domain.Actor;
import domain.CreditCard;
import domain.Customer;
import domain.PersonalTraining;
import domain.Report;
import domain.RequestTraining;
import domain.Trainer;
import forms.RequestTrainingForm;

@Controller
@RequestMapping("/personalTraining")
public class PersonalTrainingController extends AbstractController {

	//Services
	// ===============================================================

	@Autowired
	private PersonalTrainingService	personalTrainingService;

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private TrainerService			trainerService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private CreditCardService		creditCardService;

	@Autowired
	private RequestTrainingService	requestTrainingService;

	@Autowired
	private ReportService			reportService;


	// Constructors
	// ====================================================================================

	public PersonalTrainingController() {
		super();
	}

	// Listing PersonalTrainings
	// ====================================================================================

	@RequestMapping(value = "/listPT", method = RequestMethod.GET)
	public ModelAndView listPT() {
		ModelAndView result;

		final Trainer principal = this.trainerService.findByPrincipal();
		final Collection<PersonalTraining> trainerPersonalTrainings = this.personalTrainingService.findByTrainerId(principal.getId());

		result = new ModelAndView("personalTraining/list");
		result.addObject("personalTrainings", trainerPersonalTrainings);
		result.addObject("requestURI", "personalTraining/listPT.do");
		return result;

	}
	// Create
	// =======================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int personalTrainingId) {
		ModelAndView result;

		final PersonalTraining personalTraining = this.personalTrainingService.findOne(personalTrainingId);

		final RequestTrainingForm requestTrainingForm = new RequestTrainingForm();
		requestTrainingForm.setRequestTrainingId(0);
		requestTrainingForm.setPersonalTraining(personalTraining);
		result = this.createEditModelAndView(requestTrainingForm);

		return result;
	}

	@RequestMapping(value = "/createPT", method = RequestMethod.GET)
	public ModelAndView createPT() {

		final PersonalTraining personalTraining = this.personalTrainingService.create();

		final ModelAndView resul = new ModelAndView("personalTraining/create");
		resul.addObject("personalTraining", personalTraining);
		resul.addObject("requestURI", "personalTraining/create.do");

		return resul;
	}

	@RequestMapping(value = "/createPT", method = RequestMethod.POST, params = "submit")
	public ModelAndView createPT(final @Valid PersonalTraining personalTraining, final BindingResult binding) {

		ModelAndView result;

		try {
			if (binding.hasErrors())
				result = this.createEditModelAndView(personalTraining);
			else {

				this.personalTrainingService.save(personalTraining);

				result = new ModelAndView("redirect:/personalTraining/listPT.do");
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(personalTraining, "error.commit.personalTraining");
		}
		return result;
	}

	@RequestMapping(value = "/createReport", method = RequestMethod.GET)
	public ModelAndView createReport(@RequestParam final int personalTrainingId) {

		final Report report = this.reportService.create();

		report.setId(personalTrainingId);

		final ModelAndView resul = new ModelAndView("personalTraining/createReport");
		resul.addObject("report", report);
		resul.addObject("requestURI", "personalTraining/createReport.do");

		return resul;
	}

	@RequestMapping(value = "/createReport", method = RequestMethod.POST, params = "submitReport")
	public ModelAndView createReport(final @Valid Report report, final BindingResult binding) {

		ModelAndView result;
		final PersonalTraining personalTraining = this.personalTrainingService.findOne(report.getId());
		report.setId(0);

		try {
			if (binding.hasErrors()) {
				result = new ModelAndView("personalTraining/createReport");
				result.addObject("report", report);
				result.addObject("requestURI", "personalTraining/createReport.do");
			} else {

				this.reportService.save(report);
				personalTraining.setReport(report);
				this.personalTrainingService.save(personalTraining);

				result = new ModelAndView("redirect:/personalTraining/listPT.do");
			}
		} catch (final Throwable oops) {
			result = new ModelAndView("personalTraining/createReport");
			result.addObject("report", report);
			result.addObject("requestURI", "personalTraining/createReport.do");
		}
		return result;
	}

	// Application
	// =====================================================================================

	@RequestMapping(value = "/application", method = RequestMethod.GET)
	public ModelAndView application(@RequestParam final int personalTrainingId) {

		ModelAndView result;
		final Customer customer = this.customerService.findByPrincipal();
		final Actor actor = this.actorService.findByPrincipal();
		final PersonalTraining personalTraining = this.personalTrainingService.findOne(personalTrainingId);
		final CreditCard creditCard = this.creditCardService.findByActorId(actor.getId());

		final RequestTraining requestTraining = this.requestTrainingService.findByCustomerAndPersonalTraining(customer.getId(), personalTrainingId);

		if (requestTraining == null) {

			if (creditCard == null || !this.creditCardService.checkValidity(creditCard)) {
				result = new ModelAndView("requestTraining/invalidCreditCard");
				result.addObject("requestURI", "personalTraining/invalidCreditCard.do");
				return result;
			} else
				result = new ModelAndView("redirect:create.do?personalTrainingId=" + personalTrainingId);

		} else {
			result = new ModelAndView("requestTraining/display");
			final RequestTrainingForm requestTrainingForm = this.requestTrainingService.reconstructForm(requestTraining);
			result.addObject("requestTrainingForm", requestTrainingForm);
			result.addObject("trainerId", personalTraining.getTrainer().getId());
		}

		return result;
	}

	@RequestMapping(value = "/invalidCreditCard", method = RequestMethod.GET)
	public ModelAndView invalidCreditCard() {
		ModelAndView result;

		result = new ModelAndView("requestTraining/invalidCreditCard");

		return result;
	}

	@RequestMapping(value = "/applicationConfirm", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final RequestTrainingForm requestTrainingForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(requestTrainingForm);
		else
			try {
				final RequestTraining requestTraining = this.requestTrainingService.reconstruct(requestTrainingForm, binding);
				this.requestTrainingService.save(requestTraining);
				result = new ModelAndView("redirect:/trainer/personalTraining.do?trainerId=" + requestTraining.getPersonalTraining().getTrainer().getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(requestTrainingForm, "error.commit.request");
			}
		return result;

	}

	@RequestMapping(value = "/applicationConfirm", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final RequestTrainingForm requestTrainingForm, final BindingResult binding) {
		ModelAndView result;

		final RequestTraining requestTraining = this.requestTrainingService.findOne(requestTrainingForm.getRequestTrainingId());

		try {
			this.requestTrainingService.delete(requestTraining);
			result = new ModelAndView("redirect:/trainer/personalTraining.do?trainerId=" + requestTraining.getPersonalTraining().getTrainer().getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(requestTrainingForm, "error.commit.request");
		}
		return result;
	}

	// Application
	// =====================================================================================

	@RequestMapping(value = "/displayReport", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int personalTrainingId) {
		final ModelAndView result;
		final PersonalTraining personalTraining = this.personalTrainingService.findOne(personalTrainingId);
		final Report report = personalTraining.getReport();

		result = new ModelAndView("personalTraining/displayReport");
		result.addObject("report", report);
		result.addObject("requestURI", "personalTraining/displayReports.do");

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

	protected ModelAndView createEditModelAndView(final PersonalTraining personalTraining) {
		ModelAndView result;

		result = this.createEditModelAndView(personalTraining, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final PersonalTraining personalTraining, final String message) {
		ModelAndView result;

		result = new ModelAndView("personalTraining/create");
		result.addObject("personalTraining", personalTraining);
		result.addObject("message", message);

		return result;
	}

}
