
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import domain.Activity;

@Controller
@RequestMapping("/activity")
public class SearchActivityController extends AbstractController {

	//Services
	//=======================================================

	@Autowired
	private ActivityService	activityService;


	//Search
	// ====================================================================================

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Collection<Activity> activities = this.activityService.findAll();

		result = new ModelAndView("activity/search");
		result.addObject("activities", activities);
		result.addObject("requestURI", "activity/search.do");

		return result;

	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(@RequestParam(value = "keyword") final String word) {
		ModelAndView result;
		final Collection<Activity> activities;

		activities = this.activityService.search(word);
		result = new ModelAndView("activity/search");
		result.addObject("activities", activities);
		result.addObject("requestURI", "activity/search.do");

		return result;
	}

	// Ancillary Methods
	// ===============================================================================
}
