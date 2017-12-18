
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import services.EmployeeService;
import domain.Curriculum;
import domain.Employee;
import forms.CurriculumForm;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController extends AbstractController {

	//Services
	//=======================================================

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private EmployeeService		employeeService;


	//Create
	//=======================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		//		final Employee employee = this.employeeService.findByPrincipal();
		CurriculumForm curriculumForm = new CurriculumForm();
		curriculumForm.setCurriculumId(0);
		result = this.createEditModelAndView(curriculumForm);

		return result;
	}

	//Listing
	// ====================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Curriculum curriculum = this.curriculumService.findByUserAccount();

		if (curriculum != null) {
			result = new ModelAndView("curriculum/list");
			result.addObject("curriculum", curriculum);
			result.addObject("requestURI", "curriculum/list.do");
		} else
			result = new ModelAndView("curriculum/noCurriculum");
		return result;

	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int employeeId) {
		ModelAndView result;

		Employee employee = this.employeeService.findOne(employeeId);
		Curriculum curriculum = this.curriculumService.findByEmployeeId(employee.getId());

		if (curriculum != null) {
			result = new ModelAndView("curriculum/display");
			result.addObject("curriculum", curriculum);
			result.addObject("requestURI", "curriculum/display.do");
		} else
			result = new ModelAndView("curriculum/display");
		return result;

	}

	//Edit
	// ====================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int curriculumId) {
		final Curriculum curriculum;
		ModelAndView result;

		curriculum = this.curriculumService.findByUserAccount();

		if (curriculum == null)
			result = new ModelAndView("redirect:create.do");
		else {

			result = new ModelAndView("curriculum/edit");
			CurriculumForm curriculumForm = this.curriculumService.reconstructForm(curriculum);
			result.addObject("curriculumForm", curriculumForm);

		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(CurriculumForm curriculumForm, BindingResult binding) {
		ModelAndView result;

		Curriculum curriculumNew;

		//curriculumNew = this.curriculumService.reconstruct(curriculumForm, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(curriculumForm);
		else
			try {
				curriculumNew = this.curriculumService.reconstruct(curriculumForm, binding);
				this.curriculumService.save(curriculumNew);
				result = new ModelAndView("redirect:/curriculum/list.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(curriculumForm, "curriculum.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(CurriculumForm curriculumForm, BindingResult binding) {
		ModelAndView result;

		Curriculum curriculum;

		curriculum = this.curriculumService.findOne(curriculumForm.getCurriculumId());
		try {
			this.curriculumService.delete(curriculum);
			result = new ModelAndView("redirect:/curriculum/list.do");
		} catch (Throwable oops) {
			result = this.createEditModelAndView(curriculumForm, "curriculum.commit.error");

		}
		return result;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(final CurriculumForm curriculumForm) {
		ModelAndView result;

		result = this.createEditModelAndView(curriculumForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final CurriculumForm curriculumForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("curriculum/edit");

		result.addObject("curriculumForm", curriculumForm);
		result.addObject("message", message);

		return result;
	}
}
