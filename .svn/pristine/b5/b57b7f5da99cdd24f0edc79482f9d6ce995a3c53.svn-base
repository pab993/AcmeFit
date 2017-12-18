
package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.InvoiceService;
import domain.Customer;
import domain.Invoice;
import domain.LineInvoice;

@Controller
@RequestMapping("/invoice")
public class InvoiceController extends AbstractController {

	//Services
	//=======================================================

	@Autowired
	private InvoiceService	invoiceService;

	@Autowired
	private CustomerService	customerService;


	// Listing
	// ====================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listInvoice() {
		ModelAndView result;
		Collection<Invoice> invoices;

		final Customer principal = this.customerService.findByPrincipal();
		invoices = this.invoiceService.findByCustomerId(principal.getId());

		result = new ModelAndView("invoice/list");
		result.addObject("invoices", invoices);
		result.addObject("requestURI", "invoice/list.do");

		return result;
	}

	//Display Invoice
	// ====================================================================================

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int invoiceId) {
		final ModelAndView result;
		Invoice invoice;
		Date actual;
		Date invoiceDate;
		boolean payInvoice = false;

		actual = new Date(System.currentTimeMillis());
		final Collection<LineInvoice> lineInvoices = new ArrayList<LineInvoice>();
		invoice = this.invoiceService.findOne(invoiceId);
		invoiceDate = invoice.getInvoiceDate();

		invoiceDate.setDate(invoice.getInvoiceDate().getDate() + 7);

		if (actual.after(invoiceDate))
			payInvoice = true;

		for (final LineInvoice li : invoice.getLineInvoices())
			lineInvoices.add(li);

		result = new ModelAndView("invoice/display");
		result.addObject("invoice", invoice);
		result.addObject("payInvoice", payInvoice);
		result.addObject("lineInvoices", lineInvoices);
		result.addObject("requestURI", "invoice/display.do");

		return result;
	}

	// Paid
	// ===============================================================================

	@RequestMapping(value = "/paid", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam final int invoiceId) {
		ModelAndView resul;
		try {
			this.invoiceService.paidInvoice(invoiceId);
			resul = new ModelAndView("redirect:/invoice/list.do");
		} catch (final Throwable exception) {
			resul = new ModelAndView("redirect:/invoice/list.do");
		}

		return resul;
	}

	//Print

	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public ModelAndView print(@RequestParam final int invoiceId) {
		ModelAndView result;
		Invoice invoice;
		Collection<LineInvoice> lineInvoices = new ArrayList<LineInvoice>();

		try {
			invoice = this.invoiceService.findOne(invoiceId);
			for (final LineInvoice li : invoice.getLineInvoices())
				lineInvoices.add(li);

			result = new ModelAndView("invoice/print");
			result.addObject("invoice", invoice);
			result.addObject("lineInvoices", lineInvoices);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	// Ancillary Methods
	// ===============================================================================

	protected ModelAndView createEditModelAndView(final Invoice invoice) {
		ModelAndView result;

		result = this.createEditModelAndView(invoice, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Invoice invoice, final String message) {
		ModelAndView result;

		result = new ModelAndView("invoice/display");

		result.addObject("invoice", invoice);
		result.addObject("message", message);

		return result;
	}
}
