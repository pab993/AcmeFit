
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import domain.Actor;
import domain.Assessment;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	@Autowired
	private ActorService	actorService;


	//	@Autowired
	//	private AssessmentService assessmentService;

	// Constructors
	// ====================================================================================

	public ActorController() {
		super();
	}

	//Display Actor
	// ====================================================================================

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int actorId) {

		final Actor actor = this.actorService.findOne(actorId);
		final Collection<Assessment> assessments = actor.getAssessments();
		final Actor principal = this.actorService.findByPrincipal();

		final ModelAndView resul = new ModelAndView("actor/display");
		resul.addObject("actor", actor);
		resul.addObject("principal", principal);
		resul.addObject("assessments", assessments);
		resul.addObject("requestURI", "actor/display.do");

		return resul;
	}

	@RequestMapping(value = "/displayPrincipal", method = RequestMethod.GET)
	public ModelAndView displayPrincipal() {

		final Actor actor = this.actorService.findByPrincipal();
		final Collection<Assessment> assessments = actor.getAssessments();

		final ModelAndView resul = new ModelAndView("actor/display");
		resul.addObject("actor", actor);
		resul.addObject("assessments", assessments);
		resul.addObject("requestURI", "actor/display.do");

		return resul;
	}

	//Edit Actor
	// ====================================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		final Actor actor = this.actorService.findByPrincipal();

		final ModelAndView resul = new ModelAndView("actor/edit");
		resul.addObject("actor", actor);
		resul.addObject("requestURI", "actor/edit.do");

		return resul;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "submit")
	public ModelAndView edit(final Actor actor, final BindingResult binding) {

		ModelAndView result;
		try {
			final Actor actorRec = this.actorService.reconstruct(actor, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(actor);

			else {

				this.actorService.save(actorRec);

				result = new ModelAndView("redirect:/actor/displayPrincipal.do");
			}
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(actor, "edit.commit.error");
		}

		return result;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(final Actor actor) {
		ModelAndView result;

		result = this.createEditModelAndView(actor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Actor actor, final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", actor);
		result.addObject("message", message);

		return result;
	}

}
