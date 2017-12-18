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

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdditionalServiceServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private AdditionalServiceService	additionalServiceService;


	// Tests ------------------------------------------------------------------

	/*
	 * Create, edit and delete services. A service cannot delete if there are confirmed bookings for it.
	 * 
	 * En este caso de uso se crean, editan y eliminan los servicios.
	 * Para provocar el error, intentaremos crear, editar y eliminar servicios sin autenticar y con datos erroneos y se intentaran eliminar servicios con reservas confirmadas.
	 */

	protected void createTest(String username, Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.additionalServiceService.create();
			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	protected void saveTest(String username, String name, Date openingTime, Date closingTime, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			AdditionalService service = this.additionalServiceService.create();
			service.setName(name);
			service.setOpeningTime(openingTime);
			service.setClosingTime(closingTime);
			service.setDuration(1);
			service.setCost(1.25);
			service.setDuration(10);

			this.additionalServiceService.save(service);

			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void editTest(String username, int serviceId, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			AdditionalService service = this.additionalServiceService.findOne(serviceId);

			this.additionalServiceService.save(service);

			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	protected void deleteTest(String username, int serviceId, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			AdditionalService service = this.additionalServiceService.findOne(serviceId);

			this.additionalServiceService.delete(service);

			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ====================================================

	@Test
	public void driverCreateService() {

		Object testingData1[][] = {
			// Creación de un service si estoy autentificado como administrator -> true
			{
				"admin", null
			},
			// Creación de un service si no estoy autentificado -> false
			{
				null, IllegalArgumentException.class
			},
			// Creación de un service si estoy autentificado como customer -> false
			{
				"customer1", IllegalArgumentException.class
			},
			// Creación de un service si estoy autentificado como trainer -> false
			{
				"trainer1", IllegalArgumentException.class
			},
			// Creación de un service si estoy autentificado como nutritionist -> false
			{
				"nutritionist1", IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData1.length; i++)
			this.createTest((String) testingData1[i][0], (Class<?>) testingData1[i][1]);
	}

	@Test
	public void driverSaveService() throws ParseException {
		DateFormat format = new SimpleDateFormat("HH:mm");

		Object testingData1[][] = {
			// Creación de un service si estoy autentificado como administrator y datos correctos -> true
			{
				"admin", "Test1", format.parse("06:00"), format.parse("15:00"), null
			},
			// Creación de un service si estoy autentificado como administrator y datos incorrectos (openingTime posterior closingTime) -> false
			{
				"admin", "Test2", format.parse("06:00"), format.parse("05:00"), IllegalArgumentException.class
			},
			// Creación de un service si no estoy autentificado -> false
			{
				null, "Test1", format.parse("06:00"), format.parse("15:00"), IllegalArgumentException.class
			},
			// Creación de un service si estoy autentificado como customer -> false
			{
				"customer1", "Test1", format.parse("06:00"), format.parse("15:00"), IllegalArgumentException.class
			},
			// Creación de un service si estoy autentificado como trainer -> false
			{
				"trainer1", "Test1", format.parse("06:00"), format.parse("15:00"), IllegalArgumentException.class
			},
			// Creación de un service si estoy autentificado como nutritionist -> false
			{
				"nutritionist1", "Test1", format.parse("06:00"), format.parse("15:00"), IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData1.length; i++)
			this.saveTest((String) testingData1[i][0], (String) testingData1[i][1], (Date) testingData1[i][2], (Date) testingData1[i][3], (Class<?>) testingData1[i][4]);
	}

	@Test
	public void driverEditService() {

		Object testingData1[][] = {
			// Edición de un service si estoy autentificado como administrator y el id existe -> true
			{
				"admin", 189, null
			},
			// Edición de un service si estoy autentificado como administrator y el id no existe -> false
			{
				"admin", 12, IllegalArgumentException.class
			},
			// Edición de un service si no estoy autentificado -> false
			{
				null, 1, IllegalArgumentException.class
			},
			// Edición de un service si estoy autentificado como customer -> false
			{
				"customer1", 2, IllegalArgumentException.class
			},
			// Edición de un service si estoy autentificado como trainer -> false
			{
				"trainer1", 190, IllegalArgumentException.class
			},
			// Edición de un service si estoy autentificado como nutritionist -> false
			{
				"nutritionist1", 190, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData1.length; i++)
			this.editTest((String) testingData1[i][0], (int) testingData1[i][1], (Class<?>) testingData1[i][2]);
	}

	@Test
	public void driverDeleteService() {

		Object testingData1[][] = {
			// Eliminar un service si estoy autentificado como administrator, el id existe y no tiene reservas confirmadas -> true
			{
				"admin", 190, null
			},
			// Eliminar un service si estoy autentificado como administrator, el id existe y tiene reservas confirmadas -> false
			{
				"admin", 189, IllegalArgumentException.class
			},
			// Eliminar un service si no estoy autentificado -> false
			{
				null, 1, IllegalArgumentException.class
			},
			// // Eliminar un service si estoy autentificado como customer -> false
			{
				"customer1", 2, IllegalArgumentException.class
			},
			// Eliminar un service si estoy autentificado como trainer -> false
			{
				"trainer1", 190, IllegalArgumentException.class
			},
			// Eliminar un service si estoy autentificado como nutritionist -> false
			{
				"nutritionist1", 190, IllegalArgumentException.class
			},
		};

		for (int i = 0; i < testingData1.length; i++)
			this.deleteTest((String) testingData1[i][0], (int) testingData1[i][1], (Class<?>) testingData1[i][2]);
	}

}
