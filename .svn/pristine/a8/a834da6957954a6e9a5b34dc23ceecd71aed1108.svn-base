
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import controllers.AbstractController;

@RequestMapping("/customer/administrator")
@Controller
public class CustomerAdministratorController extends AbstractController {

	public CustomerAdministratorController() {
		super();
	}


	@Autowired
	private CustomerService	customerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView resul = new ModelAndView("customer/list");

		resul.addObject("customers", customerService.findAll());
		resul.addObject("requestURI", "administrator/customer/list.do");
		return resul;
	}
}
