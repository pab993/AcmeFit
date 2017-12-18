
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AssessmentService;
import domain.Assessment;
import forms.AssessmentForm;

@Controller
@RequestMapping("/assessment")
public class AssessmentController extends AbstractController {

	//Services
	//=======================================================

	@Autowired
	private AssessmentService assessmentService;

	
	//Create Assessment
	//=======================================================
	
	@RequestMapping(value = "/postAssessment", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int comentableId) {
		ModelAndView result;
		AssessmentForm assessmentForm = new AssessmentForm();
		assessmentForm.setIdComentable(comentableId);

		result = new ModelAndView("assessment/postAssessment");
		result.addObject("assessmentForm", assessmentForm);

		return result;

	}

	@RequestMapping(value = "/postAssessment", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid AssessmentForm assessmentForm,
			BindingResult binding) {
		ModelAndView result;
		System.out.println(binding.getAllErrors());
		Assessment assessment = assessmentService.reconstruct(assessmentForm, binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView(assessmentForm);

		} else {
			try {

				assessmentService.save(assessment);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(assessmentForm,
						"assessment.commit.error");
			}
		}

		return result;
	}

//	@RequestMapping(value = "/ban", method = RequestMethod.GET)
//	public ModelAndView ban(@RequestParam int assessmentId) {
//		assessmentService.banMethod(assessmentId);
//		ModelAndView result = new ModelAndView("redirect:/welcome/index.do");
//		return result;
//	}

	// Ancillary Methods
	// ===============================================================================
	
	protected ModelAndView createEditModelAndView(AssessmentForm assessmentForm) {
		ModelAndView result;

		result = createEditModelAndView(assessmentForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(AssessmentForm assessmentForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("assessment/postAssessment");

		result.addObject("assessmentForm", assessmentForm);
		result.addObject("message", message);

		return result;
	}


}
