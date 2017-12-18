
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.CompetitionService;
import controllers.AbstractController;
import domain.Competition;

@Controller
@RequestMapping("/competition/administrator")
public class CompetitionAdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public CompetitionAdministratorController() {
		super();
	}


	// Services -----------------------------------------------------------

	@Autowired
	private CompetitionService	competitionService;


	@RequestMapping(value = "/create.do", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView view;
		Competition competition;

		competition = this.competitionService.create();

		view = this.createModelAndView(competition);

		return view;
	}

	@RequestMapping(value = "/create.do", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Competition competition, BindingResult binding, RedirectAttributes redirectAttrs) {
		ModelAndView view;

		try {
			competition = this.competitionService.reconstruct(competition, binding);

			if (binding.hasErrors())
				view = this.createModelAndView(competition);
			else {
				competition = this.competitionService.save(competition);
				view = new ModelAndView("redirect:/competition/list.do");
				redirectAttrs.addFlashAttribute("message", "competition.commit.ok");
			}
		} catch (Throwable oops) {
			if (binding.hasErrors())
				view = this.createModelAndView(competition);
			else
				view = this.createModelAndView(competition, "competition.commit.error");
		}

		return view;
	}

	// Ancillary Methods
	// ===============================================================================

	private ModelAndView createModelAndView(Competition competition) {
		return this.createModelAndView(competition, null);
	}

	private ModelAndView createModelAndView(Competition competition, String message) {
		ModelAndView view;

		view = new ModelAndView("competition/edit");
		view.addObject("competition", competition);
		view.addObject("message", message);

		return view;
	}

}
