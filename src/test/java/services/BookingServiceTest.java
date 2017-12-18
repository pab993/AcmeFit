/*
 * SampleTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.AdditionalService;
import domain.Booking;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BookingServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private BookingService				bookingService;

	@Autowired
	private AdditionalServiceService	additionalServiceService;


	// Tests ------------------------------------------------------------------

	/*
	 * Browse the catalog of services and book in one of them. He or she can only make a reservation if at the time of booking the maximum number of capacity is not exceeded and he or she has a valid credit card.
	 * 
	 * En este caso de uso se listaran los servicios y se realizaran reservas en ellos.
	 * Para provocar errores, intentaremos realizar reservas sin autenticar y superar la capacidad maxima.
	 * 
	 * List his or her bookings allowing him or her to cancel any of them after a confirmation.
	 * En este caso de uso se listaran las reservas de un cliente y se cancelaran las mismas.
	 * Para provocar errores, intentaremos cancelar reservas sin autenticar y cancelar reservas de otros clientes.
	 */

	protected void createTest(String username, int serviceId, Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			AdditionalService service = this.additionalServiceService.findOne(serviceId);
			Booking booking = this.bookingService.create(service);
			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	protected void bookTest(String username, int serviceId, Date moment, Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			AdditionalService service = this.additionalServiceService.findOne(serviceId);
			Booking booking = this.bookingService.create(service);
			booking.setMoment(moment);
			this.bookingService.book(booking);
			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	protected void cancelTest(String username, int bookingId, Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.bookingService.cancel(bookingId);
			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	// Drivers
	// ====================================================

	@Test
	public void driverCreateBooking() throws ParseException {

		Object testingData1[][] = {
			// Crear una booking si estoy autentificado como customer, el id de service existe -> true
			{
				"customer1", 191, null
			},
			// Crear una booking si estoy autentificado como customer, el id de service no existe -> false
			{
				"customer2", 402, IllegalArgumentException.class
			},
			// Crear una booking si no estoy autentificado -> false
			{
				null, 190, IllegalArgumentException.class
			},
			// Crear una booking si estoy autentificado como administrator -> false
			{
				"admin", 190, IllegalArgumentException.class
			},
			// Crear una booking si estoy autentificado como trainer -> false
			{
				"trainer1", 190, IllegalArgumentException.class
			},
			// Crear una booking si estoy autentificado como nutritionist -> false
			{
				"nutritionist1", 190, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData1.length; i++)
			this.createTest((String) testingData1[i][0], (int) testingData1[i][1], (Class<?>) testingData1[i][2]);
	}

	@Test
	public void driverBookService() throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Object testingData1[][] = {
			// Crear una booking si estoy autentificado como customer, el id de service existe -> true
			{
				"customer1", 191, format.parse("01/10/2017 17:00"), null
			},
			// Crear una booking si estoy autentificado como customer, el id de service no existe -> true
			{
				"customer1", 315, format.parse("01/10/2017 17:00"), IllegalArgumentException.class
			},
			// Crear una booking si estoy autentificado como customer, el id de service existe pero sobrepasa la capacidad maxima -> false
			{
				"customer2", 191, format.parse("01/10/2017 17:15"), IllegalArgumentException.class
			},
			// Crear una booking si estoy autentificado como customer, el id de service existe pero momento fuera de horario -> false
			{
				"customer1", 190, format.parse("01/10/2017 05:15"), IllegalArgumentException.class
			},
			// Crear una booking si no estoy autentificado -> false
			{
				null, 190, format.parse("01/10/2017 05:15"), IllegalArgumentException.class
			},
			// Crear una booking si estoy autentificado como administrator -> false
			{
				"admin", 190, format.parse("01/10/2017 05:15"), IllegalArgumentException.class
			},
			// Crear una booking si estoy autentificado como trainer -> false
			{
				"trainer1", 190, format.parse("01/10/2017 05:15"), IllegalArgumentException.class
			},
			// Crear una booking si estoy autentificado como nutritionist -> false
			{
				"nutritionist1", 190, format.parse("01/10/2017 05:15"), IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData1.length; i++)
			this.bookTest((String) testingData1[i][0], (int) testingData1[i][1], (Date) testingData1[i][2], (Class<?>) testingData1[i][3]);
	}

	@Test
	public void driverCancelBooking() {

		Object testingData1[][] = {
			// Cancelar una booking si estoy autentificado como customer, el id existe y pertenece al customer -> true
			{
				"customer1", 192, null
			},
			// Cancelar una booking si estoy autentificado como customer, el id existe y no pertenece al customer -> false
			{
				"customer2", 192, IllegalArgumentException.class
			},
			// Cancelar una booking si no estoy autentificado -> false
			{
				null, 192, IllegalArgumentException.class
			},
			// Cancelar una booking si estoy autentificado como administrator -> false
			{
				"admin", 192, IllegalArgumentException.class
			},
			// Cancelar una booking si estoy autentificado como trainer -> false
			{
				"trainer1", 192, IllegalArgumentException.class
			},
			// Cancelar una booking si estoy autentificado como nutritionist -> false
			{
				"nutritionist1", 192, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData1.length; i++)
			this.cancelTest((String) testingData1[i][0], (int) testingData1[i][1], (Class<?>) testingData1[i][2]);
	}

}
