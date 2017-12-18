/*
 * AdministratorController.java
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

import services.ActorService;
import services.AssessmentService;
import services.CustomerService;
import services.PersonalTrainingService;
import services.TrainerService;
import domain.Assessment;
import domain.PersonalTraining;
import domain.Trainer;

@Controller
@RequestMapping("/trainer")
public class TrainerController extends AbstractController {

	@Autowired
	private TrainerService			trainerService;

	@Autowired
	private AssessmentService		assessmentService;

	@Autowired
	private ActorService			actorService;
	@Autowired
	private CustomerService			customerService;

	@Autowired
	private PersonalTrainingService	personalTrainingService;


	// Constructors
	// ====================================================================================

	public TrainerController() {
		super();
	}

	//Display Trainer
	// ====================================================================================

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int trainerId) {
		ModelAndView result;
		Trainer trainer;

		trainer = this.trainerService.findOne2(trainerId);
		Collection<Assessment> assessments = trainer.getAssessments();
		result = new ModelAndView("trainer/display");
		result.addObject("trainer", trainer);
		result.addObject("assessments", assessments);
		result.addObject("requestURI", "trainer/display.do");

		return result;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listTrainer() {
		ModelAndView result;
		Collection<Trainer> trainers;

		trainers = this.trainerService.findAll();
		//Boolean principalValido = customerService.principalValido();
		Boolean principalValido = true;

		result = new ModelAndView("trainer/list");
		result.addObject("trainers", trainers);
		result.addObject("principalNoValido", !principalValido);
		result.addObject("requestURI", "trainer/list.do");

		return result;
	}

	@RequestMapping(value = "/personalTraining", method = RequestMethod.GET)
	public ModelAndView listTrainer(@RequestParam int trainerId) {
		ModelAndView result;
		Collection<PersonalTraining> personalTrainings;

		personalTrainings = this.personalTrainingService.findByTrainerId(trainerId);

		result = new ModelAndView("personalTraining/list");
		result.addObject("personalTrainings", personalTrainings);
		result.addObject("requestURI", "personalTrainings/list.do");

		return result;
	}

}
