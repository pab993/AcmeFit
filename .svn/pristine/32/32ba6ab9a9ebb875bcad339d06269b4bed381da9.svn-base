
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CreditCardService;
import domain.Actor;
import domain.CreditCard;

@Controller
@RequestMapping("/creditCard")
public class CreditCardController extends AbstractController {

	@Autowired
	private CreditCardService	creditCardService;

	@Autowired
	private ActorService		actorService;


	public CreditCardController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		CreditCard creditCard;
		Actor actor = this.actorService.findByPrincipal();
		creditCard = this.creditCardService.create();
		creditCard.setActor(actor);
		result = this.createEditModelAndView(creditCard);

		return result;
	}

	@RequestMapping("/edit")
	public ModelAndView edit() {
		CreditCard creditCard;
		ModelAndView result;

		Actor actor = this.actorService.findByPrincipal();

		creditCard = this.creditCardService.findByActorId(actor.getId());

		if (creditCard == null)
			result = new ModelAndView("redirect:create.do");
		else {

			result = new ModelAndView("creditCard/edit");
			result.addObject("creditCard", creditCard);

		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CreditCard creditCard, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(creditCard);
		else
			try {

				this.creditCardService.save(creditCard);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (Throwable oops) {
				result = this.createEditModelAndView(creditCard, "creditCard.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid CreditCard creditCard, BindingResult binding) {
		ModelAndView result;

		try {
			this.creditCardService.delete(creditCard);
			result = new ModelAndView("redirect:create.do");
		} catch (Throwable oops) {
			result = this.createEditModelAndView(creditCard, "creditCard.commit.error");
		}
		return result;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(CreditCard creditCard) {
		ModelAndView result;

		result = this.createEditModelAndView(creditCard, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(CreditCard creditCard, String message) {
		ModelAndView result;

		result = new ModelAndView("creditCard/edit");

		result.addObject("creditCard", creditCard);
		result.addObject("message", message);

		return result;
	}
}
