
package controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import domain.Activity;

@Controller
@RequestMapping("/activity/customer")
public class ActivityCustomerController {

	public ActivityCustomerController() {
		super();
	}


	// Services ---------------

	@Autowired
	private ActivityService	activityService;


	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public ModelAndView signUp(@RequestParam int activityId) {

		ModelAndView resul;

		try {

			activityService.signUp(activityId);
			resul = new ModelAndView("redirect:/activity/list.do");

		} catch (Exception e) {
			resul = createDisplayModelAndView(activityId, "activity.customer.error");
		}

		return resul;
	}

	private ModelAndView createDisplayModelAndView(int activityId, String message) {
		// TODO Auto-generated method stub

		ModelAndView resul = new ModelAndView("activity/display");
		Activity activity = activityService.findOne(activityId);

		resul.addObject("activity", activity);

		resul.addObject("message", message);
		return resul;
	}

}
