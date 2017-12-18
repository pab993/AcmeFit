
package controllers.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CompetitionService;
import services.CustomerService;
import controllers.AbstractController;
import domain.Competition;

@Controller
@RequestMapping("/competition/customer")
public class CompetitionCustomerController extends AbstractController {

	public CompetitionCustomerController() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Autowired
	CompetitionService	competitionService;
	@Autowired
	CustomerService		customerService;


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam int competitionId) {

		ModelAndView resul;

		Competition competition = competitionService.findOne(competitionId);

		try {

			customerService.registerCompetition(competition);
			resul = new ModelAndView("redirect:/competition/list.do");

		} catch (Throwable oops) {
			resul = createListView("error.competition.customer.regiter");
		}

		return resul;
	}

	private ModelAndView createListView(String message) {
		ModelAndView result;
		Collection<Competition> competitions;

		competitions = this.competitionService.findAll();

		result = new ModelAndView("competition/list");
		result.addObject("competitions", competitions);
		result.addObject("requestURI", "competition/list.do");
		result.addObject("message", message);

		return result;
	}
}
