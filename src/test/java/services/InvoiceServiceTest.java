
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Customer;
import domain.Invoice;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class InvoiceServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private InvoiceService	invoiceService;
	@Autowired
	private CustomerService	customerService;


	// Tests
	// ====================================================

	/*
	 * Edit the information of his or her profile.
	 * 
	 * En este caso de uso editaremos la información del perfil de un actor.
	 * Para provocar el error, introduciremos atributos que no cumplen con las restricciones y/o atributos no válidos.
	 */

	public void payInvoiceTest(final String username, final int invoiceId, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.invoiceService.paidInvoice(invoiceId);
			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ====================================================

	@Test
	public void driverPayInvoice() {

		Object testingData[][] = {
			// Pagar una factura con exito
			{
				"customer1", 165, null
			},
			// Pagar una factura sin estar autenticado -> false
			{
				null, 165, IllegalArgumentException.class
			},
			// Pagar una factura con un usuario no valido-> false
			{
				"customer2", 165, IllegalArgumentException.class
			},
			// Pagar una factura con un id de factura no valido -> false
			{
				"customer2", 10, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.payInvoiceTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);

		testingData = null;

	}

	@Test
	public void driverComputeInvoice() {

		Object testingData[][] = {
			// Generar una factura con exito para el cliente
			{
				"admin", 156, null
			},
			// Generar una factura para cliente sin estar autentificado -> false
			{
				null, 156, IllegalArgumentException.class
			},
			// Generar una factura para cliente no válido-> false
			{
				"admin", 500, IllegalArgumentException.class
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.computeInvoiceTest((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);

		testingData = null;

	}

	private void computeInvoiceTest(String aut, int customerID, Class<?> expected) {
		// TODO Auto-generated method stub

		Class<?> caught = null;

		try {

			this.authenticate(aut);

			Customer customer = customerService.findOne(customerID);

			Invoice invoice = invoiceService.create();
			invoice.setCustomer(customer);
			invoice.setPaid(false);
			invoice.setTotalPrice(100.1);

			invoiceService.computeInvoice(invoice);

			this.unauthenticate();

		} catch (Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);

	}

}
