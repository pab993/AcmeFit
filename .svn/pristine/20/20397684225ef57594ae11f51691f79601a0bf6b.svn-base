
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdditionalServiceService;
import domain.AdditionalService;

@Controller
@RequestMapping("/service")
public class AdditionalServiceController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public AdditionalServiceController() {
		super();
	}


	// Services -----------------------------------------------------------

	@Autowired
	private AdditionalServiceService	additionalServiceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView view;
		Collection<AdditionalService> services;

		services = this.additionalServiceService.findAll();

		view = new ModelAndView("service/list");
		view.addObject("services", services);
		view.addObject("requestURI", "service/list.do");

		return view;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int serviceId) {
		ModelAndView view;
		AdditionalService service;

		service = this.additionalServiceService.findOne(serviceId);

		view = new ModelAndView("service/display");
		view.addObject("service", service);
		view.addObject("assessments", service.getAssessments());
		view.addObject("requestURI", "service/display.do");

		return view;
	}

}
