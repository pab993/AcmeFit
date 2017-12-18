/*
 * CustomerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.TrainerService;
import domain.Customer;
import domain.Trainer;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}


	//Supporting Services
	// ====================================================================================

	@Autowired
	private CustomerService	customerService;

	@Autowired
	private TrainerService	trainerService;


	//Listing
	// ====================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Collection<Customer> customers = this.customerService.findAll();

		result = new ModelAndView("customer/list");
		result.addObject("customers", customers);
		result.addObject("requestURI", "customer/list.do");
		return result;

	}
	
	@RequestMapping(value = "/requestTrainer", method = RequestMethod.GET)
	public ModelAndView requestTrainer(@RequestParam int trainerId) {
		ModelAndView result;

		Trainer t = trainerService.findOne(trainerId);
		Customer c = customerService.findByPrincipal();
		
		
		
			try {
				customerService.requestTrainer(t,c);
				result = new ModelAndView("redirect:/requestTraining/myListPending.do");

			} catch (final Throwable oops) {
				result = new ModelAndView("redirect:/trainer/list.do");
			}
		return result;
	
}
}
