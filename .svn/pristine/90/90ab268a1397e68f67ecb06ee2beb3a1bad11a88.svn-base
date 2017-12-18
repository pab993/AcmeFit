/*
 * LoginController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CreditCardService;
import services.CustomerService;
import services.NutritionistService;
import services.TrainerService;
import controllers.AbstractController;
import domain.CreditCard;
import domain.Customer;
import domain.Nutritionist;
import domain.Trainer;
import forms.CustomerForm;
import forms.NutritionistForm;
import forms.TrainerForm;

@Controller
@RequestMapping("/security")
public class LoginController extends AbstractController {

	// Supporting services ----------------------------------------------------

	@Autowired
	private LoginService		service;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private TrainerService		trainerService;

	@Autowired
	private NutritionistService	nutritionistService;

	@Autowired
	private CreditCardService	creditCardService;


	// Constructors -----------------------------------------------------------

	public LoginController() {
		super();
	}

	// Login ------------------------------------------------------------------

	@RequestMapping("/login")
	public ModelAndView login(@Valid @ModelAttribute final Credentials credentials, final BindingResult bindingResult, @RequestParam(required = false) final boolean showError) {
		Assert.notNull(credentials);
		Assert.notNull(bindingResult);

		ModelAndView result;

		result = new ModelAndView("security/login");
		result.addObject("credentials", credentials);
		result.addObject("showError", showError);

		return result;
	}

	// Register ---------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {

		final ModelAndView resul;

		resul = new ModelAndView("security/register");
		CustomerForm customerForm = new CustomerForm();
		customerForm.setExpirationYear(2017);

		resul.addObject("customerForm", customerForm);
		return resul;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "submit")
	public ModelAndView save(CustomerForm customerForm, BindingResult binding) {

		ModelAndView resul;
		try {
			Customer customer = customerService.reconstruct(customerForm, binding);
			CreditCard creditCard;

			if (binding.hasErrors())
				resul = createRegisterView(customerForm);

			else {

				Customer saved = customerService.save(customer);	//	cliente almacenado

				// Si el cliente intentó registrar su tarjeta de crédito
				if (customerForm.isCheck2()) {

					creditCard = creditCardService.reconstruct(customerForm, saved, binding);
					if (binding.hasErrors())
						resul = createRegisterView(customerForm);
					else {

						creditCardService.save2(creditCard);		//tarjeta de cédito guardada
					}

				}

				resul = new ModelAndView("redirect:/welcome/index.do");
			}
		} catch (Throwable oops) {
			resul = createRegisterView(customerForm, "register.commit.error");
		}

		return resul;
	}
	// LoginFailure -----------------------------------------------------------

	private ModelAndView createRegisterView(CustomerForm customerForm, String message) {
		// TODO Auto-generated method stub

		ModelAndView resul = new ModelAndView("security/register");

		resul.addObject("customerForm", customerForm);
		resul.addObject("checkStatus", customerForm.isCheck2());

		resul.addObject("message", message);
		return resul;
	}
	private ModelAndView createRegisterView(CustomerForm customerForm) {
		// TODO Auto-generated method stub
		return createRegisterView(customerForm, null);
	}

	// Register trainer ---------------------------------------------------------------

	@RequestMapping(value = "/registerTrainer", method = RequestMethod.GET)
	public ModelAndView registerTrainer() {

		final ModelAndView resul;

		resul = new ModelAndView("security/registerTrainer");
		TrainerForm trainerForm = new TrainerForm();
		trainerForm.setExpirationYear(2017);

		resul.addObject("trainerForm", trainerForm);
		return resul;
	}

	@RequestMapping(value = "/registerTrainer", method = RequestMethod.POST, params = "submit")
	public ModelAndView save(TrainerForm trainerForm, BindingResult binding) {

		ModelAndView resul;
		try {
			Trainer trainer = trainerService.reconstruct(trainerForm, binding);
			CreditCard creditCard;

			if (binding.hasErrors())
				resul = createRegisterView2(trainerForm);

			else {

				Trainer saved = trainerService.save(trainer);	//	trainer almacenado

				// Si el trainer intentó registrar su tarjeta de crédito
				if (trainerForm.isCheck2()) {

					creditCard = creditCardService.reconstruct2(trainerForm, saved, binding);
					if (binding.hasErrors())
						resul = createRegisterView2(trainerForm);
					else {

						creditCardService.save2(creditCard);		//tarjeta de cédito guardada
					}

				}

				resul = new ModelAndView("redirect:/welcome/index.do");
			}
		} catch (Throwable oops) {
			resul = createRegisterView2(trainerForm, "register.commit.error");
		}

		return resul;
	}
	// LoginFailure -----------------------------------------------------------

	private ModelAndView createRegisterView2(TrainerForm trainerForm, String message) {
		// TODO Auto-generated method stub

		ModelAndView resul = new ModelAndView("security/registerTrainer");

		resul.addObject("trainerForm", trainerForm);
		resul.addObject("checkStatus", trainerForm.isCheck2());

		resul.addObject("message", message);
		return resul;
	}
	private ModelAndView createRegisterView2(TrainerForm trainerForm) {
		// TODO Auto-generated method stub
		return createRegisterView2(trainerForm, null);
	}

	// Register nutritionist ---------------------------------------------------------------

	@RequestMapping(value = "/registerNutritionist", method = RequestMethod.GET)
	public ModelAndView registerNutritionist() {

		final ModelAndView resul;

		resul = new ModelAndView("security/registerNutritionist");
		NutritionistForm nutritionistForm = new NutritionistForm();
		nutritionistForm.setExpirationYear(2017);

		resul.addObject("nutritionistForm", nutritionistForm);
		return resul;
	}

	@RequestMapping(value = "/registerNutritionist", method = RequestMethod.POST, params = "submit")
	public ModelAndView save(NutritionistForm nutritionistForm, BindingResult binding) {

		ModelAndView resul;
		try {
			Nutritionist nutritionist = nutritionistService.reconstruct(nutritionistForm, binding);
			CreditCard creditCard;

			if (binding.hasErrors())
				resul = createRegisterView3(nutritionistForm);

			else {

				Nutritionist saved = nutritionistService.save(nutritionist);	//	nutritionist almacenado

				// Si el nutritionist intentó registrar su tarjeta de crédito
				if (nutritionistForm.isCheck2()) {

					creditCard = creditCardService.reconstruct3(nutritionistForm, saved, binding);
					if (binding.hasErrors())
						resul = createRegisterView3(nutritionistForm);
					else {

						creditCardService.save2(creditCard);		//tarjeta de cédito guardada
					}

				}

				resul = new ModelAndView("redirect:/welcome/index.do");
			}
		} catch (Throwable oops) {
			resul = createRegisterView3(nutritionistForm, "register.commit.error");
		}

		return resul;
	}

	// LoginFailure -----------------------------------------------------------

	private ModelAndView createRegisterView3(NutritionistForm nutritionistForm, String message) {
		// TODO Auto-generated method stub

		ModelAndView resul = new ModelAndView("security/registerNutritionist");

		resul.addObject("nutritionistForm", nutritionistForm);
		resul.addObject("checkStatus", nutritionistForm.isCheck2());

		resul.addObject("message", message);
		return resul;
	}
	private ModelAndView createRegisterView3(NutritionistForm nutritionistForm) {
		// TODO Auto-generated method stub
		return createRegisterView3(nutritionistForm, null);
	}

	@RequestMapping("/loginFailure")
	public ModelAndView failure() {
		ModelAndView result;

		result = new ModelAndView("redirect:login.do?showError=true");

		return result;
	}

}
