
package controllers.administrator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConfigurationSystemService;
import services.CustomerService;
import services.InvoiceService;
import controllers.AbstractController;
import domain.ConfigurationSystem;
import domain.Customer;
import domain.Diet;
import domain.Invoice;
import domain.PersonalTraining;
import domain.RequestDiet;
import domain.RequestTraining;
import forms.InvoiceForm;

@RequestMapping("/invoice/administrator")
@Controller
public class InvoiceAdministratorController extends AbstractController {

	public InvoiceAdministratorController() {
		super();
	}


	@Autowired
	private InvoiceService				invoiceService;

	@Autowired
	private CustomerService				customerService;

	@Autowired
	private ConfigurationSystemService	configurationSystemService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int customerId) {

		Invoice invoice = invoiceService.create();
		Customer customer = customerService.findOne(customerId);

		/*
		 * sss
		 */

		if (!customer.getInvoices().isEmpty()) {

			Calendar fechaActual = Calendar.getInstance();
			int mesActual = fechaActual.get(Calendar.MONTH) + 1;
			int anioActual = fechaActual.get(Calendar.YEAR);

			Collection<Invoice> invoices = customer.getInvoices();

			for (Invoice invoiceCustomer : invoices) {

				Date fechaFactura = invoiceCustomer.getInvoiceDate();

				int mesFactura = fechaFactura.getMonth() + 1;
				int anioFactura = fechaFactura.getYear() + 1900;

				if (mesActual == mesFactura && anioActual == anioFactura) {	// Si una factura coincide con año y mes, volvemos a la vista del list
					ModelAndView resul = new ModelAndView("customer/list");

					resul.addObject("requestURI", "customer/administrator/list.do");
					resul.addObject("customers", customerService.findAll());

					resul.addObject("message", "invoice.administrator.facturaCreada");
					return resul;
				}

			}
		}

		/*
		 * SI NO , PASAMOS A FACTURAR EL MES
		 */

		InvoiceForm invoiceForm = new InvoiceForm();
		invoiceForm.setNumberDiets(0);
		invoiceForm.setNumberPersonalTraingin(0);

		ConfigurationSystem system = configurationSystemService.findTheOne();

		ModelAndView resul = new ModelAndView("invoice/edit");

		invoiceForm.setInvoice(invoice);
		invoiceForm.setMontlyPayment(system.getMonthlyPayment());
		invoiceForm.setDiscount(system.getDiscount());

		// Dietas contratadas (añadir que sea solo del mes)

		Collection<Diet> diets = new ArrayList<Diet>();
		Double dietsPrice = 0.0;

		for (RequestDiet requestDiet : customer.getRequestDiets()) {
			if (requestDiet.getStatus().equals("ACCEPTED")) {
				diets.add(requestDiet.getDiet());
				dietsPrice += requestDiet.getDiet().getCost();
			}
		}
		invoiceForm.setDiets(diets);
		invoiceForm.setDietsPrice(dietsPrice);

		// Entrenamientos contratados (añadir que sea solo del mes)

		Collection<PersonalTraining> personalTrainings = new ArrayList<PersonalTraining>();
		Double personalTrainingsCost = 0.0;
		for (RequestTraining requestTraining : customer.getRequestTrainings()) {
			if (requestTraining.getStatus().equals("ACCEPTED")) {
				personalTrainings.add(requestTraining.getPersonalTraining());
				personalTrainingsCost += requestTraining.getPersonalTraining().getCost();
			}
		}

		invoiceForm.setPersonalTrainings(personalTrainings);
		invoiceForm.setPersonalTrainingsPrice(personalTrainingsCost);

		invoiceForm.setNumberDiets(invoiceForm.getDiets().size());
		invoiceForm.setNumberPersonalTraingin(invoiceForm.getPersonalTrainings().size());
		invoiceForm.setCustomer(customer);

		invoiceForm.getInvoice().setTotalPrice(dietsPrice + personalTrainingsCost + invoiceForm.getMontlyPayment() - invoiceForm.getDiscount());
		resul.addObject("invoiceForm", invoiceForm);

		return resul;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "submit")
	public ModelAndView save(InvoiceForm invoiceForm) {

		ModelAndView resul;

		Invoice invoice = invoiceService.reconstruct(invoiceForm);

		try {

			invoiceService.computeInvoice(invoice);

			resul = new ModelAndView("customer/list");

			resul.addObject("requestURI", "customer/administrator/list.do");
			resul.addObject("customers", customerService.findAll());

		} catch (Throwable oops) {
			// TODO: handle exception
			resul = new ModelAndView("customer/list");

			resul.addObject("requestURI", "customer/administrator/list.do");
			resul.addObject("customers", customerService.findAll());

			resul.addObject("message", "invoice.administrator.error");
		}

		return resul;
	}
}
