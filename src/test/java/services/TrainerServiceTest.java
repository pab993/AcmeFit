
package services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Activity;
import domain.Assessment;
import domain.PersonalTraining;
import domain.Trainer;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TrainerServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private TrainerService	trainerService;


	// Tests
	// ====================================================

	/*
	 * Register a personal trainer to the system.
	 * 
	 * En este caso de uso se registran nuevos entrenadores personales en el sistema, siendo accesible unicamente por los administradores.
	 * Para provocar el error, introduciremos atributos que no cumplen con las restricciones y/o atributos no válidos.
	 */

	public void registerTest(String username, Trainer trainer, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.trainerService.save(trainer);
			this.unauthenticate();

		} catch (Throwable oops) {

			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	// Drivers
	// ====================================================

	@Test
	public void driverRegister() {

		Trainer trainer1 = this.trainerService.create();
		Trainer trainer2 = this.trainerService.create();
		Trainer trainer3 = this.trainerService.create();

		trainer1.setName("test");
		trainer1.setSurname("test");
		trainer1.setPhone("(+34)647382899");
		trainer1.setPicture("http://wwww.picture2.com");
		trainer1.setAddress("addressTest");
		trainer1.setEmail("trainer1@hotmail.com");
		trainer1.setAssessments(new ArrayList<Assessment>());
		trainer1.setPersonalTrainings(new ArrayList<PersonalTraining>());
		trainer1.setActivities(new ArrayList<Activity>());
		trainer1.setFee(0.0);
		trainer1.setSendAssessments(new ArrayList<Assessment>());
		trainer1.setIdEmployee("aaa-77483");

		trainer1.getUserAccount().setUsername("trainer1Test");
		trainer1.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword("trainer1Test", null));

		// OTRO TRAINER
		// ============================

		trainer2.setName("test2");
		trainer2.setSurname("test2");
		trainer2.setPhone("(+34)647382899");
		trainer2.setPicture("http://wwww.picture2.com");
		trainer2.setAddress("addressTest2");
		trainer2.setEmail("trainer2@hotmail.com");
		trainer2.setAssessments(new ArrayList<Assessment>());
		trainer1.setPersonalTrainings(new ArrayList<PersonalTraining>());
		trainer1.setActivities(new ArrayList<Activity>());
		trainer2.setFee(0.0);
		trainer2.setSendAssessments(new ArrayList<Assessment>());
		trainer2.setIdEmployee("aab-71483");

		trainer2.getUserAccount().setUsername("trainer2Test");
		trainer2.getUserAccount().setPassword(new Md5PasswordEncoder().encodePassword("trainer2Test", null));

		Object testingData[][] = {
			{
				"admin", trainer1, null
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.registerTest((String) testingData[i][0], (Trainer) testingData[i][1], (Class<?>) testingData[i][2]);

		testingData = null;

	}
}
