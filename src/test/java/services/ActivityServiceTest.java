
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ActivityServiceTest extends AbstractTest {

	// The SUT
	// ====================================================

	@Autowired
	private ActivityService	activityService;

	@Autowired
	private TrainerService	trainerService;


	// Tests
	// ====================================================

	/*
	 * Browse the catalogue of activities, navigate to the corresponding personal trainers, and display their profiles (must include their work experiences).
	 * 
	 * En este caso de uso se listan las actividades que hay registradas en el sistema, siendo accesible para cualquier usuario autentificado o no.
	 * A su vez, se puede acceder al perfil del entrenador correspondiente y mostrar la actividad oportuna.
	 * Para provocar el error, introduciremos nombres e Ids de actores, entrenadores y actividades que no existen.
	 */

	protected void listActivityTest(String username, Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);
			this.activityService.findAll();
			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	protected void FindOneTrainerByActivityTest(String username, int idTrainer, Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);

			this.trainerService.findOne2(idTrainer);

			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	protected void FindOneActivityTest(String username, int idActivity, Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;

		caught = null;
		try {

			this.authenticate(username);

			this.activityService.findOne(idActivity);

			this.unauthenticate();

		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}

	// Drivers
	// ====================================================

	@Test
	public void driverListActivity() {

		Object testingData1[][] = {
			// Obtenci�n de un listado de actividades si estoy autentificado como customer -> true
			{
				"customer1", null
			},
			// Obtenci�n de un listado de actividades si estoy autentificado como trainer -> true		
			{
				"trainer1", null
			},
			// Obtenci�n de un listado de actividades si estoy autentificado como nutritionist -> true		
			{
				"nutritionist1", null
			},
			// Obtenci�n de un listado de actividades si estoy autentificado como un nutritionist que no existe -> false				
			{
				"nutritionist22", IllegalArgumentException.class
			},
			// Obtenci�n de un listado de actividades si no estoy autentificado  -> true				
			{
				null, null
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.listActivityTest((String) testingData1[i][0], (Class<?>) testingData1[i][1]);
	}

	@Test
	public void driverFindOneTrainerByActivity() {

		Object testingData1[][] = {
			// Obtenci�n de un entrenador de cierta actividad si estoy autentificado como customer -> true
			{
				"customer1", 145, null
			},
			// Obtenci�n de un entrenador de cierta actividad si no estoy autentificado -> true			
			{
				null, 145, null
			},
			// Obtenci�n de un entrenador de cierta actividad si estoy autentificado como un customer que no existe -> false		
			{
				"customer11", 145, IllegalArgumentException.class
			},
			// Obtenci�n de un entrenador de cierta actividad si estoy autentificado como un customer y el id del entrenador buscado no existe -> false			
			{
				"customer1", 120, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.FindOneTrainerByActivityTest((String) testingData1[i][0], (int) testingData1[i][1], (Class<?>) testingData1[i][2]);
	}

	@Test
	public void driverFindOneActivityTest() {

		Object testingData1[][] = {
			// Obtenci�n de una actividad si estoy autentificado como customer -> true
			{
				"customer1", 160, null
			},
			// Obtenci�n de una actividad si no estoy autentificado -> true			
			{
				null, 160, null
			},
			// Obtenci�n de un entrenador de cierta actividad si estoy autentificado como un customer que no existe -> false		
			{
				"customer11", 160, IllegalArgumentException.class
			},
			// Obtenci�n de un entrenador de cierta actividad si estoy autentificado como un customer y el id de la  actividad buscada no existe -> false		
			{
				"customer1", 10, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData1.length; i++)
			this.FindOneActivityTest((String) testingData1[i][0], (int) testingData1[i][1], (Class<?>) testingData1[i][2]);
	}
}
