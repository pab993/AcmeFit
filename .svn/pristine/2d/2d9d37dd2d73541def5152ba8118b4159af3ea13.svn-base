
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.InvoiceRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Booking;
import domain.CreditCard;
import domain.Customer;
import domain.Invoice;
import domain.LineInvoice;
import domain.RequestDiet;
import domain.RequestTraining;
import forms.InvoiceForm;

@Transactional
@Service
public class InvoiceService {

	//Repository
	//======================================================================

	@Autowired
	private InvoiceRepository	invoiceRepository;

	//Services
	// ======================================================================

	@Autowired
	private LineInvoiceService	lineInvoiceService;
	@Autowired
	private ActorService		actorService;

	@Autowired
	private CreditCardService	creditCardService;


	//CRUD methods
	//=======================================================================

	public Invoice findOne(final int id) {
		Invoice invoice;

		invoice = this.invoiceRepository.findOne(id);

		Assert.notNull(invoice);

		return invoice;
	}

	public Invoice create() {
		Assert.isInstanceOf(Administrator.class, this.actorService.findByPrincipal());

		final Invoice invoice = new Invoice();
		invoice.setInvoiceDate(new Date(System.currentTimeMillis() - 1));

		return invoice;
	}

	public Invoice save2(Invoice invoice) {
		Assert.notNull(invoice);

		return invoiceRepository.save(invoice);
	}

	public Invoice save(final Invoice invoice) {
		Assert.notNull(invoice);
		Assert.isTrue(invoice.getCustomer().equals(this.actorService.findByPrincipal()));
		Assert.isInstanceOf(Customer.class, this.actorService.findByPrincipal());

		final Invoice invoiceRes = this.invoiceRepository.save(invoice);

		return invoiceRes;
	}

	public Collection<Invoice> findAll() {

		Collection<Invoice> invoices;

		invoices = this.invoiceRepository.findAll();
		Assert.notNull(invoices);

		return invoices;
	}

	//Other bussiness methods
	// ==================================================================

	private void checkPrincipal(final Invoice invoice) {
		// comprueba qe el invoice pertence al usuario
		final Customer customer = (Customer) this.actorService.findByPrincipal();
		Assert.isTrue(invoice.getCustomer().equals(customer));
	}

	public Invoice findByUserAccount() {
		Invoice invoice;
		final UserAccount userAccount = LoginService.getPrincipal();
		invoice = this.invoiceRepository.findByUserAccount(userAccount.getId());

		return invoice;
	}

	public Double calculatePrice(final Invoice invoice) {
		Assert.notNull(invoice);
		Assert.notNull(this.actorService.findByPrincipal());
		Assert.isInstanceOf(Administrator.class, this.actorService.findByPrincipal());

		Double price = 0.;
		final Customer customer = invoice.getCustomer();

		price += customer.getMonthlyFee() + this.calculateLine(customer);

		return price;
	}

	public Double calculateLine(final Customer customer) {
		Double priceLines = 0.;

		for (final Booking b : customer.getBookings())
			priceLines += b.getService().getCost();
		for (final RequestDiet rd : customer.getRequestDiets())
			priceLines += rd.getDiet().getCost();
		for (final RequestTraining rt : customer.getRequestTrainings())
			priceLines += rt.getPersonalTraining().getCost();

		return priceLines;
	}

	public Collection<Invoice> findByCustomerId(final int customerId) {
		Collection<Invoice> invoices;

		invoices = this.invoiceRepository.findByCustomerId(customerId);

		return invoices;
	}

	public void paidInvoice(final int invoiceId) {

		final Invoice invoice = this.findOne(invoiceId);

		Assert.notNull(invoice);
		Assert.isTrue(invoice.getCustomer().equals(this.actorService.findByPrincipal()));
		Assert.isInstanceOf(Customer.class, this.actorService.findByPrincipal());

		final CreditCard creditCard = this.creditCardService.findByActorId(this.actorService.findByPrincipal().getId());
		Assert.notNull(creditCard);

		invoice.setPaid(true);

		this.invoiceRepository.save(invoice);
	}

	public Invoice reconstruct(InvoiceForm invoiceForm) {
		// TODO Auto-generated method stub

		Collection<LineInvoice> lineInvoices = new ArrayList<>();

		Invoice invoice = this.create();
		invoice.setCustomer(invoiceForm.getCustomer());
		invoice.setPaid(false);
		invoice.setTotalPrice(invoiceForm.getDietsPrice() + invoiceForm.getPersonalTrainingsPrice() + invoiceForm.getMontlyPayment() - invoiceForm.getDiscount());

		if (invoiceForm.getNumberDiets() > 0) {

			LineInvoice lineInvoice = lineInvoiceService.create();
			lineInvoice.setConcept("DIETS");
			lineInvoice.setPrice(invoiceForm.getDietsPrice());
			lineInvoice.setQuantity(invoiceForm.getNumberDiets());
			lineInvoices.add(lineInvoice);
		}
		if (invoiceForm.getNumberPersonalTraingin() > 0) {
			LineInvoice lineInvoice = lineInvoiceService.create();
			lineInvoice.setConcept("PERSONAL TRAINING");
			lineInvoice.setPrice(invoiceForm.getPersonalTrainingsPrice());
			lineInvoice.setQuantity(invoiceForm.getNumberPersonalTraingin());
			lineInvoices.add(lineInvoice);
		}

		invoice.setLineInvoices(lineInvoices);

		return invoice;
	}

	public Invoice computeInvoice(Invoice invoice) {
		// TODO Auto-generated method stub

		checkIsAdmin();

		return this.save2(invoice);
	}

	private void checkIsAdmin() {
		// TODO Auto-generated method stub
		UserAccount account = LoginService.getPrincipal();
		Collection<Authority> authorities = account.getAuthorities();
		Authority authority = authorities.iterator().next();

		Assert.isTrue(authority.getAuthority().equals("ADMINISTRATOR"));
	}
}
