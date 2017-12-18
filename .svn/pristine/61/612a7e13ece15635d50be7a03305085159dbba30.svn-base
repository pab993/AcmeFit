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

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.PersonalTraining;
import domain.Reward;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PersonalTrainingServiceServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private PersonalTrainingService	personalTrainingService;


	// Tests ------------------------------------------------------------------

	/*
	 * Create and edit a personal training.
	 * 
	 * En este caso de uso se crean y editan los entrenamientos personales.
	 * Para provocar el error, intentaremos crear y editar entrenamientos personales sin autenticar y con datos erroneos.
	 */

	protected void createAndSaveTest(final String username, final Double cost, final Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {
			final Date schedule = new Date();
			final Reward rewardsPoint = new Reward();
			rewardsPoint.setArchiveData(schedule);
			rewardsPoint.setObjetive("objetivetest");
			rewardsPoint.setPoints(10);

			this.authenticate(username);
			final PersonalTraining pt = this.personalTrainingService.create();
			pt.setCost(cost);
			pt.setDescription("Descriptiontest");
			pt.setDuration(40);
			pt.setIntensity("SOFT");
			pt.setNameActivity("activitytest");
			pt.setNameRoom("roomtest");
			pt.setRewardsPoint(rewardsPoint);
			pt.setSchedule(schedule);
			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	// Drivers
	// ====================================================

	@Test
	public void driverCreateAndSaveService() {

		final Object testingData1[][] = {
			// Creación de un entrenamiento personal si estoy autentificado como entrenador -> true
			{
				"trainer1", 50.0, null
			},
			// Creación de un entrenamiento personal si no estoy autentificado -> false
			{
				null, 50.0, IllegalArgumentException.class
			},
			// Creación de un entrenamiento personal si estoy autentificado como customer -> false
			{
				"customer1", 50.0, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.createAndSaveTest((String) testingData1[i][0], (Double) testingData1[i][1], (Class<?>) testingData1[i][2]);
	}

}
