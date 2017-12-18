package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import domain.Activity;
import domain.Assessment;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

	// Services
	// =======================================================

	@Autowired
	private ActivityService activityService;

	// Listing
	// ====================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listActivities() {
		ModelAndView result;
		Collection<Activity> activities;

		activities = this.activityService.findAll();
		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("requestURI", "activity/list.do");

		return result;
	}

	// Listing Activities
	// ====================================================================================

	@RequestMapping(value = "/listActivities", method = RequestMethod.GET)
	public ModelAndView listAcitivities(@RequestParam final int customerId) {
		ModelAndView result;

		final Collection<Activity> customerActivities = this.activityService
				.findAllByCustomerId(customerId);

		result = new ModelAndView("activity/list");
		result.addObject("activities", customerActivities);
		result.addObject("requestURI", "activity/list.do");
		return result;

	}

	// Display Activity
	// ====================================================================================

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int activityId) {
		ModelAndView result;
		Activity activity;

		activity = this.activityService.findOne(activityId);
		final Collection<Assessment> assessments = activity.getAssessments();

		// Comprobar si actividad está en principal

		result = new ModelAndView("activity/display");
		result.addObject("activity", activity);
		result.addObject("assessments", assessments);
		result.addObject("requestURI", "activity/display.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		final Activity activity = this.activityService.create();

		final ModelAndView resul = new ModelAndView("activity/create");
		resul.addObject("activity", activity);
		resul.addObject("requestURI", "activity/create.do");

		return resul;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "submit")
	public ModelAndView create(final Activity activity, final BindingResult binding) {

		ModelAndView result;
		try {
			if (binding.hasErrors()){
				result = new ModelAndView("activity/create");
			result.addObject("activity", activity);
			result.addObject("requestURI", "activity/create.do");
			}
			else {

				this.activityService.save(activity);

				result = new ModelAndView("redirect:/activity/list.do");
			}
		} catch (final Throwable oops) {
			result = new ModelAndView("activity/create");
			result.addObject("activity", activity);
			result.addObject("requestURI", "activity/create.do");
		}
				return result;
	}

	// Ancillary Methods
	// ===============================================================================
}
