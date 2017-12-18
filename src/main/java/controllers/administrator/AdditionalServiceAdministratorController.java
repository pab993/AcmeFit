
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.AdditionalServiceService;
import controllers.AbstractController;
import domain.AdditionalService;

@Controller
@RequestMapping("/service/administrator")
public class AdditionalServiceAdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public AdditionalServiceAdministratorController() {
		super();
	}


	// Services -----------------------------------------------------------

	@Autowired
	private AdditionalServiceService	additionalServiceService;


	@RequestMapping(value = "/create.do", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView view;
		AdditionalService service;

		service = this.additionalServiceService.create();
		view = this.createEditModelAndView(service);

		return view;
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int serviceId) {
		ModelAndView view;
		AdditionalService service;

		service = this.additionalServiceService.findOne(serviceId);
		view = this.createEditModelAndView(service);

		return view;
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(AdditionalService service, BindingResult binding, RedirectAttributes redirectAttrs) {
		ModelAndView view;

		try {
			service = this.additionalServiceService.reconstruct(service, binding);

			if (binding.hasErrors())
				view = this.createEditModelAndView(service);
			else {
				service = this.additionalServiceService.save(service);
				view = new ModelAndView("redirect:/service/list.do");
				redirectAttrs.addFlashAttribute("message", "service.commit.ok");
			}
		} catch (Throwable oops) {
			if (binding.hasErrors())
				view = this.createEditModelAndView(service);
			else
				view = this.createEditModelAndView(service, "service.commit.error");
		}

		return view;
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(AdditionalService service, RedirectAttributes redirectAttrs) {
		ModelAndView view;

		try {
			this.additionalServiceService.delete(service);
			view = new ModelAndView("redirect:/service/list.do");
			redirectAttrs.addFlashAttribute("message", "service.commit.ok");
		} catch (Throwable oops) {
			view = this.createEditModelAndView(service, "service.commit.error");
		}

		return view;
	}

	private ModelAndView createEditModelAndView(AdditionalService service) {
		return this.createEditModelAndView(service, null);
	}

	private ModelAndView createEditModelAndView(AdditionalService service, String message) {
		ModelAndView view;

		view = new ModelAndView("service/edit");
		view.addObject("additionalService", service);
		view.addObject("message", message);

		return view;
	}
}
